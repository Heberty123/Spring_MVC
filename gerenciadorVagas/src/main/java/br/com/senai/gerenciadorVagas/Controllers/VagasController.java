package br.com.senai.gerenciadorVagas.Controllers;


import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
	
	@GetMapping("/vagas/detalhes/{id}")
	public ModelAndView detalhes(@PathVariable Long id) {
		ModelAndView mv = new ModelAndView("Vagas/detalhes");
		Optional<Vagas> optional = this.vagasRepository.findById(id);
		
		if(optional.isPresent()) {
			Vagas vaga = optional.get();
			mv.addObject("vaga", vaga);
			return mv;
		}
		else {
			return new ModelAndView("lista");
		}
	}
	
	@GetMapping("/vagas/editar/{id}")
	public ModelAndView Edit(@PathVariable Long id, Vagas vaga) {
		ModelAndView mv = new ModelAndView("Vagas/editar");
		Optional<Vagas> optional = this.vagasRepository.findById(id);
		
		if(optional.isPresent()) {
			Vagas vagaAUX = optional.get();
			vaga.from(vagaAUX);
			mv.addObject("vagaID", vagaAUX.getId());
			return mv;
		}
		else {
			return new ModelAndView("lista");
		}
	}
	
	
	@PostMapping("/Vagas/{id}")
	public ModelAndView update(@PathVariable Long id, Vagas vaga) {
		Vagas vagaEditada = new Vagas();
		vagaEditada.setId(id);
		vagaEditada.from(vaga);
		this.vagasRepository.save(vagaEditada);
		return new ModelAndView("redirect:/vagas/detalhes/" + vaga.getId());
	}
	
	
	@GetMapping("/vagas/delete/{id}")
	public ModelAndView Delete(@PathVariable Long id) {
		this.vagasRepository.deleteById(id);
		
		return new ModelAndView("redirect:/lista");
	}
	
	
}
