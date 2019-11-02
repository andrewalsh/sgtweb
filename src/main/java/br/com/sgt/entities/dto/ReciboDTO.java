package br.com.sgt.entities.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class ReciboDTO implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private int anoBase;
	private int mesBase;
	private Date dataPagamento;
	private String formaPagamento;
	private String numeroRecibo;
	private BigDecimal valorPago;
	private int valorAutorizado;
	private String terreiroNome;
	private String terreiroEndereco;
	private String terreiroSite;
	private String terreiroEmail;
	private String terreiroSenhaEmail;
	private String terreiroBairro;
	private String terreiroCidade;
	private String terreiroUf;
	private String terreiroCep;
	private String terreiroTelefone;
	private String socioNome;
	private String socioCpf;
	private String tarifaDescricao;
	public int getAnoBase() {
		return anoBase;
	}
	public void setAnoBase(int anoBase) {
		this.anoBase = anoBase;
	}
	public int getMesBase() {
		return mesBase;
	}
	public void setMesBase(int mesBase) {
		this.mesBase = mesBase;
	}
	public Date getDataPagamento() {
		return dataPagamento;
	}
	public void setDataPagamento(Date dataPagamento) {
		this.dataPagamento = dataPagamento;
	}
	public String getFormaPagamento() {
		return formaPagamento;
	}
	public void setFormaPagamento(String formaPagamento) {
		this.formaPagamento = formaPagamento;
	}
	public String getNumeroRecibo() {
		return numeroRecibo;
	}
	public void setNumeroRecibo(String numeroRecibo) {
		this.numeroRecibo = numeroRecibo;
	}
	public BigDecimal getValorPago() {
		return valorPago;
	}
	public void setValorPago(BigDecimal valorPago) {
		this.valorPago = valorPago;
	}
	public int getValorAutorizado() {
		return valorAutorizado;
	}
	public void setValorAutorizado(int valorAutorizado) {
		this.valorAutorizado = valorAutorizado;
	}
	public String getTerreiroNome() {
		return terreiroNome;
	}
	public void setTerreiroNome(String terreiroNome) {
		this.terreiroNome = terreiroNome;
	}
	public String getTerreiroEndereco() {
		return terreiroEndereco;
	}
	public void setTerreiroEndereco(String terreiroEndereco) {
		this.terreiroEndereco = terreiroEndereco;
	}
	public String getTerreiroSite() {
		return terreiroSite;
	}
	public void setTerreiroSite(String terreiroSite) {
		this.terreiroSite = terreiroSite;
	}
	public String getTerreiroEmail() {
		return terreiroEmail;
	}
	public void setTerreiroEmail(String terreiroEmail) {
		this.terreiroEmail = terreiroEmail;
	}
	public String getTerreiroSenhaEmail() {
		return terreiroSenhaEmail;
	}
	public void setTerreiroSenhaEmail(String terreiroSenhaEmail) {
		this.terreiroSenhaEmail = terreiroSenhaEmail;
	}
	public String getTerreiroBairro() {
		return terreiroBairro;
	}
	public void setTerreiroBairro(String terreiroBairro) {
		this.terreiroBairro = terreiroBairro;
	}
	public String getTerreiroCidade() {
		return terreiroCidade;
	}
	public void setTerreiroCidade(String terreiroCidade) {
		this.terreiroCidade = terreiroCidade;
	}
	public String getTerreiroUf() {
		return terreiroUf;
	}
	public void setTerreiroUf(String terreiroUf) {
		this.terreiroUf = terreiroUf;
	}
	public String getTerreiroCep() {
		return terreiroCep;
	}
	public void setTerreiroCep(String terreiroCep) {
		this.terreiroCep = terreiroCep;
	}
	public String getTerreiroTelefone() {
		return terreiroTelefone;
	}
	public void setTerreiroTelefone(String terreiroTelefone) {
		this.terreiroTelefone = terreiroTelefone;
	}
	public String getSocioNome() {
		return socioNome;
	}
	public void setSocioNome(String socioNome) {
		this.socioNome = socioNome;
	}
	public String getSocioCpf() {
		return socioCpf;
	}
	public void setSocioCpf(String socioCpf) {
		this.socioCpf = socioCpf;
	}
	public String getTarifaDescricao() {
		return tarifaDescricao;
	}
	public void setTarifaDescricao(String tarifaDescricao) {
		this.tarifaDescricao = tarifaDescricao;
	}
	
	
	
}
