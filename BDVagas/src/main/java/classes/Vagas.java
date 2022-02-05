 package classes;

public class Vagas {
	
	private int id;
	private String Descricao;
	private String requisitosObrigatorios;
	private String requisitosDesejaveis;
	private String remuneracao;
	private String beneficio;
	private String localDeTrabalho;
	private int aberta;
	
	
	/*
	 * Comando SQL
	
	Create table vagas (
			id int primary key auto_increment,
			descricao varchar(50),
			requisitosObrigatorios varchar(50),
			requisitosDesejaveis varchar(50),
			remuneracao varchar(30),
			beneficio varchar(50),
			localDeTrabalho varchar(50),
			aberta int
			);
	
	
	*/
	
	
	public Vagas(int id, String descricao, String requisitosObrigatorios, String requisitosDesejaveis,
			String remuneracao, String beneficio, String localDeTrabalho, int aberta) {
		super();
		this.id = id;
		Descricao = descricao;
		this.requisitosObrigatorios = requisitosObrigatorios;
		this.requisitosDesejaveis = requisitosDesejaveis;
		this.remuneracao = remuneracao;
		this.beneficio = beneficio;
		this.localDeTrabalho = localDeTrabalho;
		this.aberta = aberta;
	}


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public String getDescricao() {
		return Descricao;
	}


	public void setDescricao(String descricao) {
		Descricao = descricao;
	}


	public String getRequisitosObrigatorios() {
		return requisitosObrigatorios;
	}


	public void setRequisitosObrigatorios(String requisitosObrigatorios) {
		this.requisitosObrigatorios = requisitosObrigatorios;
	}


	public String getRequisitosDesejaveis() {
		return requisitosDesejaveis;
	}


	public void setRequisitosDesejaveis(String requisitosDesejaveis) {
		this.requisitosDesejaveis = requisitosDesejaveis;
	}


	public String getRemuneracao() {
		return remuneracao;
	}


	public void setRemuneracao(String remuneracao) {
		this.remuneracao = remuneracao;
	}


	public String getBeneficio() {
		return beneficio;
	}


	public void setBeneficio(String beneficio) {
		this.beneficio = beneficio;
	}


	public String getLocalDeTrabalho() {
		return localDeTrabalho;
	}


	public void setLocalDeTrabalho(String localDeTrabalho) {
		this.localDeTrabalho = localDeTrabalho;
	}


	public int getAberta() {
		return aberta;
	}


	public void setAberta(int aberta) {
		this.aberta = aberta;
	}
	
	
	
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("\nId " + this.id);
		sb.append(" descrição: " + this.Descricao);
		sb.append(" requisitos obrigatorios: " + this.requisitosObrigatorios);
		sb.append(" requisitos desejaveis: " + this.requisitosDesejaveis);
		sb.append(" remuneração: " + this.remuneracao);
		sb.append(" beneficios: " + this.beneficio);
		sb.append(" local de trabalho: " + this.localDeTrabalho);
		sb.append(" aberta: " + this.aberta);
		return "" + sb;
	}
	
	
	
}
