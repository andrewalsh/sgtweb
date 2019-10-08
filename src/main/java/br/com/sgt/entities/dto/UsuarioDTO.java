package br.com.sgt.entities.dto;

public class UsuarioDTO {
	
	private Long idUsuario;
	private String nome;
	private String cpf;
	private int idTerreiro;
	
	public UsuarioDTO(Long idUsuario, String nome, String cpf, int idTerreiro) {
		this.idUsuario = idUsuario;
		this.nome = nome;
		this.cpf = cpf;
		this.idTerreiro = idTerreiro;
	}

	public Long getIdUsuario() {
		return idUsuario;
	}
	public String getNome() {
		return nome;
	}
	public String getCpf() {
		return cpf;
	}
	public int getIdTerreiro() {
		return idTerreiro;
	}
}
