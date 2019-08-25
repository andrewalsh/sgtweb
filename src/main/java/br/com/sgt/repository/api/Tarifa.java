package br.com.sgt.repository.api;

import java.util.List;

import br.com.sgt.repository.filtro.FiltroTarifa;

public interface Tarifa {

	Tarifa salavar(Tarifa tarifa);
	
	Tarifa atualizar(Tarifa tarifa);
	
	List<Tarifa> buscarPorFiltro(FiltroTarifa filtroTarifa);
	
	void excluir(Tarifa tarifa);
}
