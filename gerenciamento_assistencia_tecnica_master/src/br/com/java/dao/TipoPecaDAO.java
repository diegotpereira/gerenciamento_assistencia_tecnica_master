package br.com.java.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import br.com.java.factory.ConexaoBD;
import br.com.java.model.TipoPecaBEAN;

public class TipoPecaDAO {
	
	private ConexaoBD conexaobd;
	private Connection conexao = null;
	
	public TipoPecaDAO() {
		this.conexaobd = new ConexaoBD();
	}
	public boolean cadastrarTipoPeca(TipoPecaBEAN TP) {
		
		try {
			this.conexao = this.conexaobd.getConnection();
			String sql = "INSERT INTO tipos_pecas (nome, descricao) VALUES (?,?)";
			PreparedStatement PS = this.conexao.prepareStatement(sql);
			
			PS.setString(1, TP.getNome());
			PS.setString(2, TP.getDescricao());
			
			PS.executeUpdate();	
			
			return true;
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}finally {
			this.conexaobd.closeConnectio();
		}
		return false;
		
	}
	public ArrayList<TipoPecaBEAN> listarTiposPecas() {
		
		try {
			this.conexao = this.conexaobd.getConnection();
			String SQL = "SELECT * FROM tipos_pecas";
			PreparedStatement PS = this.conexao.prepareStatement(SQL);
			
			ResultSet resultados = PS.executeQuery();	
			
			ArrayList<TipoPecaBEAN> listaTipoPecas = new ArrayList<>();
			
			while(resultados.next()) {
				TipoPecaBEAN TP = new TipoPecaBEAN();
				TP.setId(resultados.getInt("id_tipo_peca"));
				TP.setNome(resultados.getString("nome"));
				TP.setDescricao(resultados.getString("descricao"));
				
				listaTipoPecas.add(TP);
			}
			return listaTipoPecas;
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}finally {
			this.conexaobd.closeConnectio();
		}
		return null;
		
	}
	public boolean editarTipoPeca(TipoPecaBEAN TP) {
		
		try {
			this.conexao = this.conexaobd.getConnection();
			
			String SQL = "UPDATE tipos_pecas SET nome = ?, descricao = ? WHERE id_tipo_peca = ?";
			PreparedStatement PS = this.conexao.prepareStatement(SQL);
			
			PS.setString(1, TP.getNome());
			PS.setString(2, TP.getDescricao());
			PS.setInt   (3, TP.getId());
			
			PS.executeUpdate();
			return true;
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}finally {
			this.conexaobd.closeConnectio();
		}
		return false;
		
	}
	
	public boolean deletarTipoPeca(TipoPecaBEAN TP) {
		
		try {
			this.conexao = this.conexaobd.getConnection();
			String SQL = "DELETE FROM tipos_pecas WHERE id_tipo_peca = ?";
			PreparedStatement PS = this.conexao.prepareStatement(SQL);
			
			PS.setInt(1, TP.getId());
			
			PS.executeUpdate();
			return true;
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}finally {
			this.conexaobd.closeConnectio();
		}
		return false;
		
	}

}
