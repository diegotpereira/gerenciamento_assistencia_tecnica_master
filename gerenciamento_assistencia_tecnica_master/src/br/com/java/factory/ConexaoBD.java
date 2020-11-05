package br.com.java.factory;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConexaoBD {
	
	private static final String driver = "com.mysql.cj.jdbc.Driver";
	private static final String caminho = "jdbc:mysql://localhost:3306/db_assistencia_tecnica?useSSL=false&serverTimezone=UTC&useLegacyDatetimeCode=false";
	private static final String usuario = "root";
	private static final String senha = "root";	
	private static Connection conexao = null;
	
	public static Connection getConnection() {
		
		try {
			Class.forName(driver);
			conexao = DriverManager.getConnection(caminho,usuario,senha);
		} catch (Exception e) {
			// TODO: handle exception
			throw new RuntimeException ("Erro ao tentar se conectar ao banco de dados:, e");
		}
		return conexao;
	}
	
	public static void closeConnectio() {
		
		if (conexao != null) {
			try {
				conexao.close();
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		}
	}

}
