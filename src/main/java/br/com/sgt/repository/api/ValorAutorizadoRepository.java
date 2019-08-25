package br.com.sgt.repository.api;

import java.util.List;

import br.com.sgt.entities.ValorAutorizado;
import br.com.sgt.repository.filtro.FiltroValorAutorizado;

public interface ValorAutorizadoRepository {
	
	ValorAutorizado salavar(ValorAutorizado valorAutorizado);
	
	ValorAutorizado atualizar(ValorAutorizado valorAutorizado);
	
	List<ValorAutorizado> buscarPorFiltro(FiltroValorAutorizado filtroValorAutorizado);
	
	void excluir(ValorAutorizado valorAutorizado);
}
