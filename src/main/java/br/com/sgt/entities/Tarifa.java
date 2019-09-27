package br.com.sgt.entities;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Table(name="t_tarifa")
@Entity
public class Tarifa implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="ID_TARIFA")
	private Long idTarifa;
	
	@Column(name="DESCRICAO", nullable=false)
	private String nomeTarifa;
	
	@Column(name="VALOR", precision=3, scale=2, nullable=false)
	private BigDecimal valor;
	
	/*@ManyToOne
	@JoinColumn(name="idTipoTarifa", nullable=false)
	private TipoTarifa tipoTarifa;*/
	
	
	public Tarifa() {
		// TODO Auto-generated constructor stub
	}


	public Long getIdTarifa() {
		return idTarifa;
	}


	public void setIdTarifa(Long idTarifa) {
		this.idTarifa = idTarifa;
	}


	public String getNomeTarifa() {
		return nomeTarifa;
	}


	public void setNomeTarifa(String nomeTarifa) {
		this.nomeTarifa = nomeTarifa;
	}


	public BigDecimal getValor() {
		return valor;
	}


	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}


	/*public TipoTarifa getTipoTarifa() {
		return tipoTarifa;
	}


	public void setTipoTarifa(TipoTarifa tipoTarifa) {
		this.tipoTarifa = tipoTarifa;
	}*/


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((idTarifa == null) ? 0 : idTarifa.hashCode());
		result = prime * result + ((nomeTarifa == null) ? 0 : nomeTarifa.hashCode());
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
		Tarifa other = (Tarifa) obj;
		if (idTarifa == null) {
			if (other.idTarifa != null)
				return false;
		} else if (!idTarifa.equals(other.idTarifa))
			return false;
		if (nomeTarifa == null) {
			if (other.nomeTarifa != null)
				return false;
		} else if (!nomeTarifa.equals(other.nomeTarifa))
			return false;
		return true;
	}


	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Tarifa [idTarifa=");
		builder.append(idTarifa);
		builder.append(", nomeTarifa=");
		builder.append(nomeTarifa);
		builder.append(", valor=");
		builder.append(valor);
		/*builder.append(", tipoTarifa=");
		builder.append(tipoTarifa);*/
		builder.append("]");
		return builder.toString();
	}
	
	
}
