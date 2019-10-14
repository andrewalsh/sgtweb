package br.com.sgt.repository.filtro;

import java.io.Serializable;

public class FiltroUltimoPagamento implements Serializable{

	
	private static final long serialVersionUID = 1L;
	
	
	private Long idValorAutorizado;
	

	public FiltroUltimoPagamento(Long idValorAutorizado) {
		this.idValorAutorizado = idValorAutorizado;
	}
	
	public Long getIdValorAutorizado() {
		return idValorAutorizado;
	}
}
