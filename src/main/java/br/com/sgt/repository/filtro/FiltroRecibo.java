package br.com.sgt.repository.filtro;

import java.io.Serializable;

public class FiltroRecibo implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private Long idRecibo;
	private Long idSocio;
	private String numeroRecibo;
	private Integer anoBase;
	private Integer mesBase;
	
	public Long getIdRecibo() {
		return idRecibo;
	}
	public void setIdRecibo(Long idRecibo) {
		this.idRecibo = idRecibo;
	}
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
	public Integer getAnoBase() {
		return anoBase;
	}
	public void setAnoBase(Integer anoBase) {
		this.anoBase = anoBase;
	}
	public Integer getMesBase() {
		return mesBase;
	}
	public void setMesBase(Integer mesBase) {
		this.mesBase = mesBase;
	}
}
