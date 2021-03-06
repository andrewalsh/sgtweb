package br.com.sgt.repository.api;

import java.util.List;

import br.com.sgt.entities.Socio;
import br.com.sgt.repository.filtro.FiltroSocio;

public interface SocioRepository {
	
	Socio salvar(Socio socio);
	
	Socio atualizar(Socio socio);
	
	Socio buscarPorId(Long id);
	
	List<Socio> buscarPorFiltro(FiltroSocio filtroSocio);
	
	void excluir(Socio socio);
	
	

}
