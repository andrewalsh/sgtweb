package br.com.sgt.entities;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Table(name="T_VALOR_AUTORIZADO")
@Entity
public class ValorAutorizado implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="ID_VALOR_AUTORIZADO")
	private Long idValorAutorizado;
	
	@Column(name="ID_TERREIRO", nullable=false)
	private Integer idTerreiro;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="ID_SOCIO", nullable=false)
	private Socio socio;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="ID_TARIFA", nullable=false)
	private Tarifa tarifa;

	@Column(name="DESCONTO", precision=2, scale=2, nullable=true)
	private BigDecimal desconto;
	
	@Column(name="ACRESCIMO", precision=2, scale=2, nullable=true)
	private BigDecimal acrescimo;
	
	public ValorAutorizado() {
		// TODO Auto-generated constructor stub
	}


	public Long getIdValorAutorizado() {
		return idValorAutorizado;
	}


	public void setIdValorAutorizado(Long idValorAutorizado) {
		this.idValorAutorizado = idValorAutorizado;
	}


	public Integer getIdTerreiro() {
		return idTerreiro;
	}


	public void setIdTerreiro(Integer idTerreiro) {
		this.idTerreiro = idTerreiro;
	}


	public Socio getSocio() {
		return socio;
	}


	public void setSocio(Socio socio) {
		this.socio = socio;
	}


	public Tarifa getTarifa() {
		return tarifa;
	}


	public void setTarifa(Tarifa tarifa) {
		this.tarifa = tarifa;
	}
	
	public BigDecimal getDesconto() {
		return desconto;
	}


	public void setDesconto(BigDecimal desconto) {
		this.desconto = desconto;
	}


	public BigDecimal getAcrescimo() {
		return acrescimo;
	}


	public void setAcrescimo(BigDecimal acrescimo) {
		this.acrescimo = acrescimo;
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((idTerreiro == null) ? 0 : idTerreiro.hashCode());
		result = prime * result + ((idValorAutorizado == null) ? 0 : idValorAutorizado.hashCode());
		result = prime * result + ((socio == null) ? 0 : socio.hashCode());
		result = prime * result + ((tarifa == null) ? 0 : tarifa.hashCode());
		return result;
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ValorAutorizado other = (ValorAutorizado) obj;
		if (idTerreiro == null) {
			if (other.idTerreiro != null)
				return false;
		} else if (!idTerreiro.equals(other.idTerreiro))
			return false;
		if (idValorAutorizado == null) {
			if (other.idValorAutorizado != null)
				return false;
		} else if (!idValorAutorizado.equals(other.idValorAutorizado))
			return false;
		if (socio == null) {
			if (other.socio != null)
				return false;
		} else if (!socio.equals(other.socio))
			return false;
		if (tarifa == null) {
			if (other.tarifa != null)
				return false;
		} else if (!tarifa.equals(other.tarifa))
			return false;
		return true;
	}


	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("ValorAutorizado [idValorAutorizado=");
		builder.append(idValorAutorizado);
		builder.append(", idTerreiro=");
		builder.append(idTerreiro);
		builder.append(", socio=");
		builder.append(socio);
		builder.append(", tarifa=");
		builder.append(tarifa);
		builder.append("]");
		return builder.toString();
	}
	
	
}
