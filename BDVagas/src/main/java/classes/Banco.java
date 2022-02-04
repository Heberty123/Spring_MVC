package classes;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class Banco {

	public Connection getConexao() {
		
		Connection conexao = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		}
		catch (ClassNotFoundException e) {
			System.out.println("Drive não encontrado!" + e);
		}
		
		try {
			String url = "jdbc:mysql://localhost:3306/BDVagas";
			String user = "root";
			String password = "Elokonamoda15";
			
			conexao = DriverManager.getConnection(url, user, password);
		}
		catch (Exception e) {
			System.out.println("Driver não encontrado! " + e);
		}
		
		return conexao;
	}
	
	
	public List<Vagas> listar(int aberta) {
		
		List<Vagas> lista = new ArrayList<>();
		try {
			Connection conn = getConexao();
			String sql = "SELECT * FROM vagas";
			if(aberta == 1)
				sql = sql + "WHERE aberta = 1";
			else if(aberta == 0)
				sql = sql + "WHERE aberta = 0";
			
			
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			
			
			while (rs.next()) {
				int id = rs.getInt(1);
				String descricao = rs.getString(2);
				String requisitosObrigatorios = rs.getString(3);
				String requisitosDesejaveis = rs.getString(4);
				String remuneracao = rs.getString(5);
				String beneficio = rs.getString(6);
				String localDeTrabalho = rs.getString(7);
				int abertura = rs.getInt(8);
				
				lista.add(new Vagas(id, descricao, requisitosObrigatorios, requisitosDesejaveis, remuneracao, beneficio, localDeTrabalho, abertura));
			}
			

		}
		catch(Exception e) {
			System.out.println("Erro: " + e);
		}
		return lista;
	}
}
