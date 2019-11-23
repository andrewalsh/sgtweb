package br.com.sgt.helper;

import java.io.Serializable;

import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import br.com.sgt.entities.dto.UsuarioDTO;

public class UsuarioLogado implements Serializable{

	private static final long serialVersionUID = 1L;
	
	
	public UsuarioDTO exibeLogado() {
		FacesContext facesContext = FacesContext.getCurrentInstance();
		ExternalContext externalContext = facesContext.getExternalContext();
		HttpSession session = (HttpSession) externalContext.getSession(true);
		UsuarioDTO usuarioDTO = (UsuarioDTO) session.getAttribute("autenticacaoBean");
		return usuarioDTO;
	}
}
