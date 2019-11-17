package br.com.sgt.pattern.startegy;

import java.io.Serializable;
import java.text.DateFormat;

import br.com.sgt.entities.Recibo;

public class CorpoDoEmail implements Serializable, MessageBodyStrategy {

	
	private static final long serialVersionUID = 1L;
	

	@Override
	public String setTextPagamento(Recibo recibo) {
		return
				"--------------------------------------------------------------\n" + "Recibo Nº: "
				+ recibo.getNumeroRecibo() + "\n" + "Referente à: "
				+ recibo.getValorAutorizado().getTarifa().getNomeTarifa() + "\n" + "Mês/Ano: "
				+ recibo.getMesBase() + "/" + recibo.getAnoBase() + "\n" + "Valor pago: R$ " + recibo.getValorRecibo()
				+ "\n" + "Data de pagamento: " + DateFormat.getDateInstance().format(recibo.getDataPagamento())
				+ "\n" + "--------------------------------------------------------------";
	}


	@Override
	public String setTextEstorno(Recibo recibo) {
		return
				"--------------------------------------------------------------\n" + "Estorno de Recibo "
				+ recibo.getNumeroRecibo() + "\n" + "Referente à: "
				+ recibo.getValorAutorizado().getTarifa().getNomeTarifa() + "\n" + "Mês/Ano: "
				+ recibo.getMesBase() + "/" + recibo.getAnoBase() + "\n" + "Valor pago: R$ " + recibo.getValorRecibo()
				+ "\n" + "Data de pagamento: " + DateFormat.getDateInstance().format(recibo.getDataPagamento())
				+ "\n" + "--------------------------------------------------------------";
	}

}
