package demo.Controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import demo.DTO.RequisicaoCliente;
import demo.Models.Cliente;
import demo.Repositories.ClienteRepository;

 

@Controller
@RequestMapping(value = "/Cliente")
public class ClienteController {

	@Autowired
	private ClienteRepository clienteRepository;
	
	@GetMapping(value = "/principal")
	public String Principal() {
		
		return "Cliente/principal.html";
	}
	
	@GetMapping(value = "/incluir")
	public String inclusao() {
		
		return "Cliente/inclusao.html";
	}
	
	@PostMapping(value = "/incluir")
	public ModelAndView inclusaoPost(@Valid RequisicaoCliente requisicaoCliente) {
		ModelAndView mv = new ModelAndView("Cliente/inclusao.html");
		Cliente clienteSearch = this.clienteRepository.findByCpf(requisicaoCliente.getCpf());

		if(clienteSearch != null) {
			System.out.println("já tem cliente com esse cpf");
			return mv;
		}

			Cliente cliente = requisicaoCliente.toCliente();
			mv.addObject("nome", cliente.getNome());
			mv.addObject("cpf", cliente.getCpf());
			mv.addObject("descricao", cliente.getDescricao());
			
			this.clienteRepository.save(cliente);
			Cliente cliente_exist = this.clienteRepository.findByCpf(cliente.getCpf());
			
			mv.addObject("id", cliente_exist.getId());
			mv.addObject("salvo", true);

			return mv;
		
	}
}
