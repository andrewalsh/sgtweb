package br.com.sgt.service.api;

import br.com.sgt.entities.dto.UsuarioDTO;
import br.com.sgt.repository.filtro.FiltroUsuario;

public interface UsuarioService {

	UsuarioDTO usuarioLogado();
	
	UsuarioDTO autenticar(FiltroUsuario filtroUsuario);
}
