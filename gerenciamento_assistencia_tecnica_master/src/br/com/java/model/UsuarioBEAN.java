package br.com.java.model;

import java.util.Date;

public class UsuarioBEAN extends PessoaBEAN{
	
	private CargoBEAN cargo;
	private String senha;
	private Date data_nascimento;
	
	public CargoBEAN getCargo() {
		
		if (cargo ==null) {
			cargo = new CargoBEAN();
		}
		return cargo;
	}
	public void setCargo(CargoBEAN cargo) {
		this.cargo = cargo;
	}
	public String getSenha() {
		return senha;
	}
	public void setSenha(String senha) {
		this.senha = senha;
	}
	public Date getData_nascimento() {
		return data_nascimento;
	}
	public void setData_nascimento(Date data_nascimento) {
		this.data_nascimento = data_nascimento;
	}
	@Override
	public String toString() {
		return String.format("%s[id=%d]", getClass().getSimpleName(), getId());
	}
	
}
