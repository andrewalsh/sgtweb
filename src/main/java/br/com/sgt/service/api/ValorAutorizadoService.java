package br.com.sgt.service.api;

import br.com.sgt.entities.Socio;
import br.com.sgt.entities.Tarifa;
import br.com.sgt.entities.ValorAutorizado;

public interface ValorAutorizadoService {
	
	boolean autorizacaoValida(ValorAutorizado va);
	
	ValorAutorizado associarValor(Tarifa tarifa, Socio socio);
	
	ValorAutorizado salvar(ValorAutorizado va);

}
