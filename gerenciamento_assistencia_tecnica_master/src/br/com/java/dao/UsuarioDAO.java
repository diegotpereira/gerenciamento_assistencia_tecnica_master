package br.com.java.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import br.com.java.factory.ConexaoBD;
import br.com.java.model.CargoBEAN;
import br.com.java.model.EnderecoBEAN;
import br.com.java.model.UsuarioBEAN;

public class UsuarioDAO {
	
	private ConexaoBD conexaobd;
	private Connection conexao = null;
	
	public UsuarioDAO() {
		this.conexaobd = new ConexaoBD();
	}
	public UsuarioBEAN autenticarUsuario (UsuarioBEAN usuario) {
		
		try {
			this.conexao = this.conexaobd.getConnection();
			String SQL = "SELECT u.id_usuario, u.nome as nome_usuario, u.cpf, u.data_nascimento, u.telefone_celular, u.telefone_fixo, u.email, u.senha, u.cargo_id, u.endereco_id, u.data_cadastro, c.id_cargo, c.nome as nome_cargo, c.descricao, c.salario, e.id_endereco, e.cep, e.estado, e.cidade, e.logradouro, e.bairro, e.complemento, e.numero_propriedade FROM USUARIOS as u, cargos as c, enderecos as e WHERE u.email = ? and u.senha = ? and u.cargo_id = c.id_cargo and u.endereco_id = e.id_endereco";
			PreparedStatement PS = this.conexao.prepareStatement(SQL);
			
			PS.setString(1, usuario.getEmail());
			PS.setString(2, usuario.getSenha());
			ResultSet RES = PS.executeQuery();
			
			if (RES.next()) {
				usuario.setId              (RES.getInt("id_usuario"));
				usuario.setNome            (RES.getString("nome_usuario"));
				usuario.setCpf             (RES.getString("cpf"));
				usuario.setData_nascimento (RES.getDate("data_nascimento"));
				usuario.setTelefone_celular(RES.getString("telefone_celular"));
				usuario.setTelefone_fixo   (RES.getString("telefone_fixo"));
				usuario.setEmail           (RES.getString("email"));
				usuario.setData_cadastro   (RES.getDate("data_cadastro"));
				
				CargoBEAN cargo = new CargoBEAN();
				cargo.setId(RES.getInt("id_cargo"));
				cargo.setNome(RES.getString("nome_cargo"));
				cargo.setDescricao(RES.getString("descricao"));
				cargo.setSalario(RES.getFloat("salario"));
				usuario.setCargo(cargo);
				
				EnderecoBEAN endereco = new EnderecoBEAN();
				endereco.setId                (RES.getInt("id_endereco"));
				endereco.setCep               (RES.getString("cep"));
				endereco.setEstado            (RES.getString("estado"));
				endereco.setCidade            (RES.getString("cidade"));
				endereco.setLogradouro        (RES.getString("logradouro"));
				endereco.setBairro            (RES.getString("bairro"));
				endereco.setComplemento       (RES.getString("complemento"));
				endereco.setNumero_propriedade(RES.getInt("numero_propriedade"));
				usuario.setEndereco(endereco);
				
				return usuario;
			}
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}finally {
			this.conexaobd.closeConnectio();
		}
		return null;
	}
	public boolean cadastrarUsuario(UsuarioBEAN usuario) {
		this.conexao = this.conexaobd.getConnection();
		
		EnderecoDAO dao = new EnderecoDAO();
		int idEndereco = dao.cadastrarEndereco(usuario.getEndereco());
		
		if (idEndereco != 0) {
			try {
				String SQL = "INSERT INTO USUARIOS (nome, cpf, data_nascimento, telefone_celular, telefone_fixo, email, senha, cargo_id, endereco_id) VALUES (?,?,?,?,?,?,?,?,?)";
				PreparedStatement PS = this.conexao.prepareStatement(SQL);
				
				PS.setString(1, usuario.getNome());
				PS.setString(2, usuario.getCpf());
				PS.setDate  (3, new java.sql.Date(usuario.getData_nascimento().getTime()));
				PS.setString(4, usuario.getTelefone_celular());
				PS.setString(5, usuario.getTelefone_fixo());
				PS.setString(6, usuario.getEmail());
				PS.setString(7, usuario.getSenha());
				PS.setInt   (8, usuario.getCargo().getId());
				PS.setInt   (9, idEndereco);
				
				PS.executeUpdate();
				
				return true;
			} catch (SQLException e) {
				// TODO: handle exception
				e.printStackTrace();
				dao.deletarEndereco(idEndereco);
			}finally {
				this.conexaobd.closeConnectio();
			}
		}
		return false;
		
	}
	public UsuarioBEAN getUsuarioPorID(int id_usuario) throws SQLException {
		this.conexao = this.conexaobd.getConnection();
		
		String SQL = "SELECT u.id_usuario, u.nome as nome_usuario, u.cpf, u.data_nascimento, u.telefone_celular, u.telefone_fixo, u.email, u.senha, u.cargo_id, u.endereco_id, u.data_cadastro, c.id_cargo, c.nome as nome_cargo, c.descricao, c.salario, e.id_endereco, e.cep, e.estado, e.cidade, e.logradouro, e.bairro, e.complemento, e.numero_propriedade FROM usuarios as u, cargos as c, enderecos as e WHERE id_usuario = ? and u.cargo_id = c.id_cargo and u.endereco_id = e.id_endereco;";
		PreparedStatement PS = this.conexao.prepareStatement(SQL);
		
		PS.setInt(1, id_usuario);
		
		ResultSet RES = PS.executeQuery();
		
		UsuarioBEAN usuario = new UsuarioBEAN();
		
		if (RES.next()) {
			usuario.setId(RES.getInt("id_usuario"));
			usuario.setNome(RES.getString("nome_usuario"));
			usuario.setCpf(RES.getString("cpf"));
			usuario.setData_nascimento(RES.getDate("data_nascimento"));
			usuario.setTelefone_celular(RES.getString("telefone_celular"));
			usuario.setTelefone_fixo(RES.getString("telefone_fixo"));
			usuario.setEmail(RES.getString("email"));
			usuario.setSenha(RES.getString("senha"));
			usuario.setData_cadastro(RES.getDate("data_cadastro"));
			
			CargoBEAN CARG = new CargoBEAN();
			CARG.setId       (RES.getInt("id_cargo"));
			CARG.setNome     (RES.getString("nome_cargo"));
			CARG.setDescricao(RES.getString("descricao"));
			CARG.setSalario  (RES.getFloat("salario"));
			usuario.setCargo    (CARG);
			
			EnderecoBEAN endereco = new EnderecoBEAN();
			endereco.setId				 (RES.getInt("id_endereco"));
			endereco.setCep    		     (RES.getString("cep"));
			endereco.setEstado			 (RES.getString("estado"));
			endereco.setCidade			 (RES.getString("cidade"));
			endereco.setLogradouro		 (RES.getString("logradouro"));
			endereco.setBairro			 (RES.getString("bairro"));
			endereco.setComplemento       (RES.getString("complemento"));
			endereco.setNumero_propriedade(RES.getInt("numero_propriedade"));
    		usuario.setEndereco         (endereco);
		}
		
		this.conexaobd.closeConnectio();
		return usuario;
	}
	public ArrayList<UsuarioBEAN> listarUsuarios() {
		
		try {
			this.conexao = this.conexaobd.getConnection();
			
			String SQL = "SELECT u.id_usuario, u.nome as nome_usuario, u.cpf, u.data_nascimento, u.telefone_celular, u.telefone_fixo, u.email, u.senha, u.cargo_id, u.endereco_id, u.data_cadastro, c.id_cargo, c.nome as nome_cargo, c.descricao, c.salario, e.id_endereco, e.cep, e.estado, e.cidade, e.logradouro, e.bairro, e.complemento, e.numero_propriedade FROM usuarios as u, cargos as c, enderecos as e WHERE u.cargo_id = c.id_cargo and u.endereco_id = e.id_endereco;";
			PreparedStatement PS = this.conexao.prepareStatement(SQL);
			ResultSet RES = PS.executeQuery();
			
			ArrayList<UsuarioBEAN> listaUsuarios = new ArrayList<>();
			
			while (RES.next()) {
				UsuarioBEAN user = new UsuarioBEAN();
				user.setId              (RES.getInt("id_usuario"));
				user.setNome            (RES.getString("nome_usuario"));
				user.setCpf             (RES.getString("cpf"));
				user.setData_nascimento (RES.getDate("data_nascimento"));
				user.setTelefone_celular(RES.getString("telefone_celular"));
				user.setTelefone_fixo   (RES.getString("telefone_fixo"));
				user.setEmail           (RES.getString("email"));
				user.setSenha           (RES.getString("senha"));
				user.setData_cadastro   (RES.getDate("data_cadastro"));
				
				CargoBEAN CARG = new CargoBEAN();
				CARG.setId       (RES.getInt("id_cargo"));
				CARG.setNome     (RES.getString("nome_cargo"));
				CARG.setDescricao(RES.getString("descricao"));
				CARG.setSalario  (RES.getFloat("salario"));
				user.setCargo(CARG);
				
				EnderecoBEAN END = new EnderecoBEAN();
				END.setId    			 (RES.getInt("id_endereco"));
				END.setCep				 (RES.getString("cep"));
				END.setEstado			 (RES.getString("estado"));
				END.setCidade			 (RES.getString("cidade"));
				END.setLogradouro		 (RES.getString("logradouro"));
				END.setBairro			 (RES.getString("bairro"));
				END.setComplemento       (RES.getString("complemento"));
				END.setNumero_propriedade(RES.getInt("numero_propriedade"));
				user.setEndereco         (END);
				
				listaUsuarios.add(user);
			}
			
			return listaUsuarios;
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}finally {
			this.conexaobd.closeConnectio();
		}
		return null;
		
	}
	 public boolean deletarUsuario(UsuarioBEAN user) {
		 
		 
		 
		 
		 try {
			 this.conexao = this.conexaobd.getConnection();
			 
			 String SQL = "DELETE FROM usuarios WHERE id_usuario = ?";
			 PreparedStatement PS = this.conexao.prepareStatement(SQL);
			 PS.setInt(1, user.getId());
			 PS.executeUpdate();
			 
			 EnderecoDAO dao = new EnderecoDAO();
			 
			 dao.deletarEndereco(user.getEndereco().getId());
			 
			 return true;
			
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}finally {
			this.conexaobd.closeConnectio();
		}
		return false;
		
	 }
	 public boolean editarUsuario(UsuarioBEAN user) {
		 
		 this.conexao = this.conexaobd.getConnection();
		 
		 EnderecoDAO dao = new EnderecoDAO();
		 
		 try {
			dao.editarEndereco(user.getEndereco());
			
			String SQL = "UPDATE usuarios SET nome = ?, cpf = ?, data_nascimento = ?, telefone_celular = ?, telefone_fixo = ?, email = ?, senha = ? WHERE id_usuario = ?;";
			PreparedStatement PS = this.conexao.prepareStatement(SQL);
			
			PS.setString(1, user.getNome());
			PS.setString(2, user.getCpf());
			PS.setDate  (3, new java.sql.Date(user.getData_nascimento().getTime()));
			PS.setString(4, user.getTelefone_celular());
			PS.setString(5, user.getTelefone_fixo());
			PS.setString(6, user.getEmail());
			PS.setString(7, user.getSenha());
			PS.setInt   (8, user.getId());
			
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
