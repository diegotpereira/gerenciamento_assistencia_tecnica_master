package br.com.java.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import br.com.java.factory.ConexaoBD;
import br.com.java.model.OrdemServicoBEAN;

public class OrdemServicoDAO {
	
	private ConexaoBD conexaobd;
	private Connection conexao = null;
	
	public OrdemServicoDAO() {
		this.conexaobd = new ConexaoBD();
	}
	
	public boolean cadastrarOrdemServico(OrdemServicoBEAN OS ) {
		try {
			this.conexao = this.conexaobd.getConnection();
			String SQL = "INSERT INTO ordens_servicos (status, cliente_id, descricao_problema, usuario_id) VALUES (?, ?, ?, ?)";
			PreparedStatement PS = this.conexao.prepareStatement(SQL);
			
			PS.setString(1, OS.getStatus());	
			PS.setInt   (2, OS.getCliente().getId());
			PS.setString(3, OS.getDescricaoProblema());
			PS.setInt   (4, OS.getUsuario().getId());
			
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
	public boolean editarOrdemServico(OrdemServicoBEAN OS) {
		
		try {
			this.conexao = this.conexaobd.getConnection();
			String SQL = "UPDATE ordens_servicos SET status = ?, cliente_id = ?, descricao_problema = ?, usuario_id = ? WHERE id_ordem_servico = ?";
			PreparedStatement PS = this.conexao.prepareStatement(SQL);
			
			PS.setString(1, OS.getStatus());
			PS.setInt   (2, OS.getCliente().getId());
			PS.setString(3, OS.getDescricaoProblema());
			PS.setInt   (4, OS.getUsuario().getId());
			PS.setInt   (5, OS.getId());
			
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
	public boolean atualizarOrdemServico(OrdemServicoBEAN OS) {
		
		try {
			this.conexao = this.conexaobd.getConnection();
			String SQL = "UPDATE ordens_servicos SET status = ?, descricao_problema = ?, descricao_solucao = ?, pecas_usadas = ? WHERE id_ordem_servico = ?";
			PreparedStatement PS = this.conexao.prepareStatement(SQL);
			
			PS.setString(1, OS.getStatus());
			PS.setString(2, OS.getDescricaoProblema());
			PS.setString(3, OS.getDescricaoSolucao());
			PS.setString(4, OS.pecas_usadas_toString());
			PS.setInt   (5, OS.getId());
			
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
	public ArrayList<OrdemServicoBEAN> listarOrdensServicos() {
		try {
			this.conexao = this.conexaobd.getConnection();
			String SQL = "SELECT * FROM ordens_servicos";
			PreparedStatement PS = this.conexao.prepareStatement(SQL);
			ResultSet RES = PS.executeQuery();
			
			ArrayList<OrdemServicoBEAN> listaOrdensServico = new ArrayList<>();
			
			while (RES.next()) {
				OrdemServicoBEAN OS = new OrdemServicoBEAN();
				OS.setId(RES.getInt("id_ordem_servico"));
				OS.setStatus(RES.getString("status"));
				OS.setDescricaoProblema(RES.getString("descricao_problema"));
				OS.setDataRegistro(RES.getDate("data_registro"));
				OS.setDescricaoSolucao(RES.getString("descricao_solucao"));
				
				if (RES.getString("pecas_usadas") != null) {
					String[] pecas_usadas = RES.getString("pecas_usadas").split(",");
					OS.setPecas_usadas(pecas_usadas);
				}
				
				ClienteDAO dao = new ClienteDAO();
				OS.setCliente(dao.getClientePorID(RES.getInt("cliente_id")));
				
				UsuarioDAO usuarioDAO = new UsuarioDAO();
				OS.setUsuario(usuarioDAO.getUsuarioPorID(RES.getInt("usuario_id")));
				
				listaOrdensServico.add(OS);
			}
			return listaOrdensServico;
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}finally {
			this.conexaobd.closeConnectio();
		}
		return null;
	}
	public boolean deletarOrdemServico(OrdemServicoBEAN OS) {
		
		try {
			this.conexao = this.conexaobd.getConnection();
			String SQL = "DELETE FROM ordens_servicos WHERE id_ordem_servico = ?";
			PreparedStatement PS = this.conexao.prepareStatement(SQL);
			
			PS.setInt(1, OS.getId());
			
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
