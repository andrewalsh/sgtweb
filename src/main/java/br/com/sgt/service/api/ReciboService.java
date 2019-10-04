package br.com.sgt.service.api;

import br.com.sgt.entities.Recibo;

public interface ReciboService {

	Recibo salvar(Recibo recibo);
	
	void enviarEmail(Recibo recibo);
}
