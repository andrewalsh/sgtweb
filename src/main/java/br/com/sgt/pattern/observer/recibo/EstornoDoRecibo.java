package br.com.sgt.pattern.observer.recibo;

import java.io.Serializable;

import javax.inject.Inject;

import br.com.sgt.dao.tx.Transacional;
import br.com.sgt.entities.Recibo;
import br.com.sgt.repository.api.ReciboRepository;

public class EstornoDoRecibo implements Serializable, AcaoAposGerarRecibo{
	
	private static final long serialVersionUID = 1L;

	@Inject
	private ReciboRepository reciboRepository;
	

	@Override
	@Transacional
	public void executa(Recibo recibo) {
		try {
			reciboRepository.excluir(recibo);
		} catch (RuntimeException e) {
			throw new RuntimeException("Ocorreu um erro ao estornar o recibo "+e.getMessage());
		}
	}
	
	
	/*private void executaEstornoAnoDiferente(Recibo recibo) {
		try {
			recibo.setEstornado("S");
			ultimoPagamentoService.salvar(new UltimoPagamentoDaTarifa(
					recibo.getUltimoPagamento().getIdUltimoPagamento(),
					recibo.getValorAutorizado().getIdValorAutorizado(), 
					1, 
					recibo.getAnoBase() -1));
			reciboRepository.excluir(recibo);
		} catch (RuntimeException e) {
			throw new RuntimeException("Ocorreu um erro ao salvar o recibo no banco "+e.getMessage());
		}
	}

	
	private void executaEstornoMesmoAno(Recibo recibo) {
		try {
			recibo.setEstornado("S");
			ultimoPagamentoService.salvar(new UltimoPagamentoDaTarifa(
					recibo.getUltimoPagamento().getIdUltimoPagamento(),
					recibo.getValorAutorizado().getIdValorAutorizado(), 
					recibo.getMesBase() -1, 
					recibo.getAnoBase()));
			reciboRepository.excluir(recibo);
		} catch (RuntimeException e) {
			throw new RuntimeException("Ocorreu um erro ao salvar o recibo no banco "+e.getMessage());
		}
	}*/

	/*private int anoCorrente() {
		Calendar calendar = Calendar.getInstance();
		int ano = calendar.get(Calendar.YEAR);
		return ano;
	}*/

}