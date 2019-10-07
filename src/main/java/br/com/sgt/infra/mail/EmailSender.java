package br.com.sgt.infra.mail;

import java.io.Serializable;
import java.text.DateFormat;
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
import br.com.sgt.entities.Terreiro;
import br.com.sgt.service.api.TerreiroService;

public class EmailSender implements Serializable{

	
	private static final long serialVersionUID = 1L;
	
	@Inject
	private TerreiroService terreiroService;
	
	Terreiro terreiro = new Terreiro();

	private Recibo recibo;	

	public void enviar(Recibo recibo) {
		this.recibo = recibo;
		
		try {
			obterEmailSenha();
			
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
				                 return new PasswordAuthentication("secretaria@temploestreladooriente.com", 
				                 "caboclo7*");
				           }
				      });
			
			
			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress(this.recibo.getValorAutorizado().getSocio().getPessoa().getEmail()));
			Address[] toUser = InternetAddress.parse(this.recibo.getValorAutorizado().getSocio().getPessoa().getEmail());
			message.setRecipients(Message.RecipientType.TO, toUser);
			message.setSubject(this.recibo.getTerreiro().getNome() + " - Recibo de pagamento");
			message.setText("--------------------------------------------------------------\n" + "Recibo Nº: "
					+ this.recibo.getNumeroRecibo() + "\n" + "Referente à: "
					+ this.recibo.getValorAutorizado().getTarifa().getNomeTarifa() + "\n" + "Mês/Ano: "
					+ this.recibo.getMesBase() + "/" + recibo.getAnoBase() + "\n" + "Valor pago: R$ " + this.recibo.getValorRecibo()
					+ "\n" + "Data de pagamento: " + DateFormat.getDateInstance().format(this.recibo.getDataPagamento())
					+ "\n" + "--------------------------------------------------------------");

			Transport.send(message);
		} catch (MessagingException | RuntimeException e) {
			throw new RuntimeException(e.getMessage());
		}
	}
	
	private void obterEmailSenha() {
		try {
			recibo.setTerreiro(terreiroService.buscarTerreiro());
		} catch (RuntimeException e) {
			throw e;
		}
	}
}
