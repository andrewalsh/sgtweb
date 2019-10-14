package br.com.sgt.service.api;

import br.com.sgt.entities.UltimoPagamentoDaTarifa;
import br.com.sgt.repository.filtro.FiltroUltimoPagamento;

public interface UltimoPagamentoService {
	
	UltimoPagamentoDaTarifa buscarPorFiltro(FiltroUltimoPagamento filtroUltimoPagamento);
	
	UltimoPagamentoDaTarifa salvar(UltimoPagamentoDaTarifa ultimoPagamento);

}
