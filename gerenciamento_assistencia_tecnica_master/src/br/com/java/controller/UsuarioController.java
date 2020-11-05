package br.com.java.controller;

import java.util.ArrayList;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import br.com.java.dao.CargoDAO;
import br.com.java.dao.UsuarioDAO;
import br.com.java.model.CargoBEAN;
import br.com.java.model.UsuarioBEAN;
import br.com.java.util.FacesUtil;

@ManagedBean
@ViewScoped
public class UsuarioController {
	
    private UsuarioBEAN USER = new UsuarioBEAN();
	private UsuarioDAO USERDAO = new UsuarioDAO();
	private CargoDAO CARGODAO = new CargoDAO();
	
	@PostConstruct
	public void init() {
		UsuarioBEAN usuarioRecebido = (UsuarioBEAN) FacesContext.getCurrentInstance().getExternalContext().getFlash().get("USER"); 
		if(usuarioRecebido != null) {
			this.USER = usuarioRecebido;
		}
	}
	public UsuarioBEAN getUsuario() {
		return USER;
	}

	public void setUsuario(UsuarioBEAN USER) {
		this.USER = USER;
	}
	public ArrayList<CargoBEAN> listarCargos() {
		return this.CARGODAO.listarCargos();
	}
	
	public ArrayList<UsuarioBEAN> listarUsuarios(){
		return this.USERDAO.listarUsuarios();
	}
	
	private boolean validarCamposCustom() {
	
		boolean validacaoAprovada = true;
		if(this.USER.getTelefone_celular().equals("") && this.USER.getTelefone_fixo().equals("")) {
			FacesUtil.adicionarMensagemErro("Você precisa preencher pelo menos um campo de telefone");
			validacaoAprovada = false;
		}
		
		return validacaoAprovada;
	}
	
	public String cadastrarUsuario() {
		if(this.validarCamposCustom() == true) {	//Se passar na validação
			if(this.USERDAO.cadastrarUsuario(this.USER) == true) {
				FacesUtil.adicionarMensagemInfo("Usuário cadastrado com sucesso!");
				return("UsuarioListar.xhtml?faces-redirect=true");
			}else {
				FacesUtil.adicionarMensagemErro("Houve um erro ao tentar cadastrar o usuário. O cpf informado já esteja cadastrado.");				
			}
		}
		
		return "";
	}
	public void excluirUsuario(ActionEvent evento) {
		this.USER = (UsuarioBEAN) evento.getComponent().getAttributes().get("usuarioSelecionado");
		
		if(this.USERDAO.deletarUsuario(this.USER) == true) {
			FacesUtil.adicionarMensagemInfo("Usuário excluido com sucesso.");
		} else {
			FacesUtil.adicionarMensagemErro("Houve um erro ao tentar excluir o usuário.");
		}
	}
	public String editarClicado(UsuarioBEAN usuarioSelecionado) {
	
		FacesContext.getCurrentInstance().getExternalContext().getFlash().put("usuario", usuarioSelecionado); 
		return "UsuarioEditar.xhtml?faces-redirect=true";	
	}
	public String editarUsuario() {		
		if(this.validarCamposCustom() == true) {
			if(this.USERDAO.editarUsuario(this.USER) == true) {
				FacesUtil.adicionarMensagemInfo("Usuário editado com sucesso!");
				return("UsuarioListar.xhtml?faces-redirect=true");
			}else {
				FacesUtil.adicionarMensagemErro("Houve um erro ao tentar editar o usuário.");
			}
		}
		
		return "";
	}

}
