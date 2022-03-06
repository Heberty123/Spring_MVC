package br.com.Agencia.Controllers;

import java.text.ParseException;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import br.com.Agencia.DTO.RequisicaoPeople;
import br.com.Agencia.DTO.RequisicaoTurismo;
import br.com.Agencia.Models.People;
import br.com.Agencia.Models.Turismo;
import br.com.Agencia.Repositories.PeopleRepository;
import br.com.Agencia.Repositories.TurismoRepository;

@Controller
@RequestMapping(value = "/People")
public class PeopleController {

	@Autowired
	private TurismoRepository turismoRepository;
	@Autowired
	private PeopleRepository peopleRepository;
	
	@GetMapping("/{id}")
	public ModelAndView Index(@PathVariable Long id) {
		
		ModelAndView mv = new ModelAndView("People/lista.html");
		
		Optional<Turismo> optional = this.turismoRepository.findById(id);
		Turismo turismo = optional.get();
		mv.addObject("turismo", turismo);
	
		
		List<People> peoples = this.peopleRepository.findAllByIdTurismo(id);
		
		System.out.println("Lista de people! da viagem para " + turismo.getNome());
		for (People people : peoples) {
			System.out.println("name -> " + people.getNome());
		}
		
		mv.addObject("lista", peoples);
		
		return mv;
	}
		
	
	@GetMapping("/form/{id}")
	public ModelAndView Form(RequisicaoPeople requisicaoPeople, @PathVariable Long id) {
		
		ModelAndView mv = new ModelAndView("People/form.html");
		mv.addObject("id", id);
		
		return mv;
	}
	
	
	@PostMapping("/add/{id}")
	public ModelAndView Adicionar(@Valid RequisicaoPeople requisicaoPeople, @PathVariable Long id) throws ParseException {
		
		ModelAndView mv = new ModelAndView("redirect:/People/" + id);
		
		Optional<Turismo> optional = turismoRepository.findById(id);
		Turismo turismo = optional.get();
		
		People people = new People();
		
		people.fromReqPeople(requisicaoPeople);
		people.setTurismo(turismo);
		this.peopleRepository.save(people);
		
		return mv;
	}
	
	
	
	@GetMapping("/detalhes/{id}")
	public ModelAndView detalhes(RequisicaoPeople requisicaoPeople ,@PathVariable Long id) {
		ModelAndView mv = new ModelAndView("People/detalhes.html");
		
		Optional<People> optional = this.peopleRepository.findById(id);
		People people = optional.get();
		people.toReqPeople(requisicaoPeople);
		mv.addObject("id", id);
		
		return mv;
	}
	
	
	//editar
	@GetMapping("/editar/{id}")
	public ModelAndView editar(RequisicaoPeople requisicaoPeople ,@PathVariable Long id) {
		ModelAndView mv = new ModelAndView("People/editar.html");
		
		Optional<People> optional = this.peopleRepository.findById(id);
		People people = optional.get();
		people.toReqPeople(requisicaoPeople);
		mv.addObject("id", id);
		
		return mv;
	}
	
	
	
	//alterar
	@PostMapping("/alterar/{id}")
	public ModelAndView alterar(@Valid RequisicaoPeople requisicaoPeople ,@PathVariable Long id) throws ParseException {
		People people = new People();
		people.fromReqPeople(requisicaoPeople);
		people.setId(id);
		
		Turismo turismo = this.turismoRepository.findTurismoByIdOfPeople(id);
		
		ModelAndView mv = new ModelAndView("redirect:/People/" + turismo.getId());
		
		System.out.println("Voce pegou turismo com nome: " + turismo.getNome());
		System.out.println("Voce pegou turismo com localidade: " + turismo.getLocal());
		
		people.setTurismo(turismo);
		
		this.peopleRepository.save(people);
		
		return mv;
	}
	

	
	//Excluir
	@GetMapping("/delete/{id}")
	public ModelAndView Excluir(@PathVariable Long id) throws ParseException {
		
		Turismo turismo = this.turismoRepository.findTurismoByIdOfPeople(id);
		
		ModelAndView mv = new ModelAndView("redirect:/People/" + turismo.getId());
		
		this.peopleRepository.deleteById(id);
		
		return mv;
	}
}
