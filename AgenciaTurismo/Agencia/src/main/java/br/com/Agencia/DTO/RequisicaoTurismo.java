package br.com.Agencia.DTO;

import java.util.Date;
import br.com.Agencia.Models.SalarioEnum;

public class RequisicaoTurismo {
		

	private Long id;
	private String nome;
	private String local;
	private String data_initial;
	private String data_final;
	private String descricao;
	private SalarioEnum salario;
	
	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getLocal() {
		return local;
	}
	public void setLocal(String local) {
		this.local = local;
	}
	public String getData_initial() {
		return data_initial;
	}
	public void setData_initial(String data_initial) {
		this.data_initial = data_initial;
	}
	public String getData_final() {
		return data_final;
	}
	public void setData_final(String data_final) {
		this.data_final = data_final;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public SalarioEnum getSalario() {
		return salario;
	}
	public void setSalario(SalarioEnum salario) {
		this.salario = salario;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	
	
}	
