package br.com.sgt.service.impl.pattern;

import java.io.Serializable;
import java.util.List;

import br.com.sgt.entities.ValorAutorizado;

public class GerenciadorDeValorAutorizado implements Serializable{
	
	private static final long serialVersionUID = 1L;

	public boolean valorAutorizadoParaSocioPermitido(ValorAutorizado valorAutorizado, List<ValorAutorizado> valoresAutorizados) {
		
		if(naoPossuiEstaTarifaAssocioada(valorAutorizado, valoresAutorizados))
			return true;
		else
			throw new RuntimeException("A tarifa informa já está associada ao sócio.");
		
	}

	private boolean naoPossuiEstaTarifaAssocioada(ValorAutorizado valorAutorizado, List<ValorAutorizado> valoresAutorizados) {
		for (int i = 0; i < valoresAutorizados.size(); i++) {
			if(valoresAutorizados.get(i).getTarifa().getIdTarifa().equals(valorAutorizado.getTarifa().getIdTarifa())) {
				return false;
			}
		}
		return true;
	}

}
