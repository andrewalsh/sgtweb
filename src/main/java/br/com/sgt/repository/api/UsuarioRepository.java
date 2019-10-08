package br.com.sgt.repository.api;

import java.util.List;

import br.com.sgt.entities.Usuario;
import br.com.sgt.entities.dto.UsuarioDTO;
import br.com.sgt.repository.filtro.FiltroUsuario;

public interface UsuarioRepository {

	Usuario salavar(Usuario usuario);
	
	Usuario atualizar(Usuario usuario);
	
	List<UsuarioDTO> buscarPorFiltro(FiltroUsuario filtroUsuario);
	
	UsuarioDTO login(FiltroUsuario filtroUsuario);
	
	void excluir(Usuario usuario);
}
