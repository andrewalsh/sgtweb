package br.com.sgt.pattern.builder;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;

import br.com.sgt.entities.ValorAutorizado;

public class ReciboBuilder {

	private String numRecibo;
	private int anoBase;
	private int mesBse;
	private Date dataPagamento;
	private String formaPagamento;
	private ValorAutorizado valorAutorizado;
	private int idTerreiro;
	
	
	
	public ReciboBuilder numeroDoRecibo() {
		this.numRecibo = gerarNumeroRecibo();
		return this;
	}
	
	public ReciboBuilder referenteAoAno(int anoBase) {
		this.anoBase = anoBase;
		return this;
	}
	
	public ReciboBuilder referenteAoMes(int anoBase) {
		this.anoBase = anoBase;
		return this;
	}
	
	public ReciboBuilder pagoEm(Date dataPagamento) {
		this.dataPagamento = dataPagamento;
		return this;
	}
	
	public ReciboBuilder paraValorAutorizaado(ValorAutorizado valorAutorizado) {
		this.valorAutorizado = valorAutorizado;
		return this;
	}
	
	public ReciboBuilder terreiroDeEmissao() {
		this.idTerreiro = 1;
		return this;
	}
	
	private String gerarNumeroRecibo() {
		Locale locale = new Locale("pt", "BR");
		GregorianCalendar calendar = new GregorianCalendar();
		SimpleDateFormat formatador = new SimpleDateFormat("yyMMddHHmmss", locale);
		return formatador.format(calendar.getTime());
	}
}
