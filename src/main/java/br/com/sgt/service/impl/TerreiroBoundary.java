package br.com.sgt.service.impl;

import java.io.Serializable;

import javax.inject.Inject;

import br.com.sgt.entities.Terreiro;
import br.com.sgt.repository.api.TerreiroRepository;
import br.com.sgt.service.api.TerreiroService;

public class TerreiroBoundary implements Serializable, TerreiroService{

	private static final long serialVersionUID = 1L;
	
	@Inject
	private TerreiroRepository terreiroRepository;

	@Override
	public Terreiro buscarTerreiro() {
		try {
			return terreiroRepository.buscarTerreiro();
		} catch (RuntimeException e) {
			throw e;
		}
	}

}
