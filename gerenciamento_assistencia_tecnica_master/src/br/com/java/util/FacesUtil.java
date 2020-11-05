package br.com.java.util;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

public class FacesUtil {
	
	public static void adicionarMensagemInfo(String mensagem) {
		FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_INFO, mensagem, mensagem);
		
		FacesContext facesContext = FacesContext.getCurrentInstance();
		facesContext.addMessage(null, facesMessage);
		facesContext.getExternalContext().getFlash().setKeepMessages(true);	

	} 
	
	public static void adicionarMensagemErro(String mensagem) {
		FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, mensagem, mensagem);	
		
		FacesContext facesContext = FacesContext.getCurrentInstance();	
		facesContext.addMessage(null, facesMessage);
	}

}
