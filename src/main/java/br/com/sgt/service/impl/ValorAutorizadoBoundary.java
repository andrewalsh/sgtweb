package br.com.sgt.service.impl;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;

import javax.inject.Inject;

import br.com.sgt.dao.tx.Transacional;
import br.com.sgt.entities.Pessoa;
import br.com.sgt.entities.Socio;
import br.com.sgt.entities.Tarifa;
import br.com.sgt.entities.ValorAutorizado;
import br.com.sgt.repository.api.ValorAutorizadoRepository;
import br.com.sgt.repository.filtro.FiltroValorAutorizado;
import br.com.sgt.service.api.ValorAutorizadoService;
import br.com.sgt.service.impl.pattern.GerenciadorDeValorAutorizado;

public class ValorAutorizadoBoundary implements Serializable, ValorAutorizadoService{
	
	private static final long serialVersionUID = 1L;

	@Inject
	private ValorAutorizadoRepository valorAutorizadoRepository;
	
	@Inject
	private GerenciadorDeValorAutorizado gerenciadorDeValorAutorizado;
	

	
	@Transacional
	public ValorAutorizado salvar(ValorAutorizado va) {
		try {
			if(Objects.isNull(va.getIdValorAutorizado())) {
				return salvarValorAutorizado(va);
			}else {
				return atualizarValorAutorizado(va);
			}
		} catch (RuntimeException e) {
			throw e;
		}
	}

	@Override
	public ValorAutorizado associarValor(Tarifa tarifa, Socio socio) {
		try {
			if(Objects.nonNull(tarifa) && Objects.nonNull(socio))
				return new ValorAutorizado(tarifa, socio);
			else
				throw new RuntimeException("O sócio e/ou tarifa não podem estar vazios");
		} catch (RuntimeException e) {
			throw e;
		}
	}


	@Override
	public List<ValorAutorizado> listarPorSocio(Socio socio) {
		FiltroValorAutorizado filtro = new FiltroValorAutorizado();
		filtro.setIdSocio(361L);
		try {
			return valorAutorizadoRepository.buscarPorFiltro(filtro);
		} catch (RuntimeException e) {
			throw e;
		}
	}
	
	@Override
	public ValorAutorizado valorAutorizadoBuilder() {
		ValorAutorizado va = new ValorAutorizado();
		va.setIdTerreiro(1);
		va.setSocio(new Socio());
		va.getSocio().setPessoa(new Pessoa());
		va.setTarifa(new Tarifa());
		return va;
	}
	
	private boolean novoValorAutorizadoNaoFoiCadastrado() {
		// TODO Auto-generated method stub
		return false;
	}


	private boolean possuiMenosDeTresTarifas(ValorAutorizado va) {
		// TODO Auto-generated method stub
		return false;
	}

	private ValorAutorizado salvarValorAutorizado(ValorAutorizado va) {
		try {
			if(new GerenciadorDeValorAutorizado().valorAutorizadoParaSocioPermitido(va, listarPorSocio(null))) {
				return valorAutorizadoRepository.salavar(va);
			}
			else
				return null;
		} catch (RuntimeException e) {
			throw e;
		}
	}
	
	private ValorAutorizado atualizarValorAutorizado(ValorAutorizado va) {
		try {
			return valorAutorizadoRepository.atualizar(va);
		} catch (RuntimeException e) {
			throw e;
		}
	}
}
