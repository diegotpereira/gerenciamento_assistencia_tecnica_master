package br.com.java.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import br.com.java.factory.ConexaoBD;
import br.com.java.model.FabricanteBEAN;

public class FabricanteDAO {
	
	private ConexaoBD conexaodb;
	private Connection conexao = null;
	
	public FabricanteDAO() {
		this.conexaodb = new ConexaoBD();
	}
	public boolean cadastrarFabricante(FabricanteBEAN fab) {
		
		try {
			this.conexao = this.conexaodb.getConnection();
			String SQL = "INSERT INTO FABRICANTES (nome, email, telefone, site) VALUES (?,?,?,?";
			PreparedStatement PS = this.conexao.prepareStatement(SQL);
			
			PS.setString(1, fab.getNome());
			PS.setString(2, fab.getEmail());
			PS.setString(3, fab.getTelefone());
			PS.setString(4, fab.getSite());
			PS.executeUpdate();
			
			return true;
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}finally {
			this.conexaodb.closeConnectio();
		}
		return false;
		
	}
	public ArrayList<FabricanteBEAN> listarFabricantes(){
		
		try {
			this.conexao = this.conexaodb.getConnection();
			String SQL = "SELECT * FROM FABRICANTES";
			PreparedStatement PS = this.conexao.prepareStatement(SQL);
			ResultSet RS = PS.executeQuery();
			
			ArrayList<FabricanteBEAN> listaFabricantes = new ArrayList<>();
			
			while (RS.next()) {
				FabricanteBEAN fab = new FabricanteBEAN();
				fab.setId      (RS.getInt("id_fabricante"));
				fab.setNome    (RS.getString("nome"));
				fab.setEmail   (RS.getString("email"));
				fab.setTelefone(RS.getString("telefone"));
				fab.setSite    (RS.getString("site"));
				
				listaFabricantes.add(fab);
			}
			
			return listaFabricantes;
			
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}finally {
			this.conexaodb.closeConnectio();
		}
		return null;
	}
	public boolean editarFabricantes (FabricanteBEAN fab) {
		
		try {
			this.conexao = this.conexaodb.getConnection();
			String SQL = "UPDATE fabricantes SET nome = ?, email = ?, telefone = ?, site = ? WHERE id_fabricante = ?";
			PreparedStatement PS = this.conexao.prepareStatement(SQL);
			
			PS.setString(1, fab.getNome());
			PS.setString(2, fab.getEmail());
			PS.setString(3, fab.getTelefone());
			PS.setString(4, fab.getSite());
			PS.setInt   (5, fab.getId());
			
			PS.executeUpdate();
			return true;
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}finally {
			this.conexaodb.closeConnectio();
		}
		return false;
	}
	public boolean deletarFabricante(FabricanteBEAN FAB) {
		// TODO Auto-generated method stub
		try {
			this.conexao = this.conexaodb.getConnection();
			String SQL = "DELETE FROM FABRICANTES WHERE id_fabricante = ?";
			PreparedStatement PS = this.conexao.prepareStatement(SQL);
			
			PS.setInt(1, FAB.getId());
			
			PS.executeUpdate();
			return true;
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}finally {
			this.conexaodb.closeConnectio();
		}
		
		return false;
	}
}
