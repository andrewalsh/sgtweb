package br.com.sgt.repository.impl;

import java.util.List;

import br.com.sgt.dao.DAO;
import br.com.sgt.entities.TipoTarifa;
import br.com.sgt.repository.api.TipoTarifaRepository;
import br.com.sgt.repository.filtro.FiltroTipoTarifa;

public class TipoTarifaDAO implements TipoTarifaRepository {
	
	private DAO<TipoTarifa> dao;

	public TipoTarifaDAO() {
		this.dao = new DAO<TipoTarifa>(TipoTarifa.class);
	}

	public TipoTarifa salavar(TipoTarifa tipoTarifa) {
		TipoTarifa toReturn = new TipoTarifa();
		try {
			toReturn = dao.adiciona(tipoTarifa);
		} catch (RuntimeException e) {
			throw e;
		}
		return toReturn;
	}

	public TipoTarifa atualizar(TipoTarifa tipoTarifa) {
		TipoTarifa toReturn = new TipoTarifa();
		try {
			toReturn = dao.atualizar(tipoTarifa);
		} catch (RuntimeException e) {
			throw e;
		}
		return toReturn;
	}
	
	public TipoTarifa buscarPorId(Long id) {
		TipoTarifa toReturn = new TipoTarifa();
		try {
			toReturn = dao.buscaPorId(id);
		} catch (RuntimeException e) {
			throw e;
		}
		return toReturn;
	}

	public List<TipoTarifa> buscarPorFiltro(FiltroTipoTarifa filtroTipoTarifa) {
		// TODO Auto-generated method stub
		return null;
	}

	public void excluir(TipoTarifa tipoTarifa) {
		// TODO Auto-generated method stub

	}

}
