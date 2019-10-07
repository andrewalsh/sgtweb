package br.com.sgt.pattern.builder;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Locale;

import br.com.sgt.entities.Recibo;
import br.com.sgt.entities.Socio;
import br.com.sgt.entities.Tarifa;
import br.com.sgt.entities.ValorAutorizado;

public class ReciboBuilder {

	private Recibo recibo = new Recibo();
	
	public ReciboBuilder(){
		recibo.setValorAutorizado(new ValorAutorizado());
		recibo.getValorAutorizado().setTarifa(new Tarifa());
		recibo.getValorAutorizado().setSocio(new Socio());
		recibo.setAnoBase(anoCorrente());
		recibo.setMesBase(mesCorrente());
		recibo.setNumeroRecibo(gerarNumeroRecibo());
		recibo.setIdTerreiro(1);
		recibo.setDataPagamento(Calendar.getInstance().getTime());
	}
	
	public Recibo gerar() {
		return new Recibo(recibo);
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
