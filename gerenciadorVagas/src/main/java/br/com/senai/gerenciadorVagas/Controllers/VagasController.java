package br.com.senai.gerenciadorVagas.Controllers;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import br.com.senai.gerenciadorVagas.Models.Vagas;
import br.com.senai.gerenciadorVagas.Repositories.VagasRepository;

@Controller
public class VagasController {
	
	@Autowired
	private VagasRepository vagasRepository;

	
	@GetMapping("/Bem-vindo")
	public ModelAndView BemVindo() {
		ModelAndView mv = new ModelAndView("Bem-vindo");
		return mv;
	}
	
	@GetMapping("/inicial")
	public ModelAndView Index() {
		ModelAndView mv = new ModelAndView("Vagas/index");
		return mv;
	}
	
	@GetMapping("/incluir")
	public ModelAndView Incluir() {
		ModelAndView mv = new ModelAndView("Vagas/incluir");
		return mv;
	}
	
	@PostMapping("/Vagas")
	public ModelAndView Vagas(Vagas vagas) {
		this.vagasRepository.save(vagas);
		return new ModelAndView("redirect:/lista");
	}
	
	@GetMapping("/lista")
	public ModelAndView lista() {
		ModelAndView mv = new ModelAndView("Vagas/listaVagas");
		List<Vagas> vagas = this.vagasRepository.findAll();
		mv.addObject("lista", vagas);
		return mv;
	}
}
