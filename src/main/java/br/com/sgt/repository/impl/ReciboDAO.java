package br.com.sgt.repository.impl;

import java.util.List;

import br.com.sgt.dao.DAO;
import br.com.sgt.entities.Recibo;
import br.com.sgt.repository.api.ReciboRepository;
import br.com.sgt.repository.filtro.FiltroRecibo;

public class ReciboDAO implements ReciboRepository {
	
	private DAO<Recibo> dao;
	
	public ReciboDAO() {
		dao = new DAO<Recibo>(Recibo.class);
	}

	public Recibo salavar(Recibo recibo) {
		Recibo toReturn = new Recibo();
		try {
			toReturn = dao.adiciona(recibo);
		} catch (RuntimeException e) {
			throw e;
		}
		return toReturn;
	}

	public Recibo atualizar(Recibo recibo) {
		Recibo toReturn = new Recibo();
		try {
			toReturn = dao.atualizar(recibo);
		} catch (RuntimeException e) {
			throw e;
		}
		return toReturn;
	}

	public List<Recibo> buscarPorFiltro(FiltroRecibo filtroRecibo) {
		// TODO Auto-generated method stub
		return null;
	}

}
