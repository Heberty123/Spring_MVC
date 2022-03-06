package br.com.Agencia.Controllers;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.Agencia.Models.Continente;
import br.com.Agencia.Repositories.ContinenteRepository;

@Controller
@RequestMapping(value = "/Continente")
public class ContinenteController {
	
	ContinenteRepository continenteRepository;
	
	
	
}
