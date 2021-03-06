package br.com.sgt.repository.impl;

import java.util.List;

import br.com.sgt.dao.DAO;
import br.com.sgt.entities.Tarifa;
import br.com.sgt.repository.api.TarifaRepository;
import br.com.sgt.repository.filtro.FiltroTarifa;

public class TarifaDAO implements TarifaRepository {

	private DAO<Tarifa> dao;
	
	
	public TarifaDAO() {
		dao = new DAO<Tarifa>(Tarifa.class);
	}

	public Tarifa salavar(Tarifa tarifa) {
		Tarifa toReturn = new Tarifa();
		try {
			toReturn = dao.adiciona(tarifa);
		} catch (RuntimeException e) {
			throw e;
		}
		return toReturn;
	}

	public Tarifa atualizar(Tarifa tarifa) {
		Tarifa toReturn = new Tarifa();
		try {
			toReturn = dao.atualizar(tarifa);
		} catch (RuntimeException e) {
			throw e;
		}
		return toReturn;
	}
	
	public Tarifa buscaPorId(Long id) {
		Tarifa toReturn = new Tarifa();
		try {
			toReturn = dao.buscaPorId(id);
		} catch (RuntimeException e) {
			throw e;
		}
		return toReturn;
	}

	public List<Tarifa> buscarPorFiltro(FiltroTarifa filtroTarifa) {
		// TODO Auto-generated method stub
		return null;
	}

	public void excluir(Tarifa tarifa) {
		// TODO Auto-generated method stub
		
	}
	
	

}
