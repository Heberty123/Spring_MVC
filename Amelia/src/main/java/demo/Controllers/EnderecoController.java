package demo.Controllers;

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

import demo.DTO.RequisicaoEndereco;
import demo.Models.Cliente;
import demo.Models.Endereco;
import demo.Repositories.ClienteRepository;
import demo.Repositories.EnderecoRepository;

@Controller
@RequestMapping(value = "/Endereco")
public class EnderecoController {

	@Autowired
	private EnderecoRepository enderecoRepository;
	@Autowired
	private ClienteRepository clienteRepository;
	
	
	@GetMapping(value = "/incluir/{id}")
	public ModelAndView incluir(@PathVariable Long id) {
		ModelAndView mv = new ModelAndView("Endereco/formulario.html");
		
		Optional<Cliente> optional = this.clienteRepository.findById(id);
		Cliente cliente = optional.get();
		mv.addObject("nome", cliente.getNome());
		mv.addObject("id", cliente.getId());
		
		return mv;
	}
	
	
	@PostMapping(value = "/incluir/{id}")
	public ModelAndView Cadastrar(@PathVariable Long id, @Valid RequisicaoEndereco requisicaoEndereco) {
		ModelAndView mv = new ModelAndView("redirect:/Endereco/lista/" + id);
		
		Optional<Cliente> optional = this.clienteRepository.findById(id);
		Cliente cliente = optional.get();
		mv.addObject("nome", cliente.getNome());
		mv.addObject("id", cliente.getId());
		
		Endereco endereco = requisicaoEndereco.toEndereco();
		endereco.setCliente(cliente);
		this.enderecoRepository.save(endereco);
		this.clienteRepository.save(cliente);
		
		return mv;
	}
	
	
	@GetMapping(value = "/lista/{id}")
	public ModelAndView lista(@PathVariable Long id) {
		ModelAndView mv = new ModelAndView("Endereco/lista.html");
		
		Optional<Cliente> optional = this.clienteRepository.findById(id);
		Cliente cliente = optional.get();
		mv.addObject("nome", cliente.getNome());
		mv.addObject("id", cliente.getId());
		
		List<Endereco> enderecos = this.enderecoRepository.findAllEnderecoByIdOfCliente(id);
		mv.addObject("lista", enderecos);
		
		return mv;	
	}
	
	
	@GetMapping(value = "/edit/{id}")
	public ModelAndView edit(@PathVariable Long id) {
		ModelAndView mv = new ModelAndView("Endereco/editar.html");
		Cliente cliente = this.clienteRepository.findClienteByIdOfEndereco(id);
		Optional<Endereco> optional = this.enderecoRepository.findById(id);
		Endereco endereco = optional.get();
		mv.addObject("endereco", endereco);
		mv.addObject("nome", cliente.getNome());
		mv.addObject("id", cliente.getId());
		return mv;	
	}
	
	@PostMapping(value = "/alterar/{id}")
	public ModelAndView alterar(@PathVariable Long id, @Valid RequisicaoEndereco requisicaoEndereco) {
		Cliente cliente = this.clienteRepository.findClienteByIdOfEndereco(id);
		Endereco endereco = requisicaoEndereco.toEndereco();
		endereco.setId(id);
		endereco.setCliente(cliente);
		this.enderecoRepository.save(endereco);
		ModelAndView mv = new ModelAndView("redirect:/Endereco/lista/" + cliente.getId());
		
		mv.addObject("nome", cliente.getNome());
		mv.addObject("id", cliente.getId());
		return mv;	
	}
	
	
	@GetMapping(value = "/delete/{id}")
	public ModelAndView delete(@PathVariable Long id) {
		
		Cliente cliente = this.clienteRepository.findClienteByIdOfEndereco(id);
		this.enderecoRepository.deleteById(id);
		ModelAndView mv = new ModelAndView("redirect:/Endereco/lista/" + cliente.getId());
		
		return mv;	
	}
}
