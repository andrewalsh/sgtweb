package br.com.sgt.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class Pessoa implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="ID_PESSOA")
	private Integer idPessoa;
	
	@Column(name="CPF", unique=true, nullable=false)
	private String cpf;
	
	@Column(name="EMAIL")
	private String email;
	
	@Column(name="ENDERECO_COMERCIAL")
	private String enderecoComercial;
	
	@Column(name="ENDERECO_RESIDENCIAL", nullable=false)
	private String enderecoResidencial;
	
	@Column(name="NASCIMENTO", nullable=false)
	@Temporal(TemporalType.DATE)
	private Date nascimento;
	
	@Column(name="NOME", nullable=false)
	private String nome;
	
	@Column(name="PROFISSAO")
	private String profissao;
	
	@Column(name="TELEFONE_CELULAR1")
	private String telefoneCelular1;
	
	@Column(name="TELEFONE_CELULAR2")
	private String telefoneCelular2;
	
	@Column(name="TELEFONE_COMERCIAL")
	private String telefoneComercial;
	
	@Column(name="TELEFONE_RESIDENCIA")
	private String telefoneResidencial;
	
	@Column(name="ID_TERREIRO", nullable=false)
	private Integer idTerreiro;
	

	public Pessoa() {
		// TODO Auto-generated constructor stub
	}


	public Integer getIdPessoa() {
		return idPessoa;
	}


	public void setIdPessoa(Integer idPessoa) {
		this.idPessoa = idPessoa;
	}


	public String getCpf() {
		return cpf;
	}


	public void setCpf(String cpf) {
		this.cpf = cpf;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public String getEnderecoComercial() {
		return enderecoComercial;
	}


	public void setEnderecoComercial(String enderecoComercial) {
		this.enderecoComercial = enderecoComercial;
	}


	public String getEnderecoResidencial() {
		return enderecoResidencial;
	}


	public void setEnderecoResidencial(String enderecoResidencial) {
		this.enderecoResidencial = enderecoResidencial;
	}


	public Date getNascimento() {
		return nascimento;
	}


	public void setNascimento(Date nascimento) {
		this.nascimento = nascimento;
	}


	public String getNome() {
		return nome;
	}


	public void setNome(String nome) {
		this.nome = nome;
	}


	public String getProfissao() {
		return profissao;
	}


	public void setProfissao(String profissao) {
		this.profissao = profissao;
	}


	public String getTelefoneCelular1() {
		return telefoneCelular1;
	}


	public void setTelefoneCelular1(String telefoneCelular1) {
		this.telefoneCelular1 = telefoneCelular1;
	}


	public String getTelefoneCelular2() {
		return telefoneCelular2;
	}


	public void setTelefoneCelular2(String telefoneCelular2) {
		this.telefoneCelular2 = telefoneCelular2;
	}


	public String getTelefoneComercial() {
		return telefoneComercial;
	}


	public void setTelefoneComercial(String telefoneComercial) {
		this.telefoneComercial = telefoneComercial;
	}


	public String getTelefoneResidencial() {
		return telefoneResidencial;
	}


	public void setTelefoneResidencial(String telefoneResidencial) {
		this.telefoneResidencial = telefoneResidencial;
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
		result = prime * result + ((cpf == null) ? 0 : cpf.hashCode());
		result = prime * result + ((idPessoa == null) ? 0 : idPessoa.hashCode());
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
		Pessoa other = (Pessoa) obj;
		if (cpf == null) {
			if (other.cpf != null)
				return false;
		} else if (!cpf.equals(other.cpf))
			return false;
		if (idPessoa == null) {
			if (other.idPessoa != null)
				return false;
		} else if (!idPessoa.equals(other.idPessoa))
			return false;
		return true;
	}


	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Pessoa [idPessoa=");
		builder.append(idPessoa);
		builder.append(", cpf=");
		builder.append(cpf);
		builder.append(", email=");
		builder.append(email);
		builder.append(", enderecoComercial=");
		builder.append(enderecoComercial);
		builder.append(", enderecoResidencial=");
		builder.append(enderecoResidencial);
		builder.append(", nascimento=");
		builder.append(nascimento);
		builder.append(", nome=");
		builder.append(nome);
		builder.append(", profissao=");
		builder.append(profissao);
		builder.append(", telefoneCelular1=");
		builder.append(telefoneCelular1);
		builder.append(", telefoneCelular2=");
		builder.append(telefoneCelular2);
		builder.append(", telefoneComercial=");
		builder.append(telefoneComercial);
		builder.append(", telefoneResidencial=");
		builder.append(telefoneResidencial);
		builder.append(", idTerreiro=");
		builder.append(idTerreiro);
		builder.append("]");
		return builder.toString();
	}

	
	
}
