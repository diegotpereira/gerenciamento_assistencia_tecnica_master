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
	public boolean cadastrarFabricante(FabricanteBEAN fabricante) {
		    
		   this.conexao = this.conexaodb.getConnection();
		
		try {
			
			String SQL = "INSERT INTO fabricantes (nome, email, telefone, site) VALUES (?,?,?,?)";
			PreparedStatement PS = this.conexao.prepareStatement(SQL);
			
			PS.setString(1, fabricante.getNome());
			
			PS.setString(2, fabricante.getEmail());
			PS.setString(3, fabricante.getTelefone());
			PS.setString(4, fabricante.getSite());
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
				FabricanteBEAN fabricante = new FabricanteBEAN();
				fabricante.setId      (RS.getInt("id_fabricante"));
				fabricante.setNome    (RS.getString("nome"));
				fabricante.setEmail   (RS.getString("email"));
				fabricante.setTelefone(RS.getString("telefone"));
				fabricante.setSite    (RS.getString("site"));
				
				listaFabricantes.add(fabricante);
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
	public boolean editarFabricantes (FabricanteBEAN fabricante) {
		
		try {
			this.conexao = this.conexaodb.getConnection();
			String SQL = "UPDATE fabricantes SET nome = ?, email = ?, telefone = ?, site = ? WHERE id_fabricante = ?";
			PreparedStatement PS = this.conexao.prepareStatement(SQL);
			
			PS.setString(1, fabricante.getNome());
			PS.setString(2, fabricante.getEmail());
			PS.setString(3, fabricante.getTelefone());
			PS.setString(4, fabricante.getSite());
			PS.setInt   (5, fabricante.getId());
			
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
