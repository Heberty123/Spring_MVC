package br.com.GerenciadorVagas.Heberty.dto;

import java.math.BigDecimal;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import br.com.GerenciadorVagas.Heberty.models.Professor;
import br.com.GerenciadorVagas.Heberty.models.StatusProfessor;

public class RequisicaoNovoProfessor {

	@NotBlank
	@NotNull
	private String nome;
	@NotNull
	@DecimalMin("0.0")
	private BigDecimal salario;
	private StatusProfessor statusProfessor;
	
	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public BigDecimal getSalario() {
		return salario;
	}
	public void setSalario(BigDecimal salario) {
		this.salario = salario;
	}
	public StatusProfessor getStatusProfessor() {
		return statusProfessor;
	}
	public void setStatusProfessor(StatusProfessor statusProfessor) {
		this.statusProfessor = statusProfessor;
	}
	
	public Professor toProfessor() {
		Professor professor = new Professor(this.nome, this.salario, this.statusProfessor);
		return professor;
	}
	
	@Override
	public String toString() {
		return "RequesiçãoNovoProfessor{" + "nome='" + nome + "' salario='" + salario + "' statusProfessor='" + statusProfessor + "'}";
	}
	
	
	
}
