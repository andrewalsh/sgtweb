package br.com.sgt.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name="t_ultimo_pagamento_da_tarifa")
@Entity
public class UltimoPagamentoDaTarifa implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="ID_ULTIMO_PAGAMENTO")
	private Long idUltimoPagamento;
	
	@Column(name="ID_VALOR_AUTORIZADO", unique=true)
	private Long idValorAutorizado;
	
	@Column(name="MES_BASE")
	private int mesBase;
	
	@Column(name="ANO_BASE")
	private int anoBase;
	
	public UltimoPagamentoDaTarifa() {
		
	}
	
	public UltimoPagamentoDaTarifa(Long idValorAutorizado, int mesBase, int anoBase) {
		this.idValorAutorizado = idValorAutorizado;
		this.mesBase = mesBase;
		this.anoBase = anoBase;
	}


	public Long getIdUltimoPagamento() {
		return idUltimoPagamento;
	}
	public Long getIdValorAutorizado() {
		return idValorAutorizado;
	}
	public int getMesBase() {
		return mesBase;
	}
	public int getAnoBase() {
		return anoBase;
	}
}
