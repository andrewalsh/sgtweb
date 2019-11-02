package br.com.sgt.service.impl;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import br.com.sgt.dao.tx.Transacional;
import br.com.sgt.entities.Tarifa;
import br.com.sgt.entities.ValorAutorizado;
import br.com.sgt.repository.api.TarifaRepository;
import br.com.sgt.repository.filtro.FiltroTarifa;
import br.com.sgt.repository.filtro.FiltroValorAutorizado;
import br.com.sgt.service.api.TarifaService;
import br.com.sgt.service.api.ValorAutorizadoService;

public class TarifaBoundary implements TarifaService, Serializable{

	
	private static final long serialVersionUID = 1L;
	
	@Inject
	TarifaRepository tarifaRepository;
	
	@Inject
	ValorAutorizadoService valorAutorizadoService;

	@Override
	public Tarifa salavar(Tarifa tarifa) {
		try {
			return tarifaRepository.salavar(tarifa);
		} catch (RuntimeException e) {
			throw e;
		}
	}

	@Override
	@Transacional
	public Tarifa atualizar(Tarifa tarifa) {
		List<ValorAutorizado> listaValorAutorizado = new ArrayList<ValorAutorizado>();
		FiltroValorAutorizado filtroValorAutorizado = new FiltroValorAutorizado();
		try {
			BigDecimal valorAntigo = buscarPorId(tarifa.getIdTarifa()).getValor();
			Tarifa toReturn = tarifaRepository.atualizar(tarifa);
			listaValorAutorizado = valorAutorizadoService.listarPorFiltro(filtroValorAutorizado);
			for (ValorAutorizado valorAutorizado : listaValorAutorizado) {
				if(valorAutorizado.getTarifa().getIdTarifa().equals(tarifa.getIdTarifa())) {
					if(valorAutorizado.getValorLiquido().equals(valorAntigo)) {
						valorAutorizado.setValorLiquido(toReturn.getValor());
					}
				}
			}
			return null;
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
