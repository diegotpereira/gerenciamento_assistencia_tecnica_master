package br.com.java.controller;

import java.io.Serializable;
import java.util.ArrayList;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;

import br.com.java.dao.FabricanteDAO;
import br.com.java.dao.PecaDAO;
import br.com.java.dao.TipoPecaDAO;
import br.com.java.model.FabricanteBEAN;
import br.com.java.model.PecaBEAN;
import br.com.java.model.TipoPecaBEAN;
import br.com.java.util.FacesUtil;

@SuppressWarnings("serial")
@ManagedBean
@ViewScoped
public class PecaController implements Serializable {
	
	private PecaBEAN PC = new PecaBEAN();
	private PecaDAO PCDAO = new PecaDAO();
	private TipoPecaDAO TPDAO = new TipoPecaDAO();
	private FabricanteDAO FABDAO = new FabricanteDAO();
	
	public PecaBEAN getPeca() {
		return PC;
	}
	public void setPeca(PecaBEAN PC) {
		this.PC = PC;
	}
	public void novaPeca() {
		this.PC = new PecaBEAN();
	}
	public ArrayList<PecaBEAN> listarPecas(){
		return this.PCDAO.listarPecas();
		
	}
	public ArrayList<TipoPecaBEAN> listarTiposPecas(){	
		return this.TPDAO.listarTiposPecas();
	}
	public ArrayList<FabricanteBEAN> listarFabricantes(){
		return this.FABDAO.listarFabricantes();
	}
	public void cadastrarPeca() {
		if (this.PCDAO.cadastrarPeca(PC) == true) {
			FacesUtil.adicionarMensagemInfo("Material cadastrado com sucesso!!");
		}else {
			FacesUtil.adicionarMensagemErro("Houve um erro ao tentar cadastrar o material!!");
		}
	}
	public void btnSalvar() {
		if (this.PC.getId()==0) {
			this.cadastrarPeca();
		}else {
			this.editarPeca();
		}
	}
	private void editarPeca() {
		// TODO Auto-generated method stub
		if (this.PCDAO.editarPeca(this.PC)== true) {
			FacesUtil.adicionarMensagemInfo("Cadastro do material alterado com sucesso!!");
		}else {
			FacesUtil.adicionarMensagemErro("Houve um erro ao tentar alterar o cadastro do material!!");
		}
	}
	public void btnEditar(ActionEvent evt) {
		this.PC = (PecaBEAN) evt.getComponent().getAttributes().get("pecaSelecionada");
	}
	public void deletarPeca(ActionEvent evt) {
		this.PC = (PecaBEAN)evt.getComponent().getAttributes().get("pecaSelecionada");
		
		if (this.PCDAO.deletarPeca(this.PC)==true) {
			FacesUtil.adicionarMensagemInfo("Material excluído com sucesso!!");
		}else {
			FacesUtil.adicionarMensagemErro("Houve um erro ao tentar excluir o material!!");
		}
	}

}
