package br.com.sgt.service.impl;

import java.math.BigDecimal;
import java.util.Objects;

import javax.inject.Inject;
import javax.management.RuntimeErrorException;

import br.com.sgt.entities.Socio;
import br.com.sgt.entities.Tarifa;
import br.com.sgt.entities.ValorAutorizado;
import br.com.sgt.repository.api.ValorAutorizadoRepository;
import br.com.sgt.service.api.ValorAutorizadoService;

public class ValorAutorizadoBoundary implements ValorAutorizadoService{
	
	@Inject
	private ValorAutorizadoRepository valorAutorizadoRepository;

	@Override
	public boolean autorizacaoValida(ValorAutorizado va) {
		boolean valido = false;
		switch (va.getSocio().getTipoSocio()) {
		
		case "COLABORADOR":
			if(descontoValido(va)) {
				valido = true;
			}
			break;
			
		case "EFETIVO":
			if(descontoValido(va)) {
				valido = true;
			}
			break;
		}
		return valido;
	}
	
	
	public ValorAutorizado salvar(ValorAutorizado va) {
		try {
			return valorAutorizadoRepository.salavar(va);
		} catch (RuntimeException e) {
			throw e;
		}
	}
	
	
	private boolean novoValorAutorizadoNaoFoiCadastrado() {
		// TODO Auto-generated method stub
		return false;
	}


	private boolean possuiMenosDeTresTarifas(ValorAutorizado va) {
		// TODO Auto-generated method stub
		return false;
	}


	private boolean descontoValido(ValorAutorizado va) {
		boolean toReturn = false;
		if(Objects.nonNull(va)) {
			try {
				if(va.getTarifa().getValor().subtract(va.getDesconto()).compareTo(BigDecimal.ZERO) > 0) {
					toReturn = true;
				}
			} catch (RuntimeException e) {
				toReturn = false;
				throw e;
			}
		}
		return toReturn;
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

}
