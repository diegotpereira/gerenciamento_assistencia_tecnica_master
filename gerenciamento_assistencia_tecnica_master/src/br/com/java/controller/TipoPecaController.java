package br.com.java.controller;

import java.io.Serializable;
import java.util.ArrayList;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;

import br.com.java.dao.TipoPecaDAO;
import br.com.java.model.TipoPecaBEAN;
import br.com.java.util.FacesUtil;

@SuppressWarnings("serial")
@ManagedBean
@ViewScoped
public class TipoPecaController implements Serializable {
    private TipoPecaBEAN TP;
	private TipoPecaDAO TPDAO = new TipoPecaDAO();
	
	public TipoPecaBEAN getTipoPeca() {
		return TP;
	}

	public void setTipoPeca(TipoPecaBEAN TP) {
		this.TP = TP;
	}
	public void novoTipoPeca() {
		this.TP = new TipoPecaBEAN();
	}
	public ArrayList<TipoPecaBEAN> listarTiposPecas(){
		return this.TPDAO.listarTiposPecas();
	}
	public void cadastrarTipoPeca() {
		if(this.TPDAO.cadastrarTipoPeca(this.TP) == true) {
			FacesUtil.adicionarMensagemInfo("Tipo de peça cadastrado com sucesso!");
			this.novoTipoPeca();
		} else {
			FacesUtil.adicionarMensagemErro("Houve um erro ao tentar cadastrar o tipo de paça.");
		}
	}
	public void btnSalvar() {
		if(this.TP.getId() == 0) {	
			this.cadastrarTipoPeca();
		} else {
			this.editarTipoPeca();
		}
	}

	private void editarTipoPeca() {
		// TODO Auto-generated method stub
		if (this.TPDAO.editarTipoPeca(this.TP)==true) {
			FacesUtil.adicionarMensagemInfo("O tipo de peça foi alterado com sucesso!!");
		}else {
			FacesUtil.adicionarMensagemErro("Houve um erro ao tentar alterar o tipo de peça!!");
		}
	}
	public void btnEditarTipoPeca(ActionEvent evt) {
		this.TP = (TipoPecaBEAN) evt.getComponent().getAttributes().get("tipoPecaSelecionado");
	}
	
	public void deletarTipoPeca(ActionEvent evt) {
		this.TP = (TipoPecaBEAN) evt.getComponent().getAttributes().get("tipoPecaSelecionado");
		if (this.TPDAO.deletarTipoPeca(this.TP)== true) {
			FacesUtil.adicionarMensagemInfo("Tipo de peça excluída com sucesso");
		}else {
			FacesUtil.adicionarMensagemInfo("Houve um erro ao tentar excluir o tipo de peça!!");
		}
	}

}
