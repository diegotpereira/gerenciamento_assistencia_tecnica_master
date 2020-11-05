package br.com.java.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import br.com.java.factory.ConexaoBD;
import br.com.java.model.CargoBEAN;

public class CargoDAO {
	
	private ConexaoBD conexaobd;
	private Connection conexao = null;
	
	public CargoDAO() {
		
		this.conexaobd = new ConexaoBD();
	}
	
	public CargoBEAN getCargoPorID (int idCargo) {
		
		try {
			this.conexao = conexaobd.getConnection();
			CargoBEAN CB = new CargoBEAN();
			
			String SQL = "SELECT * FROM CARGOS WHERE id_cargo = ?";
			PreparedStatement PS = this.conexao.prepareStatement(SQL);
			PS.setInt(0, idCargo);
			
			ResultSet RES = PS.executeQuery();
			
			if (RES.next()) {
				CB.setId       (RES.getInt("id_cargo"));
				CB.setNome     (RES.getString("nome"));
				CB.setDescricao(RES.getString("descricao"));
				CB.setSalario  (RES.getFloat("salario"));
				
				return CB;
			}
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}finally {
			this.conexaobd.closeConnectio();
		}
		return null;
	}
	public ArrayList<CargoBEAN> listarCargos(){
		
		try {
			this.conexao = conexaobd.getConnection();
			ArrayList<CargoBEAN> listaCargos = new ArrayList<>();
			
			String SQL = "SELECT * FROM CARGOS";
			PreparedStatement PS = this.conexao.prepareStatement(SQL);
			ResultSet  RES = PS.executeQuery();
			
			while (RES.next()) {
				CargoBEAN CB = new CargoBEAN();
				CB.setId(RES.getInt("id_cargo"));
				CB.setNome(RES.getString("nome"));
				CB.setDescricao(RES.getString("descricao"));
				CB.setSalario(RES.getFloat("salario"));
				listaCargos.add(CB);
				
				return listaCargos;
			}
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}finally {
			this.conexaobd.closeConnectio();
		}
		return null;
		
	}

}
