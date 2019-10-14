package br.com.sgt.repository.api;

import br.com.sgt.entities.UltimoPagamentoDaTarifa;
import br.com.sgt.repository.filtro.FiltroUltimoPagamento;

public interface UltimoPagamentoRepository {

	UltimoPagamentoDaTarifa buscarPorFiltro(FiltroUltimoPagamento filtroUltimoPagamento);
	
	UltimoPagamentoDaTarifa salvar(UltimoPagamentoDaTarifa ultimoPagamento);
	
	UltimoPagamentoDaTarifa atualizar(UltimoPagamentoDaTarifa ultimoPagamento);
}
