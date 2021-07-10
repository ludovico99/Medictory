package logic.model;

import javax.mail.Message;
import javax.mail.MessagingException;

import logic.ingegnerizzazione.EmailProvider;

public class PremioPassaggioDiLivello extends Premiazione {
	
	
	public PremioPassaggioDiLivello(String user,String em) {
		super();
		this.utente=user;
		this.email=em;
	}

	@Override
	public Message premia() {
		
		Message mex = EmailProvider.sendEmail(this.email);
		
		try {
			mex.setSubject("MEDICTORY LEVEL UP");
		} catch (MessagingException e) {
		
			e.printStackTrace();
		}
		
		this.setMessageHeader("Complimenti "+ utente +", hai superato il livello!\n");
		
		return mex;
		
	}
	
		
}
