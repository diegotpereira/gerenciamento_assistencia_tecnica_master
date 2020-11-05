package br.com.java.util;

import javax.faces.event.PhaseEvent;
import javax.faces.event.PhaseId;
import javax.faces.event.PhaseListener;

import org.omnifaces.util.Faces;

import br.com.java.controller.LoginController;
import br.com.java.model.UsuarioBEAN;

@SuppressWarnings("serial")
public class AutenticacaoListener implements PhaseListener{

	@Override
	public void afterPhase(PhaseEvent arg0) {
		// TODO Auto-generated method stub
		String paginaAtual = Faces.getViewId();
		
		boolean isPaginaAutenticacao = paginaAtual.contains("Login.xhtml");
		
		if (isPaginaAutenticacao) {
              LoginController loginController = Faces.getSessionAttribute("loginController");
			
			if(loginController == null) {
				Faces.navigate("/pages/Login.xhtml");
				return; 
			}
			
			UsuarioBEAN usuario = (UsuarioBEAN) SessionUtil.getSession().getAttribute("usuario");
			if(usuario == null) {
				Faces.navigate("/pages/Login.xhtml");
				return; 
			}
		}
		
		
	}

	@Override
	public void beforePhase(PhaseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public PhaseId getPhaseId() {
		// TODO Auto-generated method stub
		return PhaseId.RESTORE_VIEW;
	}

}
