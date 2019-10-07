package br.com.sgt.pattern.builder;

import br.com.sgt.entities.Pessoa;
import br.com.sgt.entities.Socio;
import br.com.sgt.entities.Tarifa;
import br.com.sgt.entities.ValorAutorizado;

public class ValorAutorizadoBuilder {
	
	private Socio socio;
	private Tarifa tarifa;

	public ValorAutorizadoBuilder() {
		socio = new Socio();
		socio.setPessoa(new Pessoa());
		tarifa = new Tarifa();
	}

	public ValorAutorizado gerar() {
		return new ValorAutorizado(tarifa, socio);
	}
}
