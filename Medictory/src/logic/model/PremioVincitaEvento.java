package logic.model;

import javax.mail.Message;
import javax.mail.MessagingException;


import logic.ingegnerizzazione.EmailProvider;

public class PremioVincitaEvento extends Premiazione {
	
	
	
	public PremioVincitaEvento(String utente,String email) {
		super();
		this.utente=utente;
		this.email=email;
	}

	@Override
		public Message premia() {
			
			Message mex = EmailProvider.sendEmail(this.email);
			
			try {
				mex.setSubject("MEDICTORY LEVEL UP");
				
				
			} catch (MessagingException e) {
			
				e.printStackTrace();
			}
			
			this.setMessageHeader("Complimenti "+ utente +", sei il vincitore dell'evento!\n");
			
			return mex;
			
		}
}
