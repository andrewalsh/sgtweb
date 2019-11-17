package br.com.sgt.pattern.startegy;

import br.com.sgt.entities.Recibo;

public interface MessageBodyStrategy {
	
	String setTextPagamento(Recibo recibo);
	
	String setTextEstorno(Recibo recibo);

}
