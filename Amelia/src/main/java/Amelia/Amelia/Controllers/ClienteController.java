package Amelia.Amelia.Controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping(value = "/Amelia")
public class ClienteController {

	@RequestMapping(value = "/principal")
	public ModelAndView Principal() {
		System.out.println("Fui chamado");
		return new ModelAndView("Clientes/principal.html");
	}
}
