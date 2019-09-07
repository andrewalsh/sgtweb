package br.com.sgt.service.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import br.com.sgt.entities.Socio;
import br.com.sgt.entities.dto.SocioDTO;
import br.com.sgt.repository.api.SocioRepository;
import br.com.sgt.repository.filtro.FiltroSocio;
import br.com.sgt.service.api.SocioService;

public class SocioBoundary implements SocioService, Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Inject
	private SocioRepository socioRepository;

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

	public List<SocioDTO> buscarPorFiltro(FiltroSocio filtroSocio) {
		List<SocioDTO> socios = new ArrayList<>();
		try {
			socios = socioRepository.buscarPorFiltro(filtroSocio);
		} catch (RuntimeException e) {
			throw e;
		}
		
		
		return socios;
	}

	public void excluir(Socio socio) {
		// TODO Auto-generated method stub

	}

}
