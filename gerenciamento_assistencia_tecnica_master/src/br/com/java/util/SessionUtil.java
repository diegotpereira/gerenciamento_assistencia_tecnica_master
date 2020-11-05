package br.com.java.util;

import java.io.Serializable;

import javax.servlet.http.HttpSession;

import javax.faces.context.FacesContext;

public class SessionUtil implements Serializable{
	
	public static HttpSession getSession() {
		FacesContext contexto = FacesContext.getCurrentInstance();
		HttpSession sessao = (HttpSession) contexto.getExternalContext().getSession(false);
		return sessao;
	}
	
	public static void invalidate() {	//Encerra a sessão
		getSession().invalidate();
	}

}
