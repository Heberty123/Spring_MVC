package demo.DTO;

public class RequisicaoProduto {

	private String nome;
	private int referencia;
	private Long codigoFabricante;
	
	public RequisicaoProduto() {}

	
	public RequisicaoProduto(String nome, int referencia, Long codigoFabricante) {
		this.nome = nome;
		this.referencia = referencia;
		this.codigoFabricante = codigoFabricante;
	}


	public String getNome() {
		return nome;
	}


	public void setNome(String nome) {
		this.nome = nome;
	}


	public int getReferencia() {
		return referencia;
	}


	public void setReferencia(int referencia) {
		this.referencia = referencia;
	}


	public Long getCodigoFabricante() {
		return codigoFabricante;
	}


	public void setCodigoFabricante(Long codigoFabricante) {
		this.codigoFabricante = codigoFabricante;
	}
	
	
	
}
