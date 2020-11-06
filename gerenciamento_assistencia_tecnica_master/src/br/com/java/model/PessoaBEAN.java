package br.com.java.model;

import java.util.Date;

public abstract class PessoaBEAN {
	
	private int id;
	private String nome;
	private String cpf;
	private String email;
	private String telefone_celular;
	private String telefone_fixo;
	private EnderecoBEAN endereco;
	private Date data_cadastro;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getCpf() {
		return cpf;
	}
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getTelefone_celular() {
		return telefone_celular;
	}
	public void setTelefone_celular(String telefone_celular) {
		this.telefone_celular = telefone_celular;
	}
	public String getTelefone_fixo() {
		return telefone_fixo;
	}
	public void setTelefone_fixo(String telefone_fixo) {
		this.telefone_fixo = telefone_fixo;
	}
	public EnderecoBEAN getEndereco() {
		
		if (endereco == null) {
			endereco = new EnderecoBEAN();
		}
		return endereco;
	}
	public void setEndereco(EnderecoBEAN endereco) {
		this.endereco = endereco;
	}
	public Date getData_cadastro() {
		return data_cadastro;
	}
	public void setData_cadastro(Date data_cadastro) {
		this.data_cadastro = data_cadastro;
	}
}
