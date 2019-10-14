package br.com.sgt.service.api;

import java.util.List;

import br.com.sgt.entities.Recibo;
import br.com.sgt.entities.UltimoPagamentoDaTarifa;
import br.com.sgt.repository.filtro.FiltroRecibo;

public interface ReciboService {

	Recibo salvar(Recibo recibo, UltimoPagamentoDaTarifa ultimoPagamento);
	
	List<Recibo> listar(FiltroRecibo filtro);
	
	void enviarEmail(Recibo recibo);
}
