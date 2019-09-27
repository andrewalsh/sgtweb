package br.com.sgt.service.impl;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;

import br.com.sgt.entities.Tarifa;
import br.com.sgt.repository.api.TarifaRepository;
import br.com.sgt.repository.filtro.FiltroTarifa;
import br.com.sgt.service.api.TarifaService;

public class TarifaBoundary implements TarifaService, Serializable{

	
	private static final long serialVersionUID = 1L;
	
	@Inject
	TarifaRepository tarifaRepository;

	@Override
	public Tarifa salavar(Tarifa tarifa) {
		try {
			return tarifaRepository.salavar(tarifa);
		} catch (RuntimeException e) {
			throw e;
		}
	}

	@Override
	public Tarifa atualizar(Tarifa tarifa) {
		try {
			return tarifaRepository.atualizar(tarifa);
		} catch (RuntimeException e) {
			throw e;
		}
	}

	@Override
	public Tarifa buscarPorId(Long id) {
		try {
			return tarifaRepository.buscaPorId(id);
		} catch (RuntimeException e) {
			throw e;
		}
	}

	@Override
	public List<Tarifa> buscarPorFiltro(FiltroTarifa filtroTarifa) {
		try {
			return tarifaRepository.buscarPorFiltro(filtroTarifa);
		} catch (RuntimeException e) {
			throw e;
		}
	}

	@Override
	public void excluir(Tarifa tTarifa) {
		// TODO Auto-generated method stub
		
	}

}
