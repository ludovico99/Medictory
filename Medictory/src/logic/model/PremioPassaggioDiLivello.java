package logic.model;

import java.util.Properties;
import java.util.logging.Logger;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class PremioPassaggioDiLivello extends Premiazione {
	
	
	public PremioPassaggioDiLivello(String utente,String email) {
		super();
		this.utente=utente;
		this.email=email;
	}

	@Override
	public Message premia() {
		
		final Logger log = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
        log.info("Preparing to send email....");
	
		Properties p = new Properties();
		
		p.put("mail.smtp.auth", "true");
		p.put("mail.smtp.starttls.enable", "true");
		p.put("mail.smtp.host", "smtp.gmail.com");
		p.put("mail.smtp.port", "587");
		p.put("mail.smtp.ssl.trust", "smtp.gmail.com");
		
		String myEmail = "medictoryISPW@gmail.com";
		String myPassword = "30elodecrew";												//inserire la password dell'email di medictory
		
		Session session = Session.getInstance(p, new Authenticator() {
			@Override
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(myEmail, myPassword);
			}
		});
		
		return prepareMessage(session, getMyEmail());
	}
	
	
	public Message prepareMessage(Session session, String myEmail) {
		Message mex = new MimeMessage(session);
		try{
			
			mex.setFrom(new InternetAddress(myEmail));
				
			mex.setRecipient(Message.RecipientType.TO, new InternetAddress("medictoryISPW@gmail.com"/*this.email*/));	//inserire l'email a cui inviare il messaggio
				
			mex.setSubject("MEDICTORY LEVEL UP");
				
			this.setMessageHeader("Complimenti "+ utente +", hai superato il livello!\n");
			
		} catch (MessagingException e) {
			e.printStackTrace();
		}
		return mex;
		
	}
	
}
