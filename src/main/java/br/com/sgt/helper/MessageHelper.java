package br.com.sgt.helper;

import java.io.Serializable;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

public class MessageHelper implements Serializable{

	
	private static final long serialVersionUID = 1L;
	
    public void notificacaoSucesso(String sucesso) {
		FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_INFO, null, sucesso);
        FacesContext facesContext = FacesContext.getCurrentInstance();
        facesContext.addMessage(null, facesMessage);
	}

    public void notificarErro(String erro) {
		FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, null,
				"Ocorreu um erro : "+erro);
        FacesContext facesContext = FacesContext.getCurrentInstance();
        facesContext.addMessage(null, facesMessage);
	}
}
