package br.com.java.controller;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.servlet.http.HttpSession;

import br.com.java.dao.UsuarioDAO;
import br.com.java.model.UsuarioBEAN;
import br.com.java.util.FacesUtil;
import br.com.java.util.SessionUtil;

@SuppressWarnings("serial")
@ManagedBean
@SessionScoped
public class LoginController implements Serializable{
	
	private UsuarioBEAN user = new UsuarioBEAN();
	private UsuarioDAO dao = new UsuarioDAO();
	
	public UsuarioBEAN getUsuario() {
		return user;
		
	}
	public void setUsuario (UsuarioBEAN user) {
		this.user = user;
	}
	public String autenticarUsuario() {
		UsuarioBEAN usuarioAutenticado = this.dao.autenticarUsuario(this.user);
		if(usuarioAutenticado != null) {
			HttpSession sessao = SessionUtil.getSession();
			sessao.setAttribute("user", usuarioAutenticado);
			
			FacesUtil.adicionarMensagemInfo("Bem-vindo, "+usuarioAutenticado.getNome()+"!");
			
			return "Home.xhtml?faces-redirect=true";
		} else {
			FacesUtil.adicionarMensagemErro("Email ou senha inválido");
			return "Login.xhtml?faces-redirect=true";
		}
	}
	public String logout() {
		SessionUtil.invalidate();
		return "Login.xhtml?faces-redirect=true";
	}

}
