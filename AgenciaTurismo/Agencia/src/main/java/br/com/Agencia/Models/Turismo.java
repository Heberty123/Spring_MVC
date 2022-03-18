package br.com.Agencia.Models;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import br.com.Agencia.DTO.RequisicaoTurismo;

@Entity
public class Turismo {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(nullable = false)
	private String nome;
	@Column(nullable = false)
	private String local;
	@Column(nullable = false)
	private Date data_initial;
	@Column(nullable = false)
	private Date data_final;
	@Column(nullable = false)
	private String descricao;
	@Column(nullable = false)
	private SalarioEnum salario;
	@OneToMany(mappedBy="turismo", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private List<People> peoples = new ArrayList<>();
	@ManyToOne
	private Continente continente;
	
	public Turismo() {}
	

	public Turismo(String nome, String local, Date data_initial, Date data_final, String descricao,
			SalarioEnum salario) {
		this.nome = nome;
		this.local = local;
		this.data_initial = data_initial;
		this.data_final = data_final;
		this.descricao = descricao;
		this.salario = salario;
	}


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
	public Date getData_initial() {
		return data_initial;
	}
	public void setData_initial(Date data_initial) {
		this.data_initial = data_initial;
	}
	public Date getData_final() {
		return data_final;
	}
	public void setData_final(Date data_final) {
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
	public List<People> getPeoples() {
		return peoples;
	}
	public Continente getContinente() {
		return continente;
	}
	public void setContinente(Continente continente) {
		this.continente = continente;
	}


	public void fromReqTurismo(RequisicaoTurismo RTurismo) throws ParseException {
		
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
		
		this.id = RTurismo.getId();
		this.nome = RTurismo.getNome();
		this.data_initial = simpleDateFormat.parse(RTurismo.getData_initial());
		this.data_final = simpleDateFormat.parse(RTurismo.getData_final());
		this.descricao = RTurismo.getDescricao();
		this.local = RTurismo.getLocal();
		this.salario = RTurismo.getSalario();
		
	}
	
	
	public void toReqTurismo(RequisicaoTurismo RTurismo) {
		
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
		
		RTurismo.setData_initial(simpleDateFormat.format(this.data_initial));
		RTurismo.setData_final(simpleDateFormat.format(this.data_final));
		RTurismo.setDescricao(this.descricao);
		RTurismo.setLocal(this.local);
		RTurismo.setNome(this.nome);
		RTurismo.setSalario(this.salario);
	}
	
	
}
