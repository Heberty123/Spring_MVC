package transacao.Models;

import lombok.Getter;
import lombok.Setter;
import org.apache.tomcat.jni.Local;
import transacao.Models.RedeSocial.Comentario;
import transacao.Service.ConfigImg;

import java.time.*;
import java.util.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Getter
@Setter
@Entity
public class Importacao implements Comparable<Importacao> {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private ZonedDateTime now = ZonedDateTime.now(ZoneId.of("America/Sao_Paulo"));
	private Date date;

	@ManyToOne
	private Usuario usuario;
	@OneToMany(mappedBy="importacao")
	private List<Transacao> transacoes = new ArrayList<Transacao>();
	

	public Importacao() {}
	
	public Importacao(Date date) {
		this.date = date;
	}

	@Override
	public int compareTo(Importacao o) {
		return this.now.compareTo(o.getNow());
	}






	
}
