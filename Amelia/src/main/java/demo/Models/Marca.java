package demo.Models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Entity
public class Marca {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String descricao;
	@OneToOne(/*fetch = FetchType.LAZY*/)
	@JoinColumn(name = "produto_id"/*, nullable = false, unique = true*/)
	private Produto produto;
	
	public Marca() {}

	
	
}
