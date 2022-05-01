package transacao.Controllers;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.CurrentSecurityContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import transacao.Models.Importacao;
import transacao.Models.SuspiciousAccount;
import transacao.Models.Transacao;
import transacao.Models.Usuario;
import transacao.Repositories.RepositoryImportacao;
import transacao.Repositories.RepositoryTransacao;
import transacao.Repositories.RepositoryUser;
import transacao.Service.ReadFile;
import transacao.Service.suspiciousTransaction;

@Controller
@RequestMapping("/Home")
public class PrincipalController {
	
	@Autowired
	RepositoryTransacao repositoryTransacao;
	
	@Autowired
	RepositoryImportacao repositoryImportacao;
	
	@Autowired
	RepositoryUser repositoryUser;

	@RequestMapping("")
	public String Principal() {
		return "Home/home.html";
	}
	
	
	@PostMapping("/upload")
	public ModelAndView File(@RequestParam("file") MultipartFile file, @CurrentSecurityContext(expression="authentication")
    Authentication authentication){
		
		Usuario usuario = this.repositoryUser.findByUsername(authentication.getName());
		
		ModelAndView mv = new ModelAndView("Home/home.html");
		
		if(file.isEmpty()) {
			mv.addObject("isInvalid", true);
			mv.addObject("erro", "O campo de upload está vazio !");
			return mv;
		}
		
		if(!file.getContentType().equals("text/csv")) {
			mv.addObject("isInvalid", true);
			mv.addObject("erro", "Só é permitido fazer upload arquivo com extensão csv !");
			return mv;
		}
		
		System.out.println("Está vazio: " + ReadFile.fileIsEmpty(file));
		if(ReadFile.fileIsEmpty(file)) {
			mv.addObject("emptyContent", true);
			return mv;
		}

		
		System.out.println("\n\nO nome do file é: " + file.getOriginalFilename());
		System.out.println("O tamanho em megabytes do file é: " + file.getSize());
		System.out.println("\n\n");
		
		Map<String, List<Transacao>> mapa = ReadFile.Ready(file);
		
		List<Transacao> lista = mapa.get("lista");		
		List<Transacao> listaDateErro = mapa.get("erroDate");
		List<Transacao> listaErroDuplicadas = (List<Transacao>) mapa.get("duplicidades");
		List<Transacao> listaErroNull = mapa.get("erroNull");
		
		
		if(!listaDateErro.isEmpty()) {
			mv.addObject("listaErroDate", listaDateErro);
			mv.addObject("erroDate", true);
		}
		
		if(!listaErroNull.isEmpty()) {
			mv.addObject("listaErroNull", listaErroNull);
			mv.addObject("erroNull", true);
		}
		
		if(!listaErroDuplicadas.isEmpty()) {
			mv.addObject("listaErroDuplicadas", listaErroDuplicadas);
			mv.addObject("isInvalid", true);
			mv.addObject("erroDuplicado", true);
			return mv;
		}
		
		Importacao importacao = null;
		if(!lista.isEmpty()) {
			importacao = new Importacao(new Date(), lista.get(0).getData());
			importacao.setUsuario(usuario);
			this.repositoryImportacao.save(importacao);
		}
		

		for (Transacao transacao : lista) {
			transacao.setImportacao(importacao);
			this.repositoryTransacao.save(transacao);
		}
		
		List<Importacao> listaImportacao = this.repositoryImportacao.findAll();
		Collections.reverse(listaImportacao);
		mv.addObject("listaImportacoes", listaImportacao);
		
		return mv;
	}
	
	@RequestMapping("/importacoes")
	public ModelAndView importacoes() {
		ModelAndView mv = new ModelAndView("Home/importacoes.html");
		List<Importacao> listaImportacao = this.repositoryImportacao.findAll();
		Collections.reverse(listaImportacao);
		mv.addObject("listaImportacoes", listaImportacao);
		return mv;
	}
	
	
	@RequestMapping("/detalhar/importacoes/{id}")
	public ModelAndView detalhes(@PathVariable Long id) {
		ModelAndView mv = new ModelAndView("Home/detalhesImportacao.html");
		
		Optional<Importacao> optional = this.repositoryImportacao.findById(id);
		Importacao importacao = optional.get();
		
		List<Transacao> transacoes = this.repositoryTransacao.findAllByIdOfImportacao(id);
		
		mv.addObject("importacao", importacao);
		mv.addObject("listaTransacoes", transacoes);
		
		return mv;
	}
	
	
	@RequestMapping("/suspeitas")
	public ModelAndView suspeitas() {
		ModelAndView mv = new ModelAndView("Home/suspeitas.html");
		
		return mv;
	}
	
	
	@PostMapping("/suspeitas")
	public ModelAndView suspeitas(@RequestParam("data") String data) throws ParseException {
		ModelAndView mv = new ModelAndView("Home/suspeitas.html");
		mv.addObject("data", data);
		
		YearMonth local = YearMonth.parse(data, DateTimeFormatter.ofPattern("yyyy-MM"));
		
		System.out.println("Month: " + local.getMonthValue());
		System.out.println("Year: " + local.getYear());
		
		List<Transacao> SusTransacao = this.repositoryTransacao.findAllSuspiciousTransactionsWithMonthAndYear(Double.valueOf("100000.00"), local.getMonthValue(), local.getYear());
		
		List<SuspiciousAccount> SusAccount = suspiciousTransaction.Account(repositoryTransacao.findAllWithMonthAndYear(local.getMonthValue(), local.getYear()), repositoryTransacao, Double.valueOf("1000000.00"), "saída");
				
		
		mv.addObject("SusTransacao", SusTransacao);
		mv.addObject("SusAccount", SusAccount);
		
		
		
		return mv;
	}
	
}
