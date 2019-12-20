package br.com.sgt.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Table(name="t_usuario")
@Entity
public class Usuario implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="ID_USUARIO")
	private Long idUsuario;
	
	@Column(name="ATIVO")
	private String usuarioAtivo;
	
	@Column(name="PERFIL", nullable=false)
	private String perfil;
	
	@Column(name="SENHA", nullable=false)
	private String senha;
	
	@Column(name="ID_TERREIRO", nullable=false)
	private int idTerreiro;
	
	@OneToOne
	@JoinColumn(name="ID_PESSOA", nullable=false)
	private Pessoa pessoa;

	
	
	public Usuario() {
		// TODO Auto-generated constructor stub
	}

	
	
	public Usuario(Long idUsuario, String usuarioAtivo, Pessoa pessoa) {
		this.idUsuario = idUsuario;
		this.usuarioAtivo = usuarioAtivo;
		this.pessoa = pessoa;
	}



	public Long getIdUsuario() {
		return idUsuario;
	}



	public void setIdUsuario(Long idUsuario) {
		this.idUsuario = idUsuario;
	}



	public String getUsuarioAtivo() {
		return usuarioAtivo;
	}



	public void setUsuarioAtivo(String usuarioAtivo) {
		this.usuarioAtivo = usuarioAtivo;
	}



	public String getPerfil() {
		return perfil;
	}



	public void setPerfil(String perfil) {
		this.perfil = perfil;
	}



	public String getSenha() {
		return senha;
	}



	public void setSenha(String senha) {
		this.senha = senha;
	}



	public int getIdTerreiro() {
		return idTerreiro;
	}



	public void setIdTerreiro(int idTerreiro) {
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
		result = prime * result + ((idUsuario == null) ? 0 : idUsuario.hashCode());
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
		Usuario other = (Usuario) obj;
		if (idUsuario == null) {
			if (other.idUsuario != null)
				return false;
		} else if (!idUsuario.equals(other.idUsuario))
			return false;
		if (pessoa == null) {
			if (other.pessoa != null)
				return false;
		} else if (!pessoa.equals(other.pessoa))
			return false;
		return true;
	}
	
	

}
