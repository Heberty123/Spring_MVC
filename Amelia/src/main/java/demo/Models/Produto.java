package demo.Models;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;

@Entity
public class Produto {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String nome;
	private int referencia;
	private Long codigoFabricante;
	@OneToOne(/*fetch = FetchType.LAZY, cascade = CascadeType.ALL,*/ mappedBy = "produto")
	private Marca marca;
	
	@ManyToMany(mappedBy = "produtos"/*, fetch = FetchType.LAZY*/)
	private List<Cliente> clientes = new ArrayList<Cliente>();

	public Produto() {}
	
	
}
