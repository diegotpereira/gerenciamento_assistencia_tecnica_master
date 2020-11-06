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
				EnderecoBEAN endereco = new EnderecoBEAN();
				endereco.setId(RES.getInt("id_endereco"));
				endereco.setCep(RES.getString("cep"));
				endereco.setEstado(RES.getString("estado"));
				endereco.setCidade(RES.getString("cidade"));
				endereco.setLogradouro(RES.getString("logradouro"));
				endereco.setBairro(RES.getString("bairro"));
				endereco.setComplemento(RES.getString("complemento"));
				endereco.setNumero_propriedade(RES.getInt("numero_propriedade"));
				
				return endereco;
			}
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
			
		}finally {
			this.conexaobd.closeConnectio();
		}
		return null;
	}
	
	public int cadastrarEndereco (EnderecoBEAN endereco) {
		int idEndereco = 0;
		
		try {
			conexao = this.conexaobd.getConnection();
			
			String SQL = "INSERT INTO ENDERECOS (cep, estado, cidade, logradouro, bairro, complemento, numero_propriedade) VALUES (?,?,?,?,?,?,?)";
			PreparedStatement PS = this.conexao.prepareStatement(SQL, Statement.RETURN_GENERATED_KEYS);
			PS.setString(1, endereco.getCep());
			PS.setString(2, endereco.getEstado());
			PS.setString(3, endereco.getCidade());
			PS.setString(4, endereco.getLogradouro());
			PS.setString(5, endereco.getBairro());
			PS.setString(6, endereco.getComplemento());
			PS.setInt   (7, endereco.getNumero_propriedade());
			
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
