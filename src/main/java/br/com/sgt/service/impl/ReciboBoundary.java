package br.com.sgt.service.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import br.com.sgt.entities.Recibo;
import br.com.sgt.pattern.observer.recibo.AcaoAposGerarRecibo;
import br.com.sgt.pattern.observer.recibo.EnviarEmail;
import br.com.sgt.pattern.observer.recibo.EnviarEmailEstorno;
import br.com.sgt.pattern.observer.recibo.EstornoDoRecibo;
import br.com.sgt.pattern.observer.recibo.ImprimirRecibo;
import br.com.sgt.pattern.observer.recibo.SalvarReciboNoBanco;
import br.com.sgt.repository.api.ReciboRepository;
import br.com.sgt.repository.filtro.FiltroRecibo;
import br.com.sgt.service.api.ReciboService;

public class ReciboBoundary implements Serializable, ReciboService{

	private static final long serialVersionUID = 1L;
	
	@Inject
	private ReciboRepository reciboRepository;

	/*
	@Inject
	private UltimoPagamentoService ultimoPagamentoService;*/
	
	@Inject
	private SalvarReciboNoBanco salvarReciboNoBanco;
	
	@Inject
	private EnviarEmail enviarEmail;
	
	@Inject 
	private EnviarEmailEstorno enviarEmailEstorno;
	
	@Inject
	private ImprimirRecibo imprimirRecibo;
	
	@Inject
	private EstornoDoRecibo estorno;
	
	private List<AcaoAposGerarRecibo> acoes = new ArrayList<AcaoAposGerarRecibo>();
	
	
	@Override
	
	public void salvar(Recibo recibo) {
		try {
			acoesASeremExecutadas();
			for (AcaoAposGerarRecibo acao : acoes) {
				acao.executa(recibo);
			}
		} catch (RuntimeException e) {
			throw new RuntimeException("Ocorreu um erro "+e.getMessage());
		}
	}
	
	@Override
	public void estorno(Recibo recibo) {
		try {
			acoesASeremExecutadasParaEstorno();
			for (AcaoAposGerarRecibo acao : acoes) {
				acao.executa(recibo);
			}
		} catch (RuntimeException e) {
			throw new RuntimeException("Ocorreu um erro "+e.getMessage());
		}
	}
	
	@Override
	public Recibo ultimoPagamentoDaTarifa(Long idValorAutorizado) {
		try {
			return reciboRepository.ultimoPagamentoDaTarifa(idValorAutorizado);
		} catch (RuntimeException e) {
			throw e;
		}
	}

	/*@Override
	public void enviarEmail(Recibo recibo) {
		if(Objects.nonNull(recibo.getValorAutorizado().getSocio().getPessoa().getEmail())) {
			try {
				enviar(recibo);
			} catch (Exception e) {
				throw e;
			}
		}
		else {
			return;
		}
	}*/
	
	@Override
	public List<Recibo> listar(FiltroRecibo filtro) {
		try {
			return reciboRepository.buscarPorFiltro(filtro);
		} catch (RuntimeException e) {
			throw e;
		}
	}

	
	
	private void acoesASeremExecutadas() {
		acoes.add(salvarReciboNoBanco);
		acoes.add(enviarEmail);
		acoes.add(imprimirRecibo);
	}
	
	private void acoesASeremExecutadasParaEstorno() {
		acoes.add(estorno);
		//acoes.add(enviarEmailEstorno);
	}

}
