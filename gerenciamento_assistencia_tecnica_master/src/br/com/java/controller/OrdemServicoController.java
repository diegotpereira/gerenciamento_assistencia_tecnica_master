package br.com.java.controller;

import java.io.Serializable;
import java.util.ArrayList;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;

import br.com.java.dao.ClienteDAO;
import br.com.java.dao.OrdemServicoDAO;
import br.com.java.dao.PecaDAO;
import br.com.java.dao.UsuarioDAO;
import br.com.java.model.ClienteBEAN;
import br.com.java.model.OrdemServicoBEAN;
import br.com.java.model.PecaBEAN;
import br.com.java.model.UsuarioBEAN;
import br.com.java.util.FacesUtil;

@SuppressWarnings("serial")
@ManagedBean
@ViewScoped
public class OrdemServicoController implements Serializable{
	
    private OrdemServicoBEAN OS   = new OrdemServicoBEAN();
	private OrdemServicoDAO OSDAO = new OrdemServicoDAO();
	private ClienteDAO cliDAO     = new ClienteDAO();
	private UsuarioDAO userDAO    = new UsuarioDAO();
	private PecaDAO pecaDao       = new PecaDAO();
	
	public OrdemServicoBEAN getOrdemServico() {
		return OS;
	}

	public void setOrdemServico(OrdemServicoBEAN ordemServico) {
		this.OS = OS;
	}
	
	public ArrayList<OrdemServicoBEAN> listarOrdensServicos(){
		return this.OSDAO.listarOrdensServicos();
	}
	
	public ArrayList<ClienteBEAN> listarClientes(){
		return this.cliDAO.listarClientes();
	}
	
	public ArrayList<UsuarioBEAN> listarUsuarios(){
		return this.userDAO.listarUsuarios();
	}
	
	public ArrayList<PecaBEAN> listarPecas(){
		return this.pecaDao.listarPecas();
	}
	
	public void novaOS() {
		this.OS = new OrdemServicoBEAN();
	}
	public void btnSalvar() {
		if (this.OS.getId() == 0) {
			this.cadastarOrdemServico();
		}else {
			this.editarOrdemServico();
		}
	}

	private void cadastarOrdemServico() {
		// TODO Auto-generated method stub
		if (this.OSDAO.cadastrarOrdemServico(this.OS)) {
			FacesUtil.adicionarMensagemInfo("Ordem de serviço cadastrada com sucesso!!");
		}else {
			FacesUtil.adicionarMensagemErro("Houve um erro ap tentar cadastrar a ordem de serviço!!");
		}
	}

	private void editarOrdemServico() {
		// TODO Auto-generated method stub
		if (this.OSDAO.editarOrdemServico(this.OS) == true) {
			FacesUtil.adicionarMensagemInfo("Ordem de serviço alterada com sucesso!!");
		}else {
			FacesUtil.adicionarMensagemErro("Houve um erro ao tentar alterar a ordem de serviço!!");
		}
	}
	public void btnEditar( ActionEvent evt) {
		this.OS = (OrdemServicoBEAN) evt.getComponent().getAttributes().get("ordemServicoSelecionada");
	}
	public void deletarOrdemServico(ActionEvent evt) {
		this.OS = (OrdemServicoBEAN) evt.getComponent().getAttributes().get("ordemServicoSelecionada");
		if (this.OSDAO.deletarOrdemServico(this.OS) == true) {
			FacesUtil.adicionarMensagemInfo("Ordem de serviço excluída com sucesso");
		}else {
			FacesUtil.adicionarMensagemErro("Houve um erro ao tentar  excluir a ordem de serviço!!");
		}
	}
	public void btnAtualizar (ActionEvent evt) {
		this.OS = (OrdemServicoBEAN) evt.getComponent().getAttributes().get("ordemServicoSelecionada");
	}
	public void atualizaOrdemServico() {
		if (this.OSDAO.atualizarOrdemServico(this.OS) == true) {
			FacesUtil.adicionarMensagemInfo("Ordem de serviço atualizada com sucesso!!");
		}else {
			FacesUtil.adicionarMensagemErro("Houve um erro ao tentar atualizar a ordem de serviço!!");
		}
	}

}
