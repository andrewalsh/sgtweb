package br.com.sgt.repository;

import java.util.List;

import br.com.sgt.entities.TipoTarifa;
import br.com.sgt.repository.filtro.FiltroTarifa;
import br.com.sgt.repository.filtro.FiltroTipoTarifa;

public interface Tarifa {

	Tarifa salavar(Tarifa tarifa);
	
	Tarifa atualizar(Tarifa tarifa);
	
	List<Tarifa> buscarPorFiltro(FiltroTarifa filtroTarifa);
	
	void excluir(Tarifa tarifa);
}
