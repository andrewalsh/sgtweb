package br.com.sgt.entities.dto;

public class UsuarioDTO {
	
	private Long idUsuario;
	private String nome;
	private String cpf;
	private int idTerreiro;
	private String usuarioAtivo;
	
	public UsuarioDTO(Long idUsuario, String nome, String cpf, int idTerreiro) {
		this.idUsuario = idUsuario;
		this.nome = nome;
		this.cpf = cpf;
		this.idTerreiro = idTerreiro;
	}
	
	public UsuarioDTO(Long idUsuario, String usuarioAtivo, String nome, String cpf) {
		this.idUsuario = idUsuario;
		this.nome = nome;
		this.cpf = cpf;
		this.usuarioAtivo = usuarioAtivo;
	}
	
	public UsuarioDTO(String nome, String cpf) {
		this.nome = nome;
		this.cpf = cpf;
	}
	
	public UsuarioDTO(UsuarioDTO dto) {
		this.idUsuario = dto.getIdUsuario();
		this.nome = dto.getNome();
		this.cpf = dto.getCpf();
		this.idTerreiro = dto.getIdTerreiro();
	}
	
	public UsuarioDTO() {
		// TODO Auto-generated constructor stub
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
	public String getUsuarioAtivo() {
		return usuarioAtivo;
	}
}
