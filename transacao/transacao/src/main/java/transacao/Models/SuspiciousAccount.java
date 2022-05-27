package transacao.Models;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SuspiciousAccount {

	private String banco;
	private Integer agencia;
	private String conta;
	private double valor;
	private String tipo;


	public SuspiciousAccount(){}

	public SuspiciousAccount(String banco, Integer agencia, String conta, double valor, String tipo) {
		this.banco = banco;
		this.agencia = agencia;
		this.conta = conta;
		this.valor = valor;
		this.tipo = tipo;
	}

}
