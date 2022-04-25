package demo.Models;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;

@Entity
public class Produto {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String nome;
	private int referencia;
	private Long codigoFabricante;
	@OneToOne(/*fetch = FetchType.LAZY, cascade = CascadeType.ALL,*/ mappedBy = "produto")
	private Marca marca;
	
	@ManyToMany(mappedBy = "produtos")
	private List<Cliente> clientes = new ArrayList<Cliente>();

	public Produto() {}

	public Produto(String nome, int referencia, Long codigoFabricante, Marca marca) {
		this.nome = nome;
		this.referencia = referencia;
		this.codigoFabricante = codigoFabricante;
		this.marca = marca;
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

	public int getReferencia() {
		return referencia;
	}

	public void setReferencia(int referencia) {
		this.referencia = referencia;
	}

	public Long getCodigoFabricante() {
		return codigoFabricante;
	}

	public void setCodigoFabricante(Long codigoFabricante) {
		this.codigoFabricante = codigoFabricante;
	}

	public Marca getMarca() {
		return marca;
	}

	public void setMarca(Marca marca) {
		this.marca = marca;
	}

	public List<Cliente> getClientes() {
		return clientes;
	}

	public void DeleteAllByCollect(Object collect) {
		this.clientes.removeAll(clientes);
	}

	public void RemoverCliente(Cliente cliente) {
		this.clientes.remove(cliente);
		
	}
	
}
