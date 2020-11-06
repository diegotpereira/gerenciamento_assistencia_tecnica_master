package br.com.java.controller;

import java.io.Serializable;
import java.util.ArrayList;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;

import br.com.java.dao.FabricanteDAO;
import br.com.java.model.FabricanteBEAN;
import br.com.java.util.FacesUtil;

@SuppressWarnings("serial")
@ManagedBean
@ViewScoped
public class FabricanteController implements Serializable {
	
	FabricanteBEAN fabricante = new FabricanteBEAN();
	FabricanteDAO DAO = new FabricanteDAO();
	
	public FabricanteBEAN getFabricante() {
		return fabricante;
	}
	public void setFabricante(FabricanteBEAN fabricante) {
		this.fabricante = fabricante;
	}
	public ArrayList<FabricanteBEAN> listarFabricantes() {
		return this.DAO.listarFabricantes();
	}
	public void novo() {
		this.fabricante = new FabricanteBEAN();
	}
	public void btnSalvar() {
		if (this.fabricante.getId() ==0) {
			this.cadastrarFabricante();
		}else {
			this.editarFabricante();
		}
	}
	public void cadastrarFabricante() {
		// TODO Auto-generated method stub
		if (this.DAO.cadastrarFabricante(this.fabricante)== true) {
			FacesUtil.adicionarMensagemInfo("Cadastro de fabricante efetuado com sucesso!!");
			this.novo();
		}else {
			FacesUtil.adicionarMensagemErro("Houve um erro ao tentar cadastrar fabricante!!");
		}
		
	}
	private void editarFabricante() {
		// TODO Auto-generated method stub
		if (this.DAO.editarFabricantes(this.fabricante)) {
			FacesUtil.adicionarMensagemInfo("Cadastro de fabricante alterado com sucesso!!");
		}else {
			FacesUtil.adicionarMensagemErro("Houve um erro ao tentar alterar o cadastro de fabricante!!");
		}
		
	}
	public void btnEditar(ActionEvent evt) {
		this.fabricante = (FabricanteBEAN) evt.getComponent().getAttributes().get("fabricanteSelecionado");
	}
	public void deletarFabricante(ActionEvent evt) {
		this.fabricante = (FabricanteBEAN) evt.getComponent().getAttributes().get("fabricanteSelecionado");
		
		if (this.DAO.deletarFabricante (this.fabricante) == true) {
			FacesUtil.adicionarMensagemInfo("Cadastro de fabricante excluído com sucesso!!");
		}else {
			FacesUtil.adicionarMensagemErro("Houve um erro ao tentar excluir o casdastro de fabricante!!");
		}
	}
}
