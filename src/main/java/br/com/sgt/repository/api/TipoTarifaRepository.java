package br.com.sgt.repository.api;

import java.util.List;

import br.com.sgt.entities.TipoTarifa;
import br.com.sgt.repository.filtro.FiltroTipoTarifa;

public interface TipoTarifaRepository {

	TipoTarifa salavar(TipoTarifa tipoTarifa);
	
	TipoTarifa atualizar(TipoTarifa tipoTarifa);
	
	TipoTarifa buscarPorId(Long id);
	
	List<TipoTarifa> buscarPorFiltro(FiltroTipoTarifa filtroTipoTarifa);
	
	void excluir(TipoTarifa tipoTarifa);
}
