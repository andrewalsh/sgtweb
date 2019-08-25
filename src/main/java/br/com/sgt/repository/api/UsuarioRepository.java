package br.com.sgt.repository.api;

import java.util.List;

import br.com.sgt.entities.Usuario;
import br.com.sgt.repository.filtro.FiltroUsuario;

public interface UsuarioRepository {

	Usuario salavar(Usuario usuario);
	
	Usuario atualizar(Usuario usuario);
	
	List<Usuario> buscarPorFiltro(FiltroUsuario filtroUsuario);
	
	void excluir(Usuario usuario);
}
