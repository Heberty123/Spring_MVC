package br.com.GerenciadorVagas.Heberty.Controllers;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.method.annotation.ViewNameMethodReturnValueHandler;

@Controller
public class HelloController {

	
	@GetMapping("/hello")
	public ModelAndView hello() {
		ModelAndView mv = new ModelAndView("hello");
		mv.addObject("nome", "Zete");
		return mv;
	}

	
	
	@GetMapping("/hello-model")
	public String Hello(Model model) {
		model.addAttribute("nome", "Amelia");
		return "hello";
	}
	
	
	@GetMapping("/hello-servlet")
	public String Hello(HttpServletRequest request) {
		request.setAttribute("nome", "Heberty");
		return "hello";
	}
}
