package br.com.sgt.service.api;

import java.util.List;

import br.com.sgt.entities.TipoTarifa;
import br.com.sgt.repository.filtro.FiltroTipoTarifa;

public interface TipoTarifaService {
	
	TipoTarifa salavar(TipoTarifa tipoTarifa);
	
	TipoTarifa atualizar(TipoTarifa tipoTarifa);
	
	TipoTarifa buscarPorId(Long id);
	
	List<TipoTarifa> buscarPorFiltro(FiltroTipoTarifa filtroTipoTarifa);
	
	void excluir(TipoTarifa tipoTarifa);

}
