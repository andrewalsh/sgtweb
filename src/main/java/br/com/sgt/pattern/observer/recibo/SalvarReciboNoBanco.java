package br.com.sgt.pattern.observer.recibo;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;
import java.util.Locale;

import javax.inject.Inject;

import br.com.sgt.dao.tx.Transacional;
import br.com.sgt.entities.Recibo;
import br.com.sgt.entities.UltimoPagamentoDaTarifa;
import br.com.sgt.repository.api.ReciboRepository;
import br.com.sgt.service.api.TerreiroService;
import br.com.sgt.service.api.UltimoPagamentoService;

public class SalvarReciboNoBanco implements Serializable, AcaoAposGerarRecibo{

	@Inject
	private ReciboRepository reciboRepository;
	
	@Inject
	private UltimoPagamentoService ultimoPagamentoService;
	
	//@Inject
	//private TerreiroService terreiroService;
	
	private static final long serialVersionUID = 1L;

	@Override
	@Transacional
	public void executa(Recibo recibo) {
		try {
			//recibo.setTerreiro(terreiroService.buscarTerreiro());
			recibo.setValorRecibo(recibo.getValorAutorizado().getValorLiquido());
			recibo.setNumeroRecibo(gerarNumeroRecibo());
			ultimoPagamentoService.salvar(new UltimoPagamentoDaTarifa(
					recibo.getUltimoPagamento().getIdUltimoPagamento(),
					recibo.getValorAutorizado().getIdValorAutorizado(), 
					recibo.getMesBase(), 
					recibo.getAnoBase()));
			reciboRepository.salavar(recibo);
		} catch (RuntimeException e) {
			throw new RuntimeException("Ocorreu um erro ao salvar o recibo no banco "+e.getMessage());
		}
	}

	private String gerarNumeroRecibo() {
		Locale locale = new Locale("pt", "BR");
		GregorianCalendar calendar = new GregorianCalendar();
		SimpleDateFormat formatador = new SimpleDateFormat("yyMMddHHmmss", locale);
		return formatador.format(calendar.getTime());
	}
	
}
