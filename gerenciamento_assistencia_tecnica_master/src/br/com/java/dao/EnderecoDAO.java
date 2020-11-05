package br.com.java.dao;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import br.com.java.factory.ConexaoBD;
import br.com.java.model.EnderecoBEAN;

public class EnderecoDAO {
	
	private ConexaoBD conexaobd;
	private Connection conexao = null;
	
	public EnderecoDAO() {
		
		this.conexaobd = new ConexaoBD();
	}
	
	public EnderecoBEAN getEnderecoPorID(int idEndereco) {
		
		try {
			this.conexao = this.conexaobd.getConnection();
			
			String SQL = "SELECT * FROM ENDERECOS WHERE id_endereco = ?";
			PreparedStatement PS = this.conexao.prepareStatement(SQL);
			PS.setInt (0, idEndereco);
			ResultSet RES = PS.executeQuery();
			
			if (RES.next()) {
				EnderecoBEAN END = new EnderecoBEAN();
				END.setId(RES.getInt("id_endereco"));
				END.setCep(RES.getString("cep"));
				END.setEstado(RES.getString("estado"));
				END.setCidade(RES.getString("cidade"));
				END.setLogradouro(RES.getString("logradouro"));
				END.setBairro(RES.getString("bairro"));
				END.setComplemento(RES.getString("complemento"));
				END.setNumero_propriedade(RES.getInt("numero_propriedade"));
				
				return END;
			}
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
			
		}finally {
			this.conexaobd.closeConnectio();
		}
		return null;
	}
	
	public int cadastrarEndereco (EnderecoBEAN END) {
		int idEndereco = 0;
		
		try {
			conexao = this.conexaobd.getConnection();
			
			String SQL = "INSERT INTO ENDERECOS (cep, estado, cidade, logradouro, bairro, complemento, numero_propriedade) VALUES (?,?,?,?,?,?,?)";
			PreparedStatement PS = this.conexao.prepareStatement(SQL, Statement.RETURN_GENERATED_KEYS);
			PS.setString(1, END.getCep());
			PS.setString(2, END.getEstado());
			PS.setString(3, END.getCidade());
			PS.setString(4, END.getLogradouro());
			PS.setString(5, END.getBairro());
			PS.setString(6, END.getComplemento());
			PS.setInt   (7, END.getNumero_propriedade());
			
			PS.executeUpdate();	
			
			final ResultSet RS = PS.getGeneratedKeys();	
			
			if (RS.next()) {
				idEndereco = (int) RS.getLong(1);
			}
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
			
		}finally {
				this.conexaobd.closeConnectio();
		}
		return idEndereco;
	}
	public boolean deletarEndereco(int idEndereco) {
		boolean executeInstrucao = false;
		
		try {
			conexao = this.conexaobd.getConnection();
			
			String SQL = "DELETE FROM ENDERECOS WHERE id_endereco = ?";
			PreparedStatement PS = this.conexao.prepareStatement(SQL);
			PS.setInt(1, idEndereco);
			PS.executeUpdate();
			
			executeInstrucao = true;
			
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
			
			
				
			}finally {
				this.conexaobd.closeConnectio();
		}
		return executeInstrucao;
		
	}
	public void editarEndereco(EnderecoBEAN END) throws SQLException {
		
		this.conexao = this.conexaobd.getConnection();
		
		String SQL = "UPDATE enderecos SET cep = ?, estado = ?, cidade = ?, logradouro = ?, bairro = ?, complemento = ?, numero_propriedade = ? WHERE id_endereco = ?";
		PreparedStatement PS = this.conexao.prepareStatement(SQL);
		
		PS.setString(1, END.getCep());
		PS.setString(2, END.getEstado());
		PS.setString(3, END.getCidade());
		PS.setString(4, END.getLogradouro());
		PS.setString(5, END.getBairro());
		PS.setString(6, END.getComplemento());
		PS.setInt   (7, END.getNumero_propriedade());
		PS.setInt   (8, END.getId());
		
		PS.executeUpdate();
		
		this.conexaobd.closeConnectio();
	}

}
