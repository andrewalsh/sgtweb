package br.com.sgt.repository.filtro;

public class FiltroUsuario {
	
	private String login;
	private String senha;
	
	public FiltroUsuario() {
		
	}
	public FiltroUsuario(String login, String senha) {
		this.login = login;
		this.senha = senha;
	}
	public String getLogin() {
		return login;
	}
	public String getSenha() {
		return senha;
	}
	public void setLogin(String login) {
		this.login = login;
	}
	public void setSenha(String senha) {
		this.senha = senha;
	}
	
}
