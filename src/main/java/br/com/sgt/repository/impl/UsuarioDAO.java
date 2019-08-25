package br.com.sgt.repository.impl;

import java.util.List;

import br.com.sgt.dao.DAO;
import br.com.sgt.entities.Usuario;
import br.com.sgt.repository.api.UsuarioRepository;
import br.com.sgt.repository.filtro.FiltroUsuario;

public class UsuarioDAO implements UsuarioRepository {
	
	private DAO<Usuario> dao;
	
	public UsuarioDAO() {
		dao = new DAO<Usuario>(Usuario.class);
	}

	public Usuario salavar(Usuario usuario) {
		
		return null;
	}

	public Usuario atualizar(Usuario usuario) {
		// TODO Auto-generated method stub
		return null;
	}

	public List<Usuario> buscarPorFiltro(FiltroUsuario filtroUsuario) {
		// TODO Auto-generated method stub
		return null;
	}

	public void excluir(Usuario usuario) {
		// TODO Auto-generated method stub

	}

}
