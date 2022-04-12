package demo.DTO;

import java.util.ArrayList;
import java.util.List;

import demo.Models.Cliente;
import demo.Models.Endereco;

public class RequisicaoCliente {
	
	
	private String nome;
	private Long cpf;
	private String descricao;
	private List<Endereco> enderecos = new ArrayList<Endereco>();
	
	public RequisicaoCliente() {}

	public RequisicaoCliente(String nome, Long cpf, String descricao) {
		this.nome = nome;
		this.cpf = cpf;
		this.descricao = descricao;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Long getCpf() {
		return cpf;
	}

	public void setCpf(Long cpf) {
		this.cpf = cpf;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	
	public Cliente toCliente() {
		Cliente cliente = new Cliente();
		cliente.setNome(this.nome);
		cliente.setCpf(this.cpf);
		cliente.setDescricao(this.descricao);
		return cliente;
	}
}
