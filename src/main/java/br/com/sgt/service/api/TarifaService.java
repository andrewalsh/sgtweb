package br.com.sgt.service.api;

import java.util.List;

import br.com.sgt.entities.Tarifa;
import br.com.sgt.repository.filtro.FiltroTarifa;

public interface TarifaService {

	Tarifa salavar(Tarifa tarifa);
	
	Tarifa atualizar(Tarifa tarifa);
	
	Tarifa buscarPorId(Long id);
	
	List<Tarifa> buscarPorFiltro(FiltroTarifa filtroTarifa);
	
	void excluir(Tarifa tTarifa);
}
