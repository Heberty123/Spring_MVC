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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import demo.DTO.RequisicaoCliente;
import demo.Models.Cliente;
import demo.Models.Endereco;
import demo.Models.Produto;
import demo.Repositories.ClienteRepository;
import demo.Repositories.EnderecoRepository;
import demo.Repositories.ProdutoRepository;

 

@Controller
@RequestMapping(value = "/Cliente")
public class ClienteController {

	@Autowired
	private ClienteRepository clienteRepository;
	
	@Autowired
	private ProdutoRepository produtoRepository;
	
	@Autowired
	private EnderecoRepository enderecoRepository;
	
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
		ModelAndView mv = new ModelAndView("redirect:/Cliente/listar");
		Cliente clienteSearch = this.clienteRepository.findByCpf(requisicaoCliente.getCpf());

		if(clienteSearch != null) {
			System.out.println("já tem cliente com esse cpf");
			mv.addObject("aviso", true);
			return mv;
		}

			Cliente cliente = requisicaoCliente.toCliente();
			mv.addObject("nome", cliente.getNome());
			mv.addObject("cpf", cliente.getCpf());
			mv.addObject("descricao", cliente.getDescricao());
			
			this.clienteRepository.save(cliente);

			return mv;
	}
	
	
	@GetMapping(value = "/listar")
	public ModelAndView listar() {
		ModelAndView mv = new ModelAndView("Cliente/lista.html");
		List<Cliente> clientes = this.clienteRepository.findAll();
		
		mv.addObject("lista", clientes);
		
		return mv;
	}
	
	
	@GetMapping(value = "/delete/{id}")
	public ModelAndView delete(@PathVariable Long id) {
		ModelAndView mv = new ModelAndView("redirect:/Cliente/listar");
		
		List<Produto> produtos = this.produtoRepository.findAllProdutoByIdOfCliente(id);
		List<Endereco> enderecos = this.enderecoRepository.findAllEnderecoByIdOfCliente(id);
		Optional<Cliente> optional = this.clienteRepository.findById(id);
		Cliente cliente = optional.get();
		
		/*
		for (Produto produto : produtos) {
			produto.RemoverCliente(cliente);
			this.produtoRepository.save(produto);
		}
		*/
		
		for (Endereco endereco : enderecos) {
			cliente.RemoverEndereco(endereco);
		}
	
		this.clienteRepository.save(cliente);
		this.clienteRepository.delete(cliente);
		
		return mv;
	}
	
	@GetMapping(value = "/edit/{id}")
	public ModelAndView edit(@PathVariable Long id) {
		ModelAndView mv = new ModelAndView("Cliente/editar.html");
		
		Optional<Cliente> optional = this.clienteRepository.findById(id);
		Cliente cliente = optional.get();
		
		mv.addObject("cliente", cliente);
		
		return mv;
	}
	
	
	@GetMapping(value = "/alterar/{id}")
	public ModelAndView alterar(@PathVariable Long id, @RequestParam(name = "nome") String nome, @RequestParam(name = "cpf") Long  cpf, @RequestParam(name = "descricao") String descricao) {
		ModelAndView mv = new ModelAndView("redirect:/Cliente/listar");
		
		Cliente cliente = new Cliente();
		cliente.setId(id);
		cliente.setNome(nome);
		cliente.setCpf(cpf);
		cliente.setDescricao(descricao);

		this.clienteRepository.save(cliente);
		
		return mv;
	}
}
