package br.com.sgt.infra.autenticacao;

import java.io.IOException;
import java.util.Objects;

import javax.faces.component.UIViewRoot;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.event.PhaseEvent;
import javax.faces.event.PhaseId;
import javax.faces.event.PhaseListener;
import javax.servlet.http.HttpSession;

import br.com.sgt.entities.dto.UsuarioDTO;

public class AutenticacaoPhaseListener implements PhaseListener{

	private static final long serialVersionUID = 1L;

	@Override
	public void afterPhase(PhaseEvent event) {
		FacesContext facesContext = event.getFacesContext();
		UIViewRoot uiViewRoot = facesContext.getViewRoot();
		String paginaAtual = uiViewRoot.getViewId();
		
		boolean ehPaginaAutenticacao = paginaAtual.contains("pages/login.xhtml");
		
		if(!ehPaginaAutenticacao) {
			ExternalContext externalContext = facesContext.getExternalContext();
			HttpSession session = (HttpSession) externalContext.getSession(true);
			UsuarioDTO usuario = (UsuarioDTO) session.getAttribute("autenticacaoBean");
			
			if(Objects.isNull(usuario)) {
				try {
					externalContext.redirect(externalContext.getRequestContextPath() + "/pages/login.xhtml");
					throw new RuntimeException("Usuário ou senha inválidos");
				} catch (IOException e) {
					throw new RuntimeException(e.getMessage());
				}
			}
		}
	}

	@Override
	public void beforePhase(PhaseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public PhaseId getPhaseId() {
		return PhaseId.RESTORE_VIEW;
	}

}
