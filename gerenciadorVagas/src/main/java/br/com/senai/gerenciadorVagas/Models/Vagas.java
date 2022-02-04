package br.com.senai.gerenciadorVagas.Models;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Vagas {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long Id;
	@Column(nullable = false)
	private String Descricao;
	@Column(nullable = false)
	private String requisitosObrigatorios;
	@Column(nullable = false)
	private String requisitosDesejaveis;
	@Column(nullable = false)
	private double Remuneracao;
	@Column(nullable = false)
	private String Beneficios;
	@Column(nullable = false)
	private String localDeTrabalho;
	
	
	public Vagas() {}
	
	public Vagas(String descricao, String requisitosObrigatorios, String requisitosDesejaveis, double remuneracao,
		String beneficios, String localDeTrabalho) {
		super();
		Descricao = descricao;
		this.requisitosObrigatorios = requisitosObrigatorios;
		this.requisitosDesejaveis = requisitosDesejaveis;
		Remuneracao = remuneracao;
		Beneficios = beneficios;
		this.localDeTrabalho = localDeTrabalho;
	}

	
	

	public long getId() {
		return Id;
	}


	public void setId(long id) {
		Id = id;
	}


	public String getDescricao() {
		return Descricao;
	}


	public void setDescricao(String descricao) {
		Descricao = descricao;
	}


	public String getRequisitosObrigatorios() {
		return requisitosObrigatorios;
	}


	public void setRequisitosObrigatorios(String requisitosObrigatorios) {
		this.requisitosObrigatorios = requisitosObrigatorios;
	}


	public String getRequisitosDesejaveis() {
		return requisitosDesejaveis;
	}


	public void setRequisitosDesejaveis(String requisitosDesejaveis) {
		this.requisitosDesejaveis = requisitosDesejaveis;
	}


	public double getRemuneracao() {
		return Remuneracao;
	}


	public void setRemuneracao(double remuneracao) {
		Remuneracao = remuneracao;
	}


	public String getBeneficios() {
		return Beneficios;
	}


	public void setBeneficios(String beneficios) {
		Beneficios = beneficios;
	}


	public String getLocalDeTrabalho() {
		return localDeTrabalho;
	}


	public void setLocalDeTrabalho(String localDeTrabalho) {
		this.localDeTrabalho = localDeTrabalho;
	}
	
	public void from(Vagas vaga) {
		this.Descricao = vaga.Descricao;
		this.requisitosObrigatorios = vaga.requisitosObrigatorios;
		this.requisitosDesejaveis = vaga.requisitosDesejaveis;
		this.Remuneracao = vaga.Remuneracao;
		this.Beneficios = vaga.Beneficios;
		this.localDeTrabalho = vaga.localDeTrabalho;
		
	}
	
}
