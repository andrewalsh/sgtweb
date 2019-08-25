package br.com.sgt.repository.impl;

import java.util.List;

import br.com.sgt.dao.DAO;
import br.com.sgt.entities.ValorAutorizado;
import br.com.sgt.repository.api.ValorAutorizadoRepository;
import br.com.sgt.repository.filtro.FiltroValorAutorizado;

public class ValorAutorizadoDAO implements ValorAutorizadoRepository {
	
	private DAO<ValorAutorizado> dao;
	

	public ValorAutorizadoDAO() {
		dao = new DAO<ValorAutorizado>(ValorAutorizado.class);
	}

	public ValorAutorizado salavar(ValorAutorizado valorAutorizado) {
		ValorAutorizado toReturn = new ValorAutorizado();
		try {
			toReturn = dao.adiciona(valorAutorizado);
		} catch (RuntimeException e) {
			throw e;
		}
		return toReturn;
	}

	public ValorAutorizado atualizar(ValorAutorizado valorAutorizado) {
		ValorAutorizado toReturn = new ValorAutorizado();
		try {
			toReturn = dao.atualizar(valorAutorizado);
		} catch (RuntimeException e) {
			throw e;
		}
		return toReturn;
	}
	
	public ValorAutorizado buscaPorId(Long id) {
		ValorAutorizado toReturn = new ValorAutorizado();
		try {
			toReturn = dao.buscaPorId(id);
		} catch (RuntimeException e) {
			throw e;
		}
		return toReturn;
	}

	public List<ValorAutorizado> buscarPorFiltro(FiltroValorAutorizado filtroValorAutorizado) {
		// TODO Auto-generated method stub
		return null;
	}

	public void excluir(ValorAutorizado valorAutorizado) {
		// TODO Auto-generated method stub

	}

}
