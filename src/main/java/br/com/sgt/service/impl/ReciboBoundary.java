package br.com.sgt.service.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

import javax.inject.Inject;

import com.ibm.icu.math.BigDecimal;

import br.com.sgt.entities.Recibo;
import br.com.sgt.entities.ValorAutorizado;
import br.com.sgt.entities.dto.ReciboDTO;
import br.com.sgt.pattern.observer.recibo.AcaoAposGerarRecibo;
import br.com.sgt.pattern.observer.recibo.EnviarEmail;
import br.com.sgt.pattern.observer.recibo.ImprimirRecibo;
import br.com.sgt.pattern.observer.recibo.SalvarReciboNoBanco;
import br.com.sgt.repository.api.ReciboRepository;
import br.com.sgt.repository.filtro.FiltroRecibo;
import br.com.sgt.service.api.ReciboService;
import br.com.sgt.service.api.TerreiroService;

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
	private ImprimirRecibo imprimirRecibo;
	
	private List<AcaoAposGerarRecibo> acoes = new ArrayList<AcaoAposGerarRecibo>();
	
	
	@Override
	
	public void salvar(Recibo recibo) {
		try {
			acoesASeremExecutadas();
			for (AcaoAposGerarRecibo acao : acoes) {
				acao.executa(recibo);
			}
		} catch (Exception e) {
			throw new RuntimeException("Ocorreu um erro "+e.getMessage());
		}
		
	}

	@Override
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
	}
	
	@Override
	public List<Recibo> listar(FiltroRecibo filtro) {
		try {
			return reciboRepository.buscarPorFiltro(filtro);
		} catch (RuntimeException e) {
			throw e;
		}
	}
	
	
	public void enviar(Recibo recibo) {
		//
	}
	
	
	public ReciboDTO gerarReciboDTO(ValorAutorizado valorAutorizado) {
		return new ReciboDTO()
				.anoBase()
				.mesBase()
				.numeroRecibo()
				.valorAutorizado(valorAutorizado.getIdValorAutorizado().intValue())
				.dataPagamento(new Date())
				.formaPagamento("Dinheiro")
				.terreiroBairro()
				.terreiroCep()
				.terreiroCidade()
				.terreiroEmail()
				.terreiroEndereco()
				.terreiroNome()
				.terreiroSenhaEmail()
				.terreiroSite()
				.terreiroTelefone()
				.terreiroUf()
				.valorPago(new BigDecimal(70))
				;
	}
	
	private void acoesASeremExecutadas() {
		acoes.add(salvarReciboNoBanco);
		acoes.add(enviarEmail);
		acoes.add(imprimirRecibo);
	}
}
