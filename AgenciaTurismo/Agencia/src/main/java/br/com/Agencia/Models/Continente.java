package br.com.Agencia.Models;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "continente")
public class Continente {

	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(nullable = false)
	private String nome;
	@OneToMany(mappedBy="continente", cascade = CascadeType.ALL)
	private List<Turismo> turismos = new ArrayList<Turismo>();
	
	
	public Continente() {}
	
	
	public Continente(String nome) {
		this.nome = nome;
	}
	
	
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public List<Turismo> getTurismos() {
		return turismos;
	}

	public void setTurismos(List<Turismo> turismos) {
		this.turismos = turismos;
	}

	public Long getId() {
		return id;
	}

	
}
