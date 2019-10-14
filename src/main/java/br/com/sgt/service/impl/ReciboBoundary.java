package br.com.sgt.service.impl;

import java.io.Serializable;
import java.text.DateFormat;
import java.util.List;
import java.util.Objects;
import java.util.Properties;

import javax.inject.Inject;
import javax.mail.Address;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import br.com.sgt.dao.tx.Transacional;
import br.com.sgt.entities.Recibo;
import br.com.sgt.entities.UltimoPagamentoDaTarifa;
import br.com.sgt.repository.api.ReciboRepository;
import br.com.sgt.repository.filtro.FiltroRecibo;
import br.com.sgt.service.api.ReciboService;
import br.com.sgt.service.api.TerreiroService;
import br.com.sgt.service.api.UltimoPagamentoService;

public class ReciboBoundary implements Serializable, ReciboService{

	private static final long serialVersionUID = 1L;
	
	@Inject
	private ReciboRepository reciboRepository;
	
	@Inject
	private UltimoPagamentoService ultimoPagamentoService;

	@Inject
	private TerreiroService terreiroService;
	
	
	@Override
	@Transacional
	public Recibo salvar(Recibo recibo) {
		try {
			recibo.setTerreiro(terreiroService.buscarTerreiro());
			recibo.setValorRecibo(recibo.getValorAutorizado().getValorLiquido());
			ultimoPagamentoService.salvar(new UltimoPagamentoDaTarifa(recibo.getValorAutorizado().getIdValorAutorizado(), 
					recibo.getMesBase(), recibo.getAnoBase()));
			return reciboRepository.salavar(recibo);
		} catch (RuntimeException e) {
			throw e;
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
		try {
			Properties properties = new Properties();
			properties.put("mail.smtp.host", "smtp.gmail.com");
			properties.put("mail.smtp.socketFactory.port", "465");
			properties.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
			properties.put("mail.smtp.auth", "true");
			properties.put("mail.smtp.port", "465");
			
			Session session = Session.getDefaultInstance(properties,
				      new javax.mail.Authenticator() {
				           protected PasswordAuthentication getPasswordAuthentication() 
				           {
				                 return new PasswordAuthentication(terreiroService.buscarTerreiro().getEmail(), 
				                		 terreiroService.buscarTerreiro().getSenhaEmail());
				           }
				      });
			
			session.setDebug(true);
			
			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress(recibo.getValorAutorizado().getSocio().getPessoa().getEmail()));
			Address[] toUser = InternetAddress.parse(recibo.getValorAutorizado().getSocio().getPessoa().getEmail());
			message.setRecipients(Message.RecipientType.TO, toUser);
			message.setSubject(terreiroService.buscarTerreiro().getNome() + " - Recibo de pagamento");
			message.setText("--------------------------------------------------------------\n" + "Recibo Nº: "
					+ recibo.getNumeroRecibo() + "\n" + "Referente à: "
					+ recibo.getValorAutorizado().getTarifa().getNomeTarifa() + "\n" + "Mês/Ano: "
					+ recibo.getMesBase() + "/" + recibo.getAnoBase() + "\n" + "Valor pago: R$ " + recibo.getValorRecibo()
					+ "\n" + "Data de pagamento: " + DateFormat.getDateInstance().format(recibo.getDataPagamento())
					+ "\n" + "--------------------------------------------------------------");

			Transport.send(message);
		} catch (MessagingException | RuntimeException e) {
			throw new RuntimeException(e.getMessage());
		}
	}
}
