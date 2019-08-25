package br.com.sgt.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name="tipo_tarifa")
@Entity
public class TipoTarifa implements Serializable{

	
	private static final long serialVersionUID = 1L;

	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="ID_TIPO_TARIFA")
	private Long idTipoTarifa;
	
	@Column(name="DESCRICAO", nullable=false)
	private String nomeTipoTarifa;
	
	@Column(name="ID_TERREIRO", nullable=false)
	private Integer idTerreiro;

	
	public TipoTarifa() {
		// TODO Auto-generated constructor stub
	}


	public Long getIdTipoTarifa() {
		return idTipoTarifa;
	}


	public void setIdTipoTarifa(Long idTipoTarifa) {
		this.idTipoTarifa = idTipoTarifa;
	}


	public String getNomeTipoTarifa() {
		return nomeTipoTarifa;
	}


	public void setNomeTipoTarifa(String nomeTipoTarifa) {
		this.nomeTipoTarifa = nomeTipoTarifa;
	}


	public Integer getIdTerreiro() {
		return idTerreiro;
	}


	public void setIdTerreiro(Integer idTerreiro) {
		this.idTerreiro = idTerreiro;
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((idTipoTarifa == null) ? 0 : idTipoTarifa.hashCode());
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
		TipoTarifa other = (TipoTarifa) obj;
		if (idTipoTarifa == null) {
			if (other.idTipoTarifa != null)
				return false;
		} else if (!idTipoTarifa.equals(other.idTipoTarifa))
			return false;
		return true;
	}


	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("TipoTarifa [idTipoTarifa=");
		builder.append(idTipoTarifa);
		builder.append(", nomeTipoTarifa=");
		builder.append(nomeTipoTarifa);
		builder.append(", idTerreiro=");
		builder.append(idTerreiro);
		builder.append("]");
		return builder.toString();
	}
	
	
}
