package br.com.sgt.service.impl;

import java.util.List;

import br.com.sgt.entities.Socio;
import br.com.sgt.repository.api.SocioRepository;
import br.com.sgt.repository.filtro.FiltroSocio;
import br.com.sgt.repository.impl.SocioDAO;
import br.com.sgt.service.api.SocioService;

public class SocioBoundary implements SocioService {
	
	private SocioRepository socioRepository = new SocioDAO();

	public Socio salvar(Socio socio) {
		Socio toReturn = new Socio();
		try {
			toReturn = socioRepository.salvar(socio);
		} catch (RuntimeException e) {
			throw e;
		}
		return toReturn;
	}

	public Socio atualizar(Socio socio) {
		Socio toReturn = new Socio();
		try {
			toReturn = socioRepository.atualizar(socio);
		} catch (RuntimeException e) {
			throw e;
		}
		return toReturn;
	}

	public Socio buscarPorId(Long id) {
		Socio toReturn = new Socio();
		try {
			toReturn = socioRepository.buscarPorId(id);
		} catch (RuntimeException e) {
			throw e;
		}
		return toReturn;
	}

	public List<Socio> buscarPorFiltro(FiltroSocio filtroSocio) {
		// TODO Auto-generated method stub
		return null;
	}

	public void excluir(Socio socio) {
		// TODO Auto-generated method stub

	}

}
