package br.com.Agencia.Controllers;

import java.util.List;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import br.com.Agencia.Models.Continente;
import br.com.Agencia.Repositories.ContinenteRepository;

@Controller
@RequestMapping(value = "/Continente")
public class ContinenteController {

	/*
	static {
		ContinenteRepository continenteRepository = null;
		
		continenteRepository.save(new Continente("Europa"));
		continenteRepository.save(new Continente("America"));
		continenteRepository.save(new Continente("Ásia"));
		continenteRepository.save(new Continente("Oceania"));
		continenteRepository.save(new Continente("África"));
	}
	*/
	
	@GetMapping("/index")
	public ModelAndView continentes() {
		ModelAndView mv = new ModelAndView("Continentes/");
		
		
		return mv;
	}
	
	

	
}
