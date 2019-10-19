package br.com.sgt.service.impl;

import java.io.Serializable;
import java.util.Objects;

import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.servlet.http.HttpSession;

import br.com.sgt.entities.dto.UsuarioDTO;
import br.com.sgt.repository.api.UsuarioRepository;
import br.com.sgt.repository.filtro.FiltroUsuario;
import br.com.sgt.service.api.UsuarioService;

public class UsuarioBoundary implements UsuarioService, Serializable{

	
	private static final long serialVersionUID = 1L;
	
	@Inject
	private UsuarioRepository usuarioRepository;

	@Override
	public UsuarioDTO usuarioLogado() {
		try {
			FacesContext facesContext =  FacesContext.getCurrentInstance();
			ExternalContext externalContext = facesContext.getExternalContext();
			HttpSession session = (HttpSession) externalContext.getSession(true);
			return new UsuarioDTO((UsuarioDTO) session.getAttribute("autenticacaoBean"));
		} catch (RuntimeException e) {
			throw e;
		}
	}

	@Override
	public UsuarioDTO autenticar(FiltroUsuario filtroUsuario) {
		try {
			UsuarioDTO usuario = new UsuarioDTO();
			usuario = usuarioRepository.login(filtroUsuario);
			if(Objects.nonNull(usuario))
				return usuario;
			else
				throw new RuntimeException("CPF e/ou senha inválidos");
		} catch (RuntimeException e) {
			throw e;
		}
	}
}
