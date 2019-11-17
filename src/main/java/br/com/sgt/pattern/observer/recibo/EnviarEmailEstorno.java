package br.com.sgt.pattern.observer.recibo;

import java.io.Serializable;
import java.text.DateFormat;
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

import br.com.sgt.entities.Recibo;
import br.com.sgt.service.api.TerreiroService;

public class EnviarEmailEstorno implements Serializable, AcaoAposGerarRecibo{
	
	private static final long serialVersionUID = 1L;
	
	@Inject
	private TerreiroService terreiroService;



	@Override
	public void executa(Recibo recibo) {
		if(socioPossuiEmail(recibo)) {
			enviar(recibo);
		}
	}
	
	private boolean socioPossuiEmail(Recibo recibo) {
		if(Objects.nonNull(recibo.getValorAutorizado().getSocio().getPessoa().getEmail()))
			return true;
		else
			return false;
	}
	
	private void enviar(Recibo recibo) {
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
			message.setSubject(terreiroService.buscarTerreiro().getNome());
			message.setText("--------------------------------------------------------------\n" + "Estorno de Recibo "
					+ recibo.getNumeroRecibo() + "\n" + "Referente à: "
					+ recibo.getValorAutorizado().getTarifa().getNomeTarifa() + "\n" + "Mês/Ano: "
					+ recibo.getMesBase() + "/" + recibo.getAnoBase() + "\n" + "Valor pago: R$ " + recibo.getValorRecibo()
					+ "\n" + "Data de pagamento: " + DateFormat.getDateInstance().format(recibo.getDataPagamento())
					+ "\n" + "--------------------------------------------------------------");

			Transport.send(message);
		} catch (MessagingException | RuntimeException e) {
			throw new RuntimeException("Ocorreu um erro ao enviar o e-mail "+e.getMessage());
		}
	}


}
