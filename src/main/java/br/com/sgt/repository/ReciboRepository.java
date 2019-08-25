package br.com.sgt.repository;

import java.util.List;

import br.com.sgt.entities.Recibo;
import br.com.sgt.repository.filtro.FiltroRecibo;

public interface ReciboRepository {
	
	Recibo salavar(Recibo valorAutorizado);
	
	Recibo atualizar(Recibo valorAutorizado);
	
	List<Recibo> buscarPorFiltro(FiltroRecibo filtroRecibo);

}
