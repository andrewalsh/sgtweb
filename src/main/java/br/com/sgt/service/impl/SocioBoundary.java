package br.com.sgt.service.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

import javax.inject.Inject;

import br.com.sgt.dao.tx.Transacional;
import br.com.sgt.entities.Socio;
import br.com.sgt.entities.Tarifa;
import br.com.sgt.entities.ValorAutorizado;
import br.com.sgt.entities.dto.SocioDTO;
import br.com.sgt.repository.api.SocioRepository;
import br.com.sgt.repository.filtro.FiltroSocio;
import br.com.sgt.service.api.SocioService;
import br.com.sgt.service.api.ValorAutorizadoService;

public class SocioBoundary implements SocioService, Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Inject
	private SocioRepository socioRepository;
	
	@Inject
	private ValorAutorizadoService valorAutorizadoService; 
	
	private ValorAutorizado va;

	
	public Socio salvar(Socio socio) {
		try {
			if(Objects.isNull(socio.getIdSocio())) {
				return salvarSocio(socio);
			}
			else {
				return atualizarSocio(socio);
			}
		} catch (RuntimeException e) {
			throw e;
		}
	}

	public Socio atualizar(Socio socio) {
		Socio toReturn = new Socio();
		try {
			Socio oldSocio = socioRepository.buscarPorId(socio.getIdSocio());
			
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

	@Override
	public void associarValorAutorizadoParaCadastro(Tarifa tarifa, Socio socio) {
		this.va = new ValorAutorizado(tarifa, socio);
	}
	
	
	@Transacional
	private Socio atualizarSocio(Socio socio) {
		Socio toReturn = new Socio();
		if(Objects.nonNull(socio)) {
			try {
				toReturn = socioRepository.atualizar(socio);
				if(Objects.nonNull(socio.getValorAutorizado())) {
					for (int i = 0; i < socio.getValorAutorizado().size(); i++) {
						valorAutorizadoService.salvar(socio.getValorAutorizado().get(i));
					}
				}
			} catch (RuntimeException e) {
				throw e;
			}
		}else {
			throw new RuntimeException("Erro ao carregar a lista de valores autorizados do sócio");
		}
		return toReturn;
	}

	@Transacional
	private Socio salvarSocio(Socio socio) {
		Socio toReturn = new Socio();
		
			if(Objects.nonNull(socio)) {
				socio.setDataEntrada(new Date());
				socio.setIdTerreiro(1);
				socio.getPessoa().setIdTerreiro(1L);
				
				try {
					toReturn = socioRepository.salvar(socio);
				} catch (Exception e) {
					throw e;
				}
			}
		return toReturn;
	}
}
