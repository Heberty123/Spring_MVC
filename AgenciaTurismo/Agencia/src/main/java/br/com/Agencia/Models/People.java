package br.com.Agencia.Models;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import br.com.Agencia.DTO.RequisicaoPeople;
import br.com.Agencia.DTO.RequisicaoTurismo;

@Entity
public class People {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String nome;
	private int idade;
	private String cpf;
	private String birthday;
	@ManyToOne
	private Turismo turismo;
	
	public People() {}
	
	
	public People(String nome, int idade, String cpf, String birthday, Turismo turismo) {
		super();
		this.nome = nome;
		this.idade = idade;
		this.cpf = cpf;
		this.birthday = birthday;
		this.turismo = turismo;
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

	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	
	public void fromReqPeople(RequisicaoPeople RPeople) throws ParseException {
		
		this.id = RPeople.getId();
		this.nome = RPeople.getNome();
		this.idade = RPeople.getIdade();
		this.cpf = RPeople.getCpf();
		this.birthday = RPeople.getBirthday();
		
	}
	
	
	public void toReqPeople(RequisicaoPeople RPeople) {
		
		RPeople.setNome(this.getNome());
		RPeople.setIdade(this.getIdade());
		RPeople.setCpf(this.getCpf());
		RPeople.setBirthday(this.getBirthday());
	}
	
	
	
}
