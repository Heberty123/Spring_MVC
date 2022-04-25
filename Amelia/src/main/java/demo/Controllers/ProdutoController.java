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

import demo.DTO.RequisicaoProduto;
import demo.Models.Cliente;
import demo.Models.Produto;
import demo.Repositories.ClienteRepository;
import demo.Repositories.ProdutoRepository;

@Controller
@RequestMapping(value = "/Produto")
public class ProdutoController {
	
	@Autowired
	private ProdutoRepository produtoRepository;
	@Autowired
	private ClienteRepository clienteRepository;
	
	@GetMapping(value = "/incluir")
	public ModelAndView incluir() {
		ModelAndView mv = new ModelAndView("Produto/formulario.html");
		
		return mv;
	}
	
	
	@PostMapping(value = "/incluir")
	public ModelAndView adicionar(@Valid RequisicaoProduto requisicaoProduto) {
		ModelAndView mv = new ModelAndView("redirect:/Cliente/listar");
		Produto produto = new Produto();
		produto.setNome(requisicaoProduto.getNome());
		produto.setReferencia(requisicaoProduto.getReferencia());
		produto.setCodigoFabricante(requisicaoProduto.getCodigoFabricante());
				
		this.produtoRepository.save(produto);
		
		return mv;
	}
	
	
	@GetMapping(value = "/listar")
	public ModelAndView listar() {
		ModelAndView mv = new ModelAndView("Produto/lista.html");

		List<Produto> lista = this.produtoRepository.findAll();
		mv.addObject("lista", lista);
		
		return mv;
	}
	
	
	@GetMapping(value = "/remover/{id}")
	public ModelAndView remover(@PathVariable Long id) {
		ModelAndView mv = new ModelAndView("redirect:/Produto/listar");
		
		List<Cliente> clientes = this.produtoRepository.findAllClienteByIdOfProduto(id);
		Optional<Produto> optional = this.produtoRepository.findById(id);
		Produto produto = optional.get();
		
		for (Cliente cliente : clientes) {
			cliente.RemoverProduto(produto);
			this.clienteRepository.save(cliente);
		}
		
		this.produtoRepository.deleteById(id);
		
		return mv;
	}
	
	
	@GetMapping(value = "/produtos/{id}")
	public ModelAndView listar(@PathVariable Long id) {
		ModelAndView mv = new ModelAndView("Produto/produtos.html");
		
		Optional<Cliente> optional = this.clienteRepository.findById(id);
		Cliente cliente = optional.get();
		List<Produto> lista = this.produtoRepository.findAllProdutoByIdOfCliente(id);
		mv.addObject("cliente", cliente);
		mv.addObject("lista", lista);
		
		return mv;
	}
	
	@GetMapping(value = "/adiciona/{id}")
	public ModelAndView listarAdd(@PathVariable Long id) {
		ModelAndView mv = new ModelAndView("Produto/adiciona.html");
		
		Optional<Cliente> optional = this.clienteRepository.findById(id);
		Cliente cliente = optional.get();
		List<Produto> lista = this.produtoRepository.findAll();
		mv.addObject("cliente", cliente);
		mv.addObject("lista", lista);
		
		return mv;
	}
	
	
	@GetMapping(value = "/adicionar/{id}/{cliente}")
	public ModelAndView adicionar(@PathVariable Long id, @PathVariable long cliente) {
		ModelAndView mv = new ModelAndView("Produto/AddSucess.html");
		
		Optional<Cliente> optional = this.clienteRepository.findById(cliente);
		Cliente clientee = optional.get();
		Optional<Produto> optionalProduto = this.produtoRepository.findById(id);
		Produto produto = optionalProduto.get();
		
		clientee.AdicionarProduto(produto);
		this.clienteRepository.save(clientee);
		mv.addObject("produto", produto);
		mv.addObject("cliente", clientee);
		
		return mv;
	}
	
	
	
	
	@GetMapping(value = "/removeFromCliente/{id}/{cliente}")
	public ModelAndView removeFromCliente(@PathVariable Long id, @PathVariable long cliente) {
		ModelAndView mv = new ModelAndView("redirect:/Produto/produtos/" + cliente);
		
		Optional<Cliente> optional = this.clienteRepository.findById(cliente);
		Cliente clientee = optional.get();
		Optional<Produto> optionalProduto = this.produtoRepository.findById(id);
		Produto produto = optionalProduto.get();
		
		clientee.RemoverProduto(produto);
		this.clienteRepository.save(clientee);
		mv.addObject("produto", produto);
		mv.addObject("cliente", clientee);
		
		return mv;
	}
	

}
