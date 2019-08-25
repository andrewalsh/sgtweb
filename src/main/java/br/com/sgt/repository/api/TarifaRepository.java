package br.com.sgt.repository.api;

import java.util.List;

import br.com.sgt.entities.Tarifa;
import br.com.sgt.repository.filtro.FiltroTarifa;

public interface TarifaRepository {

	Tarifa salavar(Tarifa tarifa);
	
	Tarifa atualizar(Tarifa tarifa);
	
	Tarifa buscaPorId(Long id);
	
	List<Tarifa> buscarPorFiltro(FiltroTarifa filtroTarifa);
	
	void excluir(Tarifa tarifa);
}
