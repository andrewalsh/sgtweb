package br.com.sgt.service.impl;

import java.util.List;

import br.com.sgt.entities.TipoTarifa;
import br.com.sgt.repository.api.TipoTarifaRepository;
import br.com.sgt.repository.filtro.FiltroTipoTarifa;
import br.com.sgt.repository.impl.TipoTarifaDAO;
import br.com.sgt.service.api.TipoTarifaService;

public class TipoTarifaBoundary implements TipoTarifaService {
	
	private TipoTarifaRepository tipoTarifaRepository = new TipoTarifaDAO();

	public TipoTarifa salavar(TipoTarifa tipoTarifa) {
		TipoTarifa toReturn = new TipoTarifa();
		try {
			toReturn = tipoTarifaRepository.salavar(tipoTarifa);
		} catch (RuntimeException e) {
			throw e;
		}
		return toReturn;
	}

	public TipoTarifa atualizar(TipoTarifa tipoTarifa) {
		TipoTarifa toReturn = new TipoTarifa();
		try {
			toReturn = tipoTarifaRepository.atualizar(tipoTarifa);
		} catch (RuntimeException e) {
			throw e;
		}
		return toReturn;
	}

	public TipoTarifa buscarPorId(Long id) {
		TipoTarifa toReturn = new TipoTarifa();
		try {
			toReturn = tipoTarifaRepository.buscarPorId(id);
		} catch (RuntimeException e) {
			throw e;
		}
		return toReturn;
	}

	public List<TipoTarifa> buscarPorFiltro(FiltroTipoTarifa filtroTipoTarifa) {
		// TODO Auto-generated method stub
		return null;
	}

	public void excluir(TipoTarifa tipoTarifa) {
		// TODO Auto-generated method stub

	}

}
