package br.com.sgt.entities.dto;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;

import javax.inject.Inject;

import com.ibm.icu.math.BigDecimal;

import br.com.sgt.service.api.TerreiroService;

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
	
	@Inject
	private TerreiroService terreiroService;
	
	public ReciboDTO anoBase() {
		this.anoBase = anoCorrente();
		return this;
	}
	public ReciboDTO mesBase() {
		this.mesBase = mesCorrente();
		return this;
		
	}
	public ReciboDTO dataPagamento(Date dataPagamento) {
		this.dataPagamento = dataPagamento;
		return this;
	}
	public ReciboDTO formaPagamento(String formaPagamento) {
		this.formaPagamento = formaPagamento;
		return this;
	}
	public ReciboDTO numeroRecibo() {
		this.numeroRecibo = gerarNumeroRecibo();
		return this;
	}
	public ReciboDTO valorPago(BigDecimal valorPago) {
		this.valorPago = valorPago;
		return this;
	}
	public ReciboDTO valorAutorizado(int valorAutorizado) {
		this.valorAutorizado = valorAutorizado;
		return this;
	}
	public ReciboDTO terreiroNome() {
		this.terreiroNome = terreiroService.buscarTerreiro().getNome();
		return this;
	}
	public ReciboDTO terreiroEndereco() {
		this.terreiroEndereco = terreiroService.buscarTerreiro().getEndereco();
		return this;
	}
	public ReciboDTO terreiroSite() {
		this.terreiroSite = terreiroService.buscarTerreiro().getSite();
		return this;
	}
	public ReciboDTO terreiroEmail() {
		this.terreiroEmail = terreiroService.buscarTerreiro().getEmail();
		return this;
	}
	public ReciboDTO terreiroSenhaEmail() {
		this.terreiroSenhaEmail = terreiroService.buscarTerreiro().getSenhaEmail();
		return this;
	}
	public ReciboDTO terreiroBairro() {
		this.terreiroBairro = terreiroService.buscarTerreiro().getBairro();
		return this;
	}
	public ReciboDTO terreiroCidade() {
		this.terreiroCidade = terreiroService.buscarTerreiro().getCidade();
		return this;
	}
	public ReciboDTO terreiroUf() {
		this.terreiroUf = terreiroService.buscarTerreiro().getUf();
		return this;
	}
	public ReciboDTO terreiroCep() {
		this.terreiroCep = terreiroService.buscarTerreiro().getCep();
		return this;
	}
	public ReciboDTO terreiroTelefone() {
		this.terreiroTelefone = terreiroService.buscarTerreiro().getTelefone();
		return this;
	}
	public ReciboDTO socioNome(String socioNome) {
		this.socioNome = socioNome;
		return this;
	}
	public ReciboDTO socioCpf(String socioCpf) {
		this.socioCpf = socioCpf;
		return this;
	}
	public ReciboDTO tarifaDescricao(String tarifaDescricao) {
		this.tarifaDescricao = tarifaDescricao;
		return this;
	}

	
	public int getAnoBase() {
		return anoBase;
	}
	public int getMesBase() {
		return mesBase;
	}
	public Date getDataPagamento() {
		return dataPagamento;
	}
	public String getFormaPagamento() {
		return formaPagamento;
	}
	public String getNumeroRecibo() {
		return numeroRecibo;
	}
	public BigDecimal getValorPago() {
		return valorPago;
	}
	public int getValorAutorizado() {
		return valorAutorizado;
	}
	public String getTerreiroNome() {
		return terreiroNome;
	}
	public String getTerreiroEndereco() {
		return terreiroEndereco;
	}
	public String getTerreiroSite() {
		return terreiroSite;
	}
	public String getTerreiroEmail() {
		return terreiroEmail;
	}
	public String getTerreiroSenhaEmail() {
		return terreiroSenhaEmail;
	}
	public String getTerreiroBairro() {
		return terreiroBairro;
	}
	public String getTerreiroCidade() {
		return terreiroCidade;
	}
	public String getTerreiroUf() {
		return terreiroUf;
	}
	public String getTerreiroCep() {
		return terreiroCep;
	}
	public String getTerreiroTelefone() {
		return terreiroTelefone;
	}
	public String getSocioNome() {
		return socioNome;
	}
	public String getSocioCpf() {
		return socioCpf;
	}
	public String getTarifaDescricao() {
		return tarifaDescricao;
	}
	
	
	private int mesCorrente() {
		Calendar calendar = Calendar.getInstance();
		int mes = calendar.get(Calendar.MONTH) + 1;
		return mes;
	}
	
	private int anoCorrente() {
		Calendar calendar = Calendar.getInstance();
		int ano = calendar.get(Calendar.YEAR);
		return ano;
	}
	
	private String gerarNumeroRecibo() {
		Locale locale = new Locale("pt", "BR");
		GregorianCalendar calendar = new GregorianCalendar();
		SimpleDateFormat formatador = new SimpleDateFormat("yyMMddHHmmss", locale);
		return formatador.format(calendar.getTime());
	}

}
