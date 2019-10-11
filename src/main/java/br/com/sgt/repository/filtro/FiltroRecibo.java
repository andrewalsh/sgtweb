package br.com.sgt.repository.filtro;

import java.io.Serializable;

public class FiltroRecibo implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private Long idSocio;
	private String numeroRecibo;
	
	
	public Long getIdSocio() {
		return idSocio;
	}
	public void setIdSocio(Long idSocio) {
		this.idSocio = idSocio;
	}
	public String getNumeroRecibo() {
		return numeroRecibo;
	}
	public void setNumeroRecibo(String numeroRecibo) {
		this.numeroRecibo = numeroRecibo;
	}
	
	

}
