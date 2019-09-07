package br.com.sgt.entities.dto;

public class SocioDTO {
	
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
	
}
