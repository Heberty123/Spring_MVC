package br.com.Agencia.Controllers;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.internal.build.AllowSysOut;
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
		ModelAndView mv = new ModelAndView();
		
		List<String> lista = null;
		List<String[]> otherlist = new ArrayList<>();
		
		if(param.equals("people")) {
			System.out.println("\n\nVocê chamou people");
			
			lista = this.peopleRepository.findPeopleByName(pesquisa);
			
			for(int i = 0; i<lista.size(); i++) {
				otherlist.add(lista.get(i).split(","));
			}
			
			System.out.println(otherlist.get(0)[1]);
			
			mv.addObject("lista", otherlist);
			
		}
		else if(param.equals("turismo")) {
			System.out.println("\n\nVocê chamou turismo");
			
			lista = this.turismoRepository.findTurismoWithContinenteByName(pesquisa);
			System.out.println(lista);
		}
		else {
			System.out.println("Continente");
			
			lista = this.continenteRepository.findContinentesWithManyTurismoByName(pesquisa);
			System.out.println(lista);
		}
		
		System.out.println("Voce entrou na pesquisa");
		return new ModelAndView("Turismo/index.html");
	}



}
