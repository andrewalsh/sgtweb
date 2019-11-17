package br.com.sgt.service.api;

import java.util.List;

import br.com.sgt.entities.Recibo;
import br.com.sgt.repository.filtro.FiltroRecibo;

public interface ReciboService {
	
	List<Recibo> listar(FiltroRecibo filtro);
	
	void salvar(Recibo recibo);
	
	void estorno(Recibo recibo);
}
