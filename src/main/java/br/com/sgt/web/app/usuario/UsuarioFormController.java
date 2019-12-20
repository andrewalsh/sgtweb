package br.com.sgt.web.app.usuario;

import java.io.IOException;
import java.io.Serializable;
import java.util.Objects;

import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.NoResultException;
import javax.servlet.http.HttpSession;

import br.com.sgt.entities.dto.UsuarioDTO;
import br.com.sgt.helper.MessageHelper;
import br.com.sgt.helper.UsuarioLogado;
import br.com.sgt.repository.filtro.FiltroUsuario;
import br.com.sgt.service.api.UsuarioService;

@Named("usuarioFormController")
@ViewScoped
public class UsuarioFormController implements Serializable{

	private static final long serialVersionUID = 1L;

	private UsuarioDTO usuarioDTO;
	
	private FiltroUsuario filtroUsuario = new FiltroUsuario();
	
	@Inject
	private UsuarioLogado usuarioLogado;
	
	@Inject
	private MessageHelper helper;

	
	@Inject
	private UsuarioService usuarioService;
	
	private static final String CPF_SENHA_INVALIDO = "CPF e/ou senha inválidos";
	
	public void autenticar() {
		try {
			usuarioDTO = usuarioService.autenticar(filtroUsuario);
			if (Objects.isNull(usuarioDTO)) {
				throw new RuntimeException("CPF e/ou senha inválidos");
			} else {
				FacesContext facesContext = FacesContext.getCurrentInstance();
				ExternalContext externalContext = facesContext.getExternalContext();
				HttpSession session = (HttpSession) facesContext.getExternalContext().getSession(false);
				session.setAttribute("autenticacaoBean", usuarioDTO);
				externalContext.redirect(externalContext.getRequestContextPath() + "/public/index.xhtml?faces-redirect=true");
				//return "/public/index.xhtml=?faces-redirect=true";
			}
		} catch (RuntimeException | IOException e) {
			if(e instanceof NoResultException) {
				helper.notificarErro(CPF_SENHA_INVALIDO);
				//mensagemErro(CPF_SENHA_INVALIDO);
			}
			else
				helper.notificarErro(e.getMessage());
				//mensagemErro(e.getMessage());
		}
	}
	
	public void encerraSessao() {
		try {
			FacesContext facesContext = FacesContext.getCurrentInstance();
			ExternalContext externalContext = facesContext.getExternalContext();
			HttpSession session = (HttpSession) facesContext.getExternalContext().getSession(false);
			session.setAttribute("autenticacaoBean", null);
			externalContext.redirect(externalContext.getRequestContextPath() + "/pages/login.xhtml");
			session.invalidate();
		} catch (IOException e) {
			throw new RuntimeException(e.getMessage());
			
		}
	}
	
	public void exibeLogado() {
		/*FacesContext facesContext = FacesContext.getCurrentInstance();
		ExternalContext externalContext = facesContext.getExternalContext();
		HttpSession session = (HttpSession) externalContext.getSession(true);
		UsuarioDTO usuarioDTO = (UsuarioDTO) session.getAttribute("autenticacaoBean");*/
		this.usuarioDTO = usuarioLogado.exibeLogado();
	}
	
	/*private void mensagemErro(String msg) {
		FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_FATAL, null, msg);
        FacesContext facesContext = FacesContext.getCurrentInstance();
        facesContext.addMessage(null, facesMessage);
	}*/
	

	public UsuarioDTO getUsuarioDTO() {
		return usuarioDTO;
	}

	public void setUsuarioDTO(UsuarioDTO usuarioDTO) {
		this.usuarioDTO = usuarioDTO;
	}

	public FiltroUsuario getFiltroUsuario() {
		return filtroUsuario;
	}

	public void setFiltroUsuario(FiltroUsuario filtroUsuario) {
		this.filtroUsuario = filtroUsuario;
	}
}
