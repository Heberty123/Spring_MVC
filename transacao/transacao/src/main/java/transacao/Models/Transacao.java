package transacao.Models;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
@Getter
@Setter
@Entity	
public class Transacao {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String bancoOrigem;
	private Integer agenciaOrigem;
	private String contaOrigem;
	private String bancoDestino;
	private Integer agenciaDestino;
	private String contaDestino;
	private Double valor;
	private Date data;
	@ManyToOne
	private Importacao importacao;
	
	public Transacao() {}

	public Transacao(String bancoOrigem, Integer agenciaOrigem, String contaOrigem, String bancoDestino, Integer agenciaDestino,
			String contaDestino, Double valor, Date data) {
		this.bancoOrigem = bancoOrigem;
		this.agenciaOrigem = agenciaOrigem;
		this.contaOrigem = contaOrigem;
		this.bancoDestino = bancoDestino;
		this.agenciaDestino = agenciaDestino;
		this.contaDestino = contaDestino;
		this.valor = valor;
		this.data = data;
	}
	


	@Override
	public String toString() {
		return "Transacao [id=" + id + ", bancoOrigem=" + bancoOrigem + ", agenciaOrigem=" + agenciaOrigem
				+ ", contaOrigem=" + contaOrigem + ", bancoDestino=" + bancoDestino + ", agenciaDestino="
				+ agenciaDestino + ", contaDestino=" + contaDestino + ", valor=" + valor + ", data=" + data + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(agenciaDestino, agenciaOrigem, bancoDestino, bancoOrigem, contaDestino, contaOrigem, data,
				id, valor);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (valor == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Transacao other = (Transacao) obj;
		return agenciaDestino == other.agenciaDestino && agenciaOrigem == other.agenciaOrigem
				&& Objects.equals(bancoDestino, other.bancoDestino) && Objects.equals(bancoOrigem, other.bancoOrigem)
				&& Objects.equals(contaDestino, other.contaDestino) && Objects.equals(contaOrigem, other.contaOrigem)
				&& Objects.equals(data, other.data) && Objects.equals(id, other.id)
				&& Double.doubleToLongBits(valor) == Double.doubleToLongBits(other.valor);
	}

	
	
	public boolean hasNull() {
		
		if(this.agenciaDestino == null || this.agenciaOrigem == null || this.bancoDestino.isEmpty() || this.bancoOrigem.isEmpty() || this.contaDestino.isEmpty() || this.contaOrigem.isEmpty() || this.data == null || this.valor == null) {
			return true;
		}		
		return false;
	}
	
	
}
