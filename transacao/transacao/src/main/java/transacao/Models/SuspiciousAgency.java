package transacao.Models;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SuspiciousAgency {

    private String banco;
    private Integer agencia;
    private double valor;
    private String tipo;

    public SuspiciousAgency() {}

    public SuspiciousAgency(String banco, Integer agencia, double valor, String tipo) {
        this.banco = banco;
        this.agencia = agencia;
        this.valor = valor;
        this.tipo = tipo;
    }
}
