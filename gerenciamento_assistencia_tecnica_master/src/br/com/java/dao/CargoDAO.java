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
			CargoBEAN cargo = new CargoBEAN();
			
			String SQL = "SELECT * FROM CARGOS WHERE id_cargo = ?";
			PreparedStatement PS = this.conexao.prepareStatement(SQL);
			PS.setInt(0, idCargo);
			
			ResultSet RES = PS.executeQuery();
			
			if (RES.next()) {
				cargo.setId       (RES.getInt("id_cargo"));
				cargo.setNome     (RES.getString("nome"));
				cargo.setDescricao(RES.getString("descricao"));
				cargo.setSalario  (RES.getFloat("salario"));
				
				return cargo;
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
				CargoBEAN cargo = new CargoBEAN();
				cargo.setId(RES.getInt("id_cargo"));
				cargo.setNome(RES.getString("nome"));
				cargo.setDescricao(RES.getString("descricao"));
				cargo.setSalario(RES.getFloat("salario"));
				listaCargos.add(cargo);
				
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
