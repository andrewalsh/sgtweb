package br.com.sgt.repository.impl;

import java.util.List;

import br.com.sgt.dao.DAO;
import br.com.sgt.entities.Socio;
import br.com.sgt.repository.api.SocioRepository;
import br.com.sgt.repository.filtro.FiltroSocio;

public class SocioDAO implements SocioRepository{
	
	private DAO<Socio> dao;
	
	public SocioDAO() {
		dao = new DAO<Socio>(Socio.class);
	}

	public Socio salvar(Socio socio) {
		Socio toReturn = new Socio();
		try {
			toReturn = dao.adiciona(socio);
		} catch (RuntimeException e) {
			throw e;
		}
		return toReturn;
	}

	public Socio atualizar(Socio socio) {
		Socio toReturn = new Socio();
		try {
			toReturn = dao.atualizar(socio);
		} catch (RuntimeException e) {
			throw e;
		}
		return toReturn;
	}

	public Socio buscarPorId(Long id) {
		Socio toReturn = new Socio();
		try {
			toReturn = dao.buscaPorId(id);
		} catch (RuntimeException e) {
			throw e;
		}
		return toReturn;
	}

	public List<Socio> buscarPorFiltro(FiltroSocio filtroSocio) {
		// TODO Auto-generated method stub
		return null;
	}

	public void excluir(Socio socio) {
		// TODO Auto-generated method stub
		
	}

	
}
