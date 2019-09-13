package br.com.sgt.entities.dto;

import java.io.Serializable;

public class SocioDTO implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private Long idSocio;
	private String nome;
	private String tipoSocio;
	private String telefoneCelular;
	private String telefoneResidencial;
	
	public SocioDTO(Long idSocio, String nome, String tipoSocio, String telefoneCelular, String telefoneResidencial) {
		this.idSocio = idSocio;
		this.nome = nome;
		this.tipoSocio = tipoSocio;
		this.telefoneCelular = telefoneCelular;
		this.telefoneResidencial = telefoneResidencial;
	}
	

	public Long getIdSocio() {
		return idSocio;
	}

	public String getNome() {
		return nome;
	}

	public String getTipoSocio() {
		return tipoSocio;
	}

	public String getTelefoneCelular() {
		return telefoneCelular;
	}

	public String getTelefoneResidencial() {
		return telefoneResidencial;
	}


	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("SocioDTO [idSocio=");
		builder.append(idSocio);
		builder.append(", nome=");
		builder.append(nome);
		builder.append(", tipoSocio=");
		builder.append(tipoSocio);
		builder.append(", telefoneCelular=");
		builder.append(telefoneCelular);
		builder.append(", telefoneResidencial=");
		builder.append(telefoneResidencial);
		builder.append("]");
		return builder.toString();
	}
	
	
	
}
