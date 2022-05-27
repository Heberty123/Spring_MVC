package transacao.Controllers;

import java.text.ParseException;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.util.*;

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
import transacao.Models.Transacao;
import transacao.Models.Usuario;
import transacao.Repositories.RepositoryImportacao;
import transacao.Repositories.RepositoryTransacao;
import transacao.Repositories.RepositoryUser;
import transacao.Service.Check;
import transacao.Service.ReadFile;
import transacao.Service.Transacao.SusTransaction;

@Controller
@RequestMapping("/Home")
public class PrincipalController {
	
	@Autowired
	RepositoryTransacao repTransacao;
	@Autowired
	RepositoryImportacao repImportacao;
	@Autowired
	RepositoryUser repUser;
	@Autowired
	SusTransaction susTransaction;


	@RequestMapping("")
	public String Principal() { return "Home/home.html"; }
	
	
	@PostMapping("/upload")
	public ModelAndView File(@RequestParam("file") MultipartFile file, @CurrentSecurityContext(expression="authentication")
    Authentication authentication){
		
		Usuario usuario = this.repUser.findByUsername(authentication.getName());
		ModelAndView mv = new ModelAndView("Home/home.html");

		
		Map<String, List<Transacao>> mapa = null;
		try {
			mapa = ReadFile.Ready(file);
			
		}catch(Exception e) {
			
			mv.addObject("isInvalid", true);
			mv.addObject("erro", e.getMessage());
			return mv;
		}
		
		if(Check.erroDuplicadas(mapa, mv, repImportacao, repTransacao, usuario)) {
			return mv;
		}
		
		mv.addObject("sucess", "The file was import with sucess");
		return mv;
	}
	
	@RequestMapping("/importacoes")
	public ModelAndView importacoes() {
		ModelAndView mv = new ModelAndView("Home/importacoes.html");
		List<Importacao> listaImportacao = this.repImportacao.findAll();
		Collections.reverse(listaImportacao);
		mv.addObject("listaImportacoes", listaImportacao);
		return mv;
	}
	
	
	@RequestMapping("/detalhar/importacoes/{id}")
	public ModelAndView detalhes(@PathVariable Long id) {
		ModelAndView mv = new ModelAndView("Home/detalhesImportacao.html");
		
		Optional<Importacao> optional = this.repImportacao.findById(id);
		Importacao importacao = optional.get();
		
		List<Transacao> transacoes = this.repTransacao.findAllByIdOfImportacao(id);
		
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

		susTransaction.showSuspicious(local, mv);
		
		return mv;
	}
	
}
