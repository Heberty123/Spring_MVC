package demo.Models;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Endereco {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String rua;
	private String bairro;
	private int numero;
	private String cidade;
	private int cep;
	private TipoEndereco tipo;
	private String descricao;
	private Long telefone1;
	private Long telefone2;
	@ManyToOne
	private Cliente cliente;
	
	public Endereco() {}

	public Endereco(String rua, String bairro, int numero, String cidade, int cep, TipoEndereco tipo,
			Long telefone1, Long telefone2) {
		this.rua = rua;
		this.bairro = bairro;
		this.numero = numero;
		this.cidade = cidade;
		this.cep = cep;
		this.tipo = tipo;
		this.telefone1 = telefone1;
		this.telefone2 = telefone2;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getRua() {
		return rua;
	}

	public void setRua(String rua) {
		this.rua = rua;
	}

	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	public int getNumero() {
		return numero;
	}

	public void setNumero(int numero) {
		this.numero = numero;
	}

	public String getCidade() {
		return cidade;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade;
	}

	public int getCep() {
		return cep;
	}

	public void setCep(int cep) {
		this.cep = cep;
	}

	public TipoEndereco getTipo() {
		return tipo;
	}

	public void setTipo(TipoEndereco tipo) {
		this.tipo = tipo;
	}

	public Long getTelefone1() {
		return telefone1;
	}

	public void setTelefone1(Long telefone1) {
		this.telefone1 = telefone1;
	}

	public Long getTelefone2() {
		return telefone2;
	}

	public void setTelefone2(Long telefone2) {
		this.telefone2 = telefone2;
	}
	
	
	
}
