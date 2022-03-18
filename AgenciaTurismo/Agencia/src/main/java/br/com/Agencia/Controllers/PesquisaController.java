package br.com.Agencia.Controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import br.com.Agencia.Models.People;
import br.com.Agencia.Repositories.ContinenteRepository;
import br.com.Agencia.Repositories.PeopleRepository;
import br.com.Agencia.Repositories.TurismoRepository;

@Controller
@RequestMapping(value = "/Pesquisa")
public class PesquisaController {
	
	@Autowired
	private TurismoRepository turismoRepository;
	@Autowired
	private PeopleRepository peopleRepository;
	@Autowired
	private ContinenteRepository continenteRepository;

	
	@PostMapping("/escolheu")
	public ModelAndView pesPeople(@RequestParam("opcao") String param, @RequestParam("input-pesquisa") String pesquisa) {
		
		if(param.equals("people")) {
			System.out.println("\n\nVocê chamou people");
			
			List<String> people = this.peopleRepository.findPeopleByName(pesquisa);
			System.out.println(people);
			
			
		}
		else if(param.equals("turismo")) {
			System.out.println("\n\nVocê chamou turismo");
			
			List<String> turismos = this.turismoRepository.findTurismoWithContinenteByName(pesquisa);
			System.out.println(turismos);
		}
		else {
			System.out.println("Continente");
			
			List<String> continente = this.continenteRepository.findContinentesWithManyTurismoByName(pesquisa);
			System.out.println(continente);
		}
		
		System.out.println("Voce entrou na pesquisa");
		return new ModelAndView("People/pesquisaPeople.html");
	}



}
