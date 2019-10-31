package br.com.sgt.pattern.factory;

import java.util.Calendar;
import java.util.Objects;

import br.com.sgt.entities.Pessoa;
import br.com.sgt.entities.Recibo;
import br.com.sgt.entities.Socio;
import br.com.sgt.entities.Tarifa;
import br.com.sgt.entities.ValorAutorizado;

public class ReciboFactory {
	
	private ValorAutorizado valorAutorizado;

	public Recibo factory() {
		return new Recibo(anoCorrente(), mesCorrente(), 1, getValorAutorizado());
	}
	
	private ValorAutorizado getValorAutorizado() {
		if(Objects.isNull(valorAutorizado)) {
			valorAutorizado = new ValorAutorizado();
			valorAutorizado.setTarifa(new Tarifa());
			valorAutorizado.setIdTerreiro(1);
			valorAutorizado.setSocio(new Socio());
			valorAutorizado.getSocio().setPessoa(new Pessoa());
		}
		return valorAutorizado;
	}
	
	private int mesCorrente() {
		Calendar calendar = Calendar.getInstance();
		int mes = calendar.get(Calendar.MONTH) + 1;
		return mes;
	}
	
	private int anoCorrente() {
		Calendar calendar = Calendar.getInstance();
		int ano = calendar.get(Calendar.YEAR);
		return ano;
	}
}
