package br.com.Agencia.DTO;

import br.com.Agencia.Models.Continente;

public class RequisicaoContinente {
	
	private String nomecontinente;

	public String getNomecontinente() {
		return nomecontinente;
	}

	public void setNomecontinente(String nomecontinente) {
		this.nomecontinente = nomecontinente;
	}
	
	public void fromContinente(Continente continente) {
		this.nomecontinente = continente.getNome();
	}
	
	
}
