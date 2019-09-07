package br.com.sgt.service.impl;

import java.math.BigDecimal;
import java.util.Objects;

import br.com.sgt.entities.ValorAutorizado;
import br.com.sgt.service.api.ValorAutorizadoService;

public class ValorAutorizadoBoundary implements ValorAutorizadoService{
	
	//private static final String[] TIPO_SOCIO = {"COLABORADOR","EFETIVO","VOLUNTÁRIO","ALUNO"};

	@Override
	public boolean autorizacaoValida(ValorAutorizado va) {
		boolean valido = false;
		switch (va.getSocio().getTipoSocio()) {
		
		case "COLABORADOR":
			if(descontoValido(va)) {
				valido = true;
			}
			break;
			
		case "EFETIVO":
			if(descontoValido(va)) {
				valido = true;
			}
			break;
		}
		return valido;
	}
	
	
	private boolean descontoValido(ValorAutorizado va) {
		boolean toReturn = false;
		if(Objects.nonNull(va)) {
			try {
				if(va.getTarifa().getValor().subtract(va.getDesconto()).compareTo(BigDecimal.ZERO) > 0) {
					toReturn = true;
				}
			} catch (RuntimeException e) {
				toReturn = false;
				throw e;
			}
		}
		return toReturn;
	}

}
