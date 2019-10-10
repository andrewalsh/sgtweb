package br.com.sgt.service.impl;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;

import br.com.sgt.entities.dto.UsuarioDTO;
import br.com.sgt.repository.api.UsuarioRepository;
import br.com.sgt.repository.filtro.FiltroUsuario;
import br.com.sgt.service.api.UsuarioService;

public class UsuarioBoundary implements UsuarioService, Serializable{

	
	private static final long serialVersionUID = 1L;
	
	@Inject
	private UsuarioRepository usuarioRepository;

	@Override
	public List<UsuarioDTO> usuarioLogado(FiltroUsuario filtroUsuario) {
		try {
			return usuarioRepository.buscarPorFiltro(filtroUsuario);
		} catch (RuntimeException e) {
			throw e;
		}
	}

	@Override
	public UsuarioDTO autenticar(FiltroUsuario filtroUsuario) {
		try {
			return usuarioRepository.login(filtroUsuario);
		} catch (RuntimeException e) {
			throw e;
		}
	}
}
