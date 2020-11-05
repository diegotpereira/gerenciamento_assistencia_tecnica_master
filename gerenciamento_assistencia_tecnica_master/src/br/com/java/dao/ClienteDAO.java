package br.com.java.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import br.com.java.factory.ConexaoBD;
import br.com.java.model.ClienteBEAN;
import br.com.java.model.EnderecoBEAN;

public class ClienteDAO {
	
	private ConexaoBD conexaobd;
	private Connection conexao = null;
	
	public ClienteDAO() {
		this.conexaobd = new ConexaoBD();
	}
	
	public boolean cadastrarCliente(ClienteBEAN cliente) {
		EnderecoDAO dao = new EnderecoDAO();
		int idEndereco = dao.cadastrarEndereco(cliente.getEndereco());
		
		if (idEndereco != 0) {
			try {
				this.conexao = this.conexaobd.getConnection();
				
				String SQL = "INSERT INTO CLIENTES (nome, cpf, email, telefone_celular, telefone_fixo, endereco_id) VALUES (?,?,?,?,?,?)";
				PreparedStatement PS = this.conexao.prepareStatement(SQL);
				
				PS.setString(1, cliente.getNome());
				PS.setString(2, cliente.getCpf());
				PS.setString(3, cliente.getEmail());
				PS.setString(4, cliente.getTelefone_celular());
				PS.setString(5, cliente.getTelefone_fixo());
				PS.setInt   (6, idEndereco);
				
				PS.executeUpdate();
				
				return true;
			} catch (SQLException e) {
				// TODO: handle exception
				e.printStackTrace();
			}finally {
				this.conexaobd.closeConnectio();
			}
		}
		return false;
	}
	public ClienteBEAN getClientePorID (int id_cliente) throws SQLException {
		this.conexao = this.conexaobd.getConnection();
		String SQL = "SELECT * FROM CLIENTES, enderecos WHERE id_cliente = ? and endereco_id = id_endereco;";
		PreparedStatement PS = this.conexao.prepareStatement(SQL);
		
		PS.setInt(1, id_cliente);
		
		ResultSet RES = PS.executeQuery();
		
		ClienteBEAN cliente = new ClienteBEAN();
		
		if (RES.next()) {
			cliente.setId(RES.getInt("id_cliente"));
			cliente.setNome(RES.getString("nome"));
			cliente.setCpf(RES.getString("cpf"));
			cliente.setEmail(RES.getString("email"));
			cliente.setTelefone_celular(RES.getString("telefone_celular"));
			cliente.setTelefone_fixo(RES.getString("telefone_fixo"));
			cliente.setData_cadastro(RES.getDate("data_cadastro"));
			
			EnderecoBEAN END = new EnderecoBEAN();
			END.setId(RES.getInt("id_endereco"));
			END.setCep(RES.getString("cep"));
			END.setEstado(RES.getString("estado"));
			END.setCidade(RES.getString("cidade"));
			END.setLogradouro(RES.getString("logradouro"));
			END.setBairro(RES.getString("bairro"));
			END.setComplemento(RES.getString("complemento"));
			END.setNumero_propriedade(RES.getInt("numero_propriedade"));
			
			cliente.setEndereco(END);
		}
		
		this.conexaobd.closeConnectio();
		return cliente;
	}
	public ArrayList<ClienteBEAN> listarClientes(){
		
		try {
			this.conexao = this.conexaobd.getConnection();
			String SQL = "SELECT * FROM CLIENTES as c,enderecos as e WHERE c.endereco_id = e.id_endereco;";
			PreparedStatement PS = this.conexao.prepareStatement(SQL);
			ResultSet RES = PS.executeQuery();
			
			ArrayList<ClienteBEAN> listarClientes = new ArrayList<>();
			
			while (RES.next()) {
				ClienteBEAN cli = new ClienteBEAN();
				cli.setId(RES.getInt("id_cliente"));
				cli.setNome(RES.getString("nome"));
				cli.setCpf(RES.getString("cpf"));
				cli.setEmail(RES.getString("email"));
				cli.setTelefone_celular(RES.getString("telefone_celular"));
				cli.setTelefone_fixo(RES.getString("telefone_fixo"));
				cli.setData_cadastro(RES.getDate("data_cadastro"));
				
				EnderecoBEAN END = new EnderecoBEAN();
				END.setId(RES.getInt("id_endereco"));
				END.setCep(RES.getString("cep"));
				END.setEstado(RES.getString("estado"));
				END.setCidade(RES.getString("cidade"));
				END.setLogradouro(RES.getString("logradouro"));
				END.setBairro(RES.getString("bairro"));
				END.setComplemento(RES.getString("complemento"));
				END.setNumero_propriedade(RES.getInt("numero_propriedade"));
				
				listarClientes.add(cli);
				
				cli.setEndereco(END);
			}
			
			return listarClientes;
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}finally {
			this.conexaobd.closeConnectio();
		}
		return null;
	}
	public boolean deletarCliente (ClienteBEAN cli) {
		
		try {
			this.conexao = this.conexaobd.getConnection();
			
			String SQL = "DELETE FROM CLEINTES WHERE id_cliente = ?";
			PreparedStatement PS = this.conexao.prepareStatement(SQL);
			
			PS.setInt(1, cli.getId());
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
	public boolean editarCliente(ClienteBEAN cli) {
		
		this.conexao = this.conexaobd.getConnection();
		EnderecoDAO dao = new EnderecoDAO();
		
		try {
			dao.editarEndereco(cli.getEndereco());
			
			String SQL = "UPDATE CLIENTES SET nome = ?, cpf = ?, email = ?, telefone_celular = ?, telefone_fixo = ? WHERE id_cliente = ?";
			PreparedStatement PS = this.conexao.prepareStatement(SQL);
			
			PS.setString(1, cli.getNome());
			PS.setString(2, cli.getCpf());
			PS.setString(3, cli.getEmail());
			PS.setString(4, cli.getTelefone_celular());
			PS.setString(5, cli.getTelefone_fixo());
			PS.setInt   (6, cli.getId());
			
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
