package br.com.sgt.entities.dto;

import java.math.BigDecimal;

public class ValorAutorizadoDTO {

	private Long idValorAutorizado;
	private Long idSocio;
	private Long idTarifa;
	private BigDecimal valorBruto;
	private BigDecimal valorLiquido;
	
	
	public Long getIdValorAutorizado() {
		return idValorAutorizado;
	}
	public void setIdValorAutorizado(Long idValorAutorizado) {
		this.idValorAutorizado = idValorAutorizado;
	}
	public Long getIdSocio() {
		return idSocio;
	}
	public void setIdSocio(Long idSocio) {
		this.idSocio = idSocio;
	}
	public Long getIdTarifa() {
		return idTarifa;
	}
	public void setIdTarifa(Long idTarifa) {
		this.idTarifa = idTarifa;
	}
	public BigDecimal getValorBruto() {
		return valorBruto;
	}
	public void setValorBruto(BigDecimal valorBruto) {
		this.valorBruto = valorBruto;
	}
	public BigDecimal getValorLiquido() {
		return valorLiquido;
	}
	public void setValorLiquido(BigDecimal valorLiquido) {
		this.valorLiquido = valorLiquido;
	}
}
