package transacao.Controllers;


import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import transacao.Models.Importacao;
import transacao.Models.Transacao;
import transacao.Repositories.RepositoryImportacao;
import transacao.Repositories.RepositoryTransacao;
import transacao.Service.ReadFile;

@Controller
@RequestMapping("/Home")
public class PrincipalController {
	
	@Autowired
	RepositoryTransacao repositoryTransacao;
	
	@Autowired
	RepositoryImportacao repositoryImportacao;

	@RequestMapping("")
	public String Principal() {
		
		return "Home/home.html";
	}
	
	
	@PostMapping("/upload")
	public ModelAndView File(@RequestParam("file") MultipartFile file){
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
		

		for (Transacao transacao : lista) {
			this.repositoryTransacao.save(transacao);
			Importacao importacao = new Importacao(new Date(), transacao.getData());
			this.repositoryImportacao.save(importacao);
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
	
	
}
