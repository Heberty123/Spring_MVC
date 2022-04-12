package demo.Models;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

import demo.DTO.RequisicaoCliente;

@Entity
public class Cliente {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String nome;
	@Column(name="cpf", unique=true)
	private Long cpf;
	private String descricao;
	@OneToMany(mappedBy="cliente"/*, cascade = CascadeType.ALL, fetch = FetchType.LAZY*/)
	private List<Endereco> enderecos = new ArrayList<Endereco>();
	
	@ManyToMany(/*fetch = FetchType.LAZY, cascade = CascadeType.PERSIST*/)
    @JoinTable(name = "clientes_produtos",
            joinColumns = {
                    @JoinColumn(name = "client_id", referencedColumnName = "id",
                            nullable = false, updatable = false)},
            inverseJoinColumns = {
                    @JoinColumn(name = "produto_id", referencedColumnName = "id",
                            nullable = false, updatable = false)})
	private List<Produto> produtos = new ArrayList<Produto>();
	
	public Cliente() {}

	public Cliente(String nome, Long cpf, String descricao) {
		super();
		this.nome = nome;
		this.cpf = cpf;
		this.descricao = descricao;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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
	
	public RequisicaoCliente FromCliente() {
		RequisicaoCliente req = new RequisicaoCliente();
		req.setNome(this.nome);
		req.setCpf(this.cpf);
		req.setDescricao(this.descricao);
		return req;
	}
	
}
