package br.com.Agencia.DTO;

import javax.persistence.ManyToOne;

import br.com.Agencia.Models.Turismo;

public class RequisicaoPeople {
	
	
	private Long id;
	private String nome;
	private int idade;
	private String cpf;
	private String birthday;
	@ManyToOne
	private Turismo turismo;
	
	
	
	public Long getId() {
		return id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public int getIdade() {
		return idade;
	}
	public void setIdade(int idade) {
		this.idade = idade;
	}
	public String getCpf() {
		return cpf;
	}
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	public String getBirthday() {
		return birthday;
	}
	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}
	public Turismo getTurismo() {
		return turismo;
	}
	public void setTurismo(Turismo turismo) {
		this.turismo = turismo;
	}
	
	
	
	
}
