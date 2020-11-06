package br.com.java.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import br.com.java.factory.ConexaoBD;
import br.com.java.model.FabricanteBEAN;
import br.com.java.model.PecaBEAN;
import br.com.java.model.TipoPecaBEAN;

public class PecaDAO {
	private ConexaoBD conexaobd;
	private Connection conexao = null;
	
	public PecaDAO() {
		this.conexaobd = new ConexaoBD();
	}
	public boolean cadastrarPeca(PecaBEAN peca) {
		
		try {
			this.conexao = this.conexaobd.getConnection();
			String SQL = "INSERT INTO PECAS (nome, preco, descricao, quantidade, tipo_peca_id, fabricante_id) VALUES (?, ?, ?, ?, ?, ?)";
			PreparedStatement PS = this.conexao.prepareStatement(SQL);
			
			PS.setString(1, peca.getNome());
			PS.setFloat(2, peca.getPreco());
			PS.setString(3, peca.getDescricao());
			PS.setInt(4, peca.getQuantidade());
			PS.setInt(5, peca.getTipoPeca().getId());
			PS.setInt(6, peca.getFabricante().getId());
			
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
	public ArrayList<PecaBEAN> listarPecas(){
		
		try {
			this.conexao = this.conexaobd.getConnection();
			String SQL = "SELECT p.id_peca, p.nome as nome_peca, p.preco as preco_peca, p.descricao as descricao_peca, p.quantidade as quantidade_peca, tp.id_tipo_peca, tp.nome as nome_tipo_peca, tp.descricao as descricao_tipo_peca,  f.id_fabricante, f.nome as nome_fabricante, f.email as email_fabricante, f.telefone as telefone_fabricante, f.site as site_fabricante FROM pecas as p, tipos_pecas as tp, fabricantes as f WHERE p.tipo_peca_id = tp.id_tipo_peca and p.fabricante_id = f.id_fabricante;";
			PreparedStatement PS = this.conexao.prepareStatement(SQL);
			
			ResultSet RES = PS.executeQuery();
			
			ArrayList<PecaBEAN> listaPecas = new ArrayList<>();
			
			while (RES.next()) {
				PecaBEAN peca = new PecaBEAN();
				peca.setId        (RES.getInt("id_peca"));
				peca.setNome      (RES.getString("nome_peca"));
				peca.setPreco     (RES.getFloat("preco_peca"));
				peca.setDescricao (RES.getString("descricao_peca"));
				peca.setQuantidade(RES.getInt("quantidade_peca"));
				
				TipoPecaBEAN tipoPeca = new TipoPecaBEAN();
				tipoPeca.setId(RES.getInt("id_tipo_peca"));
				tipoPeca.setNome(RES.getString("nome_tipo_peca"));
				tipoPeca.setDescricao(RES.getString("descricao_tipo_peca"));
				
				peca.setTipoPeca(tipoPeca);
				
				FabricanteBEAN FAB = new FabricanteBEAN();
				FAB.setId      (RES.getInt("id_fabricante"));
				FAB.setNome    (RES.getString("nome_fabricante"));
				FAB.setEmail   (RES.getString("email_fabricante"));
				FAB.setTelefone(RES.getString("telefone_fabricante"));
				FAB.setSite    (RES.getString("site_fabricante"));
				
				peca.setFabricante(FAB);
				
				listaPecas.add(peca);
			}
			return listaPecas;
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}finally {
			this.conexaobd.closeConnectio();
		}
		return null;
		
	}
	public boolean editarPeca(PecaBEAN peca) {
		
		try {
			this.conexao = this.conexaobd.getConnection();
			String SQL = "UPDATE pecas SET nome = ?, preco = ?, descricao = ?, quantidade = ?, tipo_peca_id = ?, fabricante_id = ? WHERE id_peca = ?";
			PreparedStatement PS = this.conexao.prepareStatement(SQL);
			
			PS.setString(1, peca.getNome());
			PS.setFloat (2, peca.getPreco());
			PS.setString(3, peca.getDescricao());
			PS.setInt   (4, peca.getQuantidade());
			PS.setInt   (5, peca.getTipoPeca().getId());
			PS.setInt   (6, peca.getFabricante().getId());
			PS.setInt   (7, peca.getId());
			
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
	public boolean deletarPeca(PecaBEAN peca) {
		
		try {
			this.conexao = this.conexaobd.getConnection();
			String SQL = "DELETE FROM pecas WHERE id_peca = ?";
			PreparedStatement PS = this.conexao.prepareStatement(SQL);
			
			PS.setInt(1, peca.getId());
			
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
