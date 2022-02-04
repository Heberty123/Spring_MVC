package br.com.GerenciadorVagas.Heberty.Controllers;


import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import br.com.GerenciadorVagas.Heberty.dto.RequisicaoFormProfessor;
import br.com.GerenciadorVagas.Heberty.models.Professor;
import br.com.GerenciadorVagas.Heberty.models.StatusProfessor;
import br.com.GerenciadorVagas.Heberty.repositories.ProfessorRepository;

@Controller
@RequestMapping(value = "/professores")
public class ProfessorController {
	
	@Autowired
	private ProfessorRepository professorRepository;
	
	

	@GetMapping("")
	public ModelAndView index() {
		List<Professor> professores = this.professorRepository.findAll();
		ModelAndView mv = new ModelAndView("professores/index");
		mv.addObject("professores", professores);
		return mv;
	}
	
	@GetMapping("/new")
	public ModelAndView nnew(RequisicaoFormProfessor requisicao) {
		ModelAndView mv = new ModelAndView("professores/new");
		mv.addObject("listaStatusProfessor", StatusProfessor.values());
		return mv;
	}
	
	@PostMapping("")
	public ModelAndView create(@Valid RequisicaoFormProfessor requisicao, BindingResult bindingResult) {
		if(bindingResult.hasErrors()) {
			System.out.println("\n********************* Tem erros ********************\n");
			ModelAndView mv = new ModelAndView("professores/new");
			mv.addObject("listaStatusProfessor", StatusProfessor.values());
			return mv;
		}
		else {
			Professor professor = requisicao.toProfessor();
			this.professorRepository.save(professor);
			return new ModelAndView("redirect:/professores/" + professor.getId());	
		}
			
	}
	
	
	@GetMapping("/{id}")
	public ModelAndView show(@PathVariable Long id) {
		Optional<Professor> optional = this.professorRepository.findById(id);
		
		if(optional.isPresent()) {
			Professor professor = optional.get();
			ModelAndView mv = new ModelAndView("professores/show");
			mv.addObject("professor", professor);
			return mv;
		}
		else {
			System.out.println("$$$$$$$$$$$$$$$$$ NÃO ACHOU O PROFESSOR DE ID " + id + 	"$$$$$$$$$$$$$$$$");
			return new ModelAndView("redirect:/professores");
		}
	}
	
	
	@GetMapping("/{id}/edit")
	public ModelAndView edit(@PathVariable Long id, RequisicaoFormProfessor requisicao) {
		Optional<Professor> optional = this.professorRepository.findById(id);
		
		if (optional.isPresent()) {
			Professor professor = optional.get();
			requisicao.fromProfessor(professor);
			
			ModelAndView mv = new ModelAndView("Professores/edit");
			mv.addObject("professorId", professor.getId());
			mv.addObject("listaStatusProfessor", StatusProfessor.values());
			return mv;
		}
		
		else {
			System.out.println("$$$$$$$$$$$$$$$$$ NÃO ACHOU O PROFESSOR DE ID " + id + 	"$$$$$$$$$$$$$$$$");
			return new ModelAndView("redirect:/professores");
		}
		
	}
}
