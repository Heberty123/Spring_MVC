package br.com.Agencia.Controllers;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;


import com.fasterxml.jackson.annotation.JsonTypeInfo.As;

import br.com.Agencia.DTO.RequisicaoContinente;
import br.com.Agencia.DTO.RequisicaoTurismo;
import br.com.Agencia.Models.Continente;
import br.com.Agencia.Models.SalarioEnum;
import br.com.Agencia.Models.Turismo;
import br.com.Agencia.Repositories.ContinenteRepository;
import br.com.Agencia.Repositories.TurismoRepository;

@Controller
@RequestMapping(value = "/Turismo")
public class TurismoController {
	
	
	@Autowired
	private TurismoRepository turismoRepository;
	@Autowired
	private ContinenteRepository continenteRepository;
	
	
	@GetMapping("/")
	public String Index() {
		
		return "Turismo/index.html";
	}
	
	@GetMapping("/formulario")
	public ModelAndView Form(RequisicaoTurismo requisicaoTurismo) {
		ModelAndView mv = new ModelAndView("Turismo/formTurismo.html");
		List<SalarioEnum> salario = Arrays.asList(SalarioEnum.values());
		List<Continente> continente = this.continenteRepository.findAll();

		mv.addObject("salarios", salario);
		mv.addObject("continentes", continente);
		
		return mv;
	}
	
	@PostMapping("/criacao")
	public String Criacao(@Valid RequisicaoTurismo requisicaoTurismo, @Valid RequisicaoContinente requisicaoContinente) throws ParseException {
		Continente continente = this.continenteRepository.findContinenteByName(requisicaoContinente.getNomecontinente());
		
		//System.out.println("\n\n\n Here object continente: id:" + continente.getId() + ", nome -> " + continente.getNome());
		
		System.out.println("pegamos continente com id -> " + continente.getId() + " com nome (" + continente.getNome() + ").");
		
		Turismo turismo = new Turismo();
		turismo.fromReqTurismo(requisicaoTurismo);
		turismo.setContinente(continente);
		this.turismoRepository.save(turismo);
		return "Turismo/index.html";
	}
	
	
	@GetMapping("/listar")
	public ModelAndView listar() {
		ModelAndView mv = new ModelAndView("Turismo/listaViagens.html");
		List<Turismo> lista = this.turismoRepository.findAll();
		mv.addObject("lista", lista);
		
		List<String[]> strings = this.turismoRepository.findTurismoAllWithContinente();
		String[] primeirodalista =  strings.get(0);
		System.out.println("Id: " + primeirodalista[0] + ", nome: " + primeirodalista[1] + ", continente: " + primeirodalista[2]);
		
		return mv;
	}
	
	///Turismo/{turismoId}
	@GetMapping("/{id}")
	public ModelAndView detalhes(@PathVariable Long id, Turismo turismo) {
		
		Optional<Turismo> optional = this.turismoRepository.findById(id);
		
		if(optional.isPresent()) {
			turismo = optional.get();
			ModelAndView mv = new ModelAndView("Turismo/detalhes.html");
			mv.addObject("turismo", turismo);
			return mv;
		}
		else {
			return new ModelAndView("redirect:/Turismo/listar");
		}
	}
	
	
	///Turismo/edit/{turismoId}
	@GetMapping("/edit/{id}")
	public ModelAndView editar(@PathVariable Long id, RequisicaoTurismo requisicaoTurismo) {
		Optional<Turismo> optional = this.turismoRepository.findById(id);
		
		if(optional.isPresent()) {
			Turismo turismo = optional.get();
			turismo.toReqTurismo(requisicaoTurismo);
			ModelAndView mv = new ModelAndView("Turismo/edit.html");
			List<SalarioEnum> salarios = Arrays.asList(SalarioEnum.values());
			mv.addObject("salarios", salarios);
			mv.addObject("id", turismo.getId());
			return mv;
		}
		else {
			return new ModelAndView("redirect:/Turismo/listar");	
		}
	}
	
	
	@PostMapping("/edit/{id}")
	public ModelAndView edit(@Valid RequisicaoTurismo requisicaoTurismo, @PathVariable Long id) throws ParseException {
		Turismo turismo = new Turismo();
		requisicaoTurismo.setId(id);
		turismo.fromReqTurismo(requisicaoTurismo);
		this.turismoRepository.save(turismo);
		
		return new ModelAndView("redirect:/Turismo/listar");
	}
	
	
	///Turismo/delete/{turismoId}
	@GetMapping("/delete/{id}")
	public ModelAndView delete(@PathVariable Long id, Turismo turismo) {	
		this.turismoRepository.deleteById(id);

		return new ModelAndView("redirect:/Turismo/listar");
	}
}	
