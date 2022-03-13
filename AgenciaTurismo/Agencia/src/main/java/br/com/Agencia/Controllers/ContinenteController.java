package br.com.Agencia.Controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import br.com.Agencia.Models.Continente;
import br.com.Agencia.Repositories.ContinenteRepository;

@Controller
@RequestMapping(value = "/Continente")
public class ContinenteController {
	
	@Autowired
	private ContinenteRepository continenteRepository;

	
	@GetMapping("/index")
	public ModelAndView continentes() {
		ModelAndView mv = new ModelAndView("Continentes/index.html");
		
		List<Continente> continentes = this.continenteRepository.findAll();
		mv.addObject("lista", continentes);
		
		return mv;
	}
	
	

	
}
