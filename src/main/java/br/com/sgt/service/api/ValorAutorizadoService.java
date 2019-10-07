package br.com.sgt.service.api;

import java.util.List;

import br.com.sgt.entities.Socio;
import br.com.sgt.entities.Tarifa;
import br.com.sgt.entities.ValorAutorizado;
import br.com.sgt.repository.filtro.FiltroValorAutorizado;

public interface ValorAutorizadoService {
	
	ValorAutorizado associarValor(Tarifa tarifa, Socio socio);
	
	ValorAutorizado salvar(ValorAutorizado va);
	
	List<ValorAutorizado> listarPorSocio(Socio socio);
	
	List<ValorAutorizado> listarPorFiltro(FiltroValorAutorizado filtroValorAutorizado);
	
	public ValorAutorizado valorAutorizadoBuilder();

}
