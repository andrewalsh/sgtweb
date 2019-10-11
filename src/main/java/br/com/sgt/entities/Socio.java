package br.com.sgt.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Table(name="t_socio")
@Entity
public class Socio implements Serializable{

	private static final long serialVersionUID = 1L;

	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="ID_SOCIO", nullable=false)
	private Long idSocio;
	
	@Temporal(TemporalType.DATE)
	@Column(name="DATA_ENTRADA", nullable=false)
	private Date dataEntrada;
	
	@Column(name="TIPO_SOCIO", nullable=false)
	private String tipoSocio;
	
	@Column(name="ID_TERREIRO", nullable=false)
	private Integer idTerreiro;
	
	@OneToOne(cascade= CascadeType.ALL)
	@JoinColumn(name="ID_PESSOA",nullable=false)
	private Pessoa pessoa;
	
	public Socio() {
		if(Objects.isNull(pessoa)) {
			setPessoa(new Pessoa());
		}
	}


	public Long getIdSocio() {
		return idSocio;
	}


	public void setIdSocio(Long idSocio) {
		this.idSocio = idSocio;
	}


	public Date getDataEntrada() {
		return dataEntrada;
	}


	public void setDataEntrada(Date dataEntrada) {
		this.dataEntrada = dataEntrada;
	}


	public String getTipoSocio() {
		return tipoSocio;
	}


	public void setTipoSocio(String tipoSocio) {
		this.tipoSocio = tipoSocio;
	}


	public Integer getIdTerreiro() {
		return idTerreiro;
	}


	public void setIdTerreiro(Integer idTerreiro) {
		this.idTerreiro = idTerreiro;
	}


	public Pessoa getPessoa() {
		return pessoa;
	}


	public void setPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((idSocio == null) ? 0 : idSocio.hashCode());
		result = prime * result + ((pessoa == null) ? 0 : pessoa.hashCode());
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
		Socio other = (Socio) obj;
		if (idSocio == null) {
			if (other.idSocio != null)
				return false;
		} else if (!idSocio.equals(other.idSocio))
			return false;
		if (pessoa == null) {
			if (other.pessoa != null)
				return false;
		} else if (!pessoa.equals(other.pessoa))
			return false;
		return true;
	}
	
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Socio [idSocio=");
		builder.append(idSocio);
		builder.append(", dataEntrada=");
		builder.append(dataEntrada);
		builder.append(", tipoSocio=");
		builder.append(tipoSocio);
		builder.append(", idTerreiro=");
		builder.append(idTerreiro);
		builder.append(", pessoa=");
		builder.append(pessoa);
		builder.append(", valorAutorizado=");

		builder.append("]");
		return builder.toString();
	}


	public List<String> getListaTipoSocios(){
		return Arrays.asList("COLABORADOR","EFETIVO","VOLUNTÁRIO","ALUNO");
	}
}
