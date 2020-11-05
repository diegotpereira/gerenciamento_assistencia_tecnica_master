package br.com.java.controller;

import java.io.Serializable;
import java.util.ArrayList;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;

import br.com.java.dao.ClienteDAO;
import br.com.java.model.ClienteBEAN;
import br.com.java.util.FacesUtil;

@SuppressWarnings("serial")
@ManagedBean
@ViewScoped	
public class ClienteController implements Serializable{
	
	private ClienteBEAN cli = new ClienteBEAN();
	private ClienteDAO dao = new ClienteDAO();
	
	public ClienteBEAN getCliente() {
		return cli;
	}
	
	public void setCliente(ClienteBEAN cli) {
		this.cli = cli;
	}
	public ArrayList<ClienteBEAN> listarClientes(){
		return this.dao.listarClientes();
		
	}
	public void novoCliente() {
		this.cli = new ClienteBEAN();
	}
	
	public void btnSalvar() {
		
		if (this.cli.getId() == 0) {
			this.cadastrarCliente();
		}else {
			this.editarCliente();
		}
	}
	private boolean validarCamposCustom() {
		
		boolean validaAprovada = true;
		
		if (this.cli.getTelefone_celular().equals("") && this.cli.getTelefone_fixo().equals("")) {
			
			FacesUtil.adicionarMensagemErro("Você precisa informar pelo menos um número de telefone");
			validaAprovada = false;
			
		}
		return validaAprovada;
		
	}
	
	private void cadastrarCliente() {
		// TODO Auto-generated method stub
		if (this.dao.cadastrarCliente(this.cli) == true) {
			FacesUtil.adicionarMensagemInfo("Cliente cadastrado com sucesso!!!");
			novoCliente();
		}else {
			FacesUtil.adicionarMensagemErro("Houve um erro ao cadastrar novo cliente");
		}
	}

	private void editarCliente() {
		// TODO Auto-generated method stub
		if (this.validarCamposCustom() == true) {
			if (this.dao.editarCliente(this.cli) == true) {
				FacesUtil.adicionarMensagemInfo("Cliente alterado com sucesso!!");
			}else {
				FacesUtil.adicionarMensagemErro("Houve um erro ao alterar cadastro de cliente!!");
			}
		}
		
	}
	
	public void btnEditar(ActionEvent evento) {
		this.cli =  (ClienteBEAN) evento.getComponent().getAttributes().get("clienteSelecionado");
	}
	public void deletarCliente(ActionEvent evento) {
		
		this.cli = (ClienteBEAN) evento.getComponent().getAttributes().get("clienteSelecionado");
		
		if (this.dao.deletarCliente(this.cli) == true) {
			FacesUtil.adicionarMensagemInfo("Cliente excluído com sucesso!");
		}else {
			FacesUtil.adicionarMensagemErro("Houve um erro ao tentar excluir cadastro de cliente!!");
		}
	}

	
}
