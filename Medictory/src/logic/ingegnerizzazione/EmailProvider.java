package logic.ingegnerizzazione;

import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class EmailProvider {
	
	private EmailProvider() {
	    throw new IllegalStateException("Utility class");}
	
	public static Message sendEmail(String email) {
	

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
		
		return prepareMessage(session, email);
	}
	
	
	public static Message prepareMessage(Session session, String myEmail) {
		Message mex = new MimeMessage(session);
		try{
			
			mex.setFrom(new InternetAddress(myEmail));
				
			mex.setRecipient(Message.RecipientType.TO, new InternetAddress("ludovico.zarr@gmail.com"/*this.email*/));	//inserire l'email a cui inviare il messaggio
				
			
			
		} catch (MessagingException e) {
			e.printStackTrace();
		}
		return mex;
		
	}
}


