package br.com.sgt.service.impl;

import java.io.Serializable;
import java.util.Objects;

import javax.inject.Inject;

import br.com.sgt.entities.UltimoPagamentoDaTarifa;
import br.com.sgt.repository.api.UltimoPagamentoRepository;
import br.com.sgt.repository.filtro.FiltroUltimoPagamento;
import br.com.sgt.service.api.UltimoPagamentoService;

public class UltimoPagamentoBoundary implements Serializable, UltimoPagamentoService {

	private static final long serialVersionUID = 1L;
	
	@Inject
	private UltimoPagamentoRepository ultimoPagamentoRepository;
	

	@Override
	public UltimoPagamentoDaTarifa buscarPorFiltro(FiltroUltimoPagamento filtroUltimoPagamento) {
		try {
			return ultimoPagamentoRepository.buscarPorFiltro(filtroUltimoPagamento);
		} catch (RuntimeException e) {
			throw e;
		}
	}

	@Override
	public UltimoPagamentoDaTarifa salvar(UltimoPagamentoDaTarifa ultimoPagamento) {
		try {
			if(Objects.nonNull(ultimoPagamento.getIdUltimoPagamento())) {
				return ultimoPagamentoRepository.atualizar(ultimoPagamento);
			}else {
				return ultimoPagamentoRepository.salvar(ultimoPagamento);
			}
		} catch (RuntimeException e) {
			throw e;
		}
	}

	@Override
	public UltimoPagamentoDaTarifa atualizar(UltimoPagamentoDaTarifa ultimoPagamento) {
		// TODO Auto-generated method stub
		return null;
	}

}
