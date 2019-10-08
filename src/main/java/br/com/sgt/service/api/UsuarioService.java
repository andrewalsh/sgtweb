package br.com.sgt.service.api;

import java.util.List;

import br.com.sgt.entities.dto.UsuarioDTO;
import br.com.sgt.repository.filtro.FiltroUsuario;

public interface UsuarioService {

	List<UsuarioDTO> usuarioLogado(FiltroUsuario filtroUsuario);
}
