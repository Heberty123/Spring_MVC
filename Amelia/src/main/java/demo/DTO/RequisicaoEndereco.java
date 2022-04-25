package demo.DTO;

import demo.Models.Endereco;

public class RequisicaoEndereco {

	private Long id;
	private String rua;
	private String bairro;
	private int numero;
	private String cidade;
	private int cep;
	private String tipo;
	private String descricao;
	private Long telefone1;
	private Long telefone2;
	
	public RequisicaoEndereco() {}

	public RequisicaoEndereco(Long id, String rua, String bairro, int numero, String cidade, int cep, String tipo,
			String descricao, Long telefone1, Long telefone2) {
		this.id = id;
		this.rua = rua;
		this.bairro = bairro;
		this.numero = numero;
		this.cidade = cidade;
		this.cep = cep;
		this.tipo = tipo;
		this.descricao = descricao;
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

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
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
	
	public Endereco toEndereco() {
		Endereco endereco = new Endereco();
		
		endereco.setRua(this.rua);
		endereco.setBairro(this.bairro);
		endereco.setNumero(this.numero);
		endereco.setCidade(this.cidade);
		endereco.setCep(this.cep);
		endereco.setTipo(this.tipo);
		endereco.setDescricao(this.descricao);
		endereco.setTelefone1(this.telefone1);
		endereco.setTelefone2(this.telefone2);
		
		return endereco;
	}
}
