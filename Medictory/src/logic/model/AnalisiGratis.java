package logic.model;

import java.util.ArrayList;
import java.util.Random;
import java.util.logging.Logger;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Transport;

public class AnalisiGratis extends Decorator {
	
	
	private Random r = new Random();
	
	
	public AnalisiGratis(Premiazione premiazione) {
		this.prem=premiazione;
	}

	@Override
	public Message premia() {
		Message mex = this.prem.premia();
		try {
			mex.setText(this.getMessageHeader() + randomAnalisi()+ ".\nLa Tua Farmacia");
		} catch (MessagingException e1) {
			e1.printStackTrace();
		}
		
		try {
			Transport.send(mex);
		} catch (MessagingException e) {
			
			e.printStackTrace();
		}
		final Logger log = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
        log.info("TCP mandaa");
        log.info("Sent message successfully....");
        return mex;
	
	}
	
	private String randomAnalisi() {
		
		
		ArrayList<String> text = new ArrayList<>();
		
		
		text.add("Hai ottenuto le ANALISI X");
		text.add("Hai ottenuto le ANALISI Y");
		text.add("Hai ottenuto le ANALISI Z");
		text.add("Hai ottenuto le ANALISI K");
		
		
		int selected = r.nextInt(10);
		
		return text.get(selected);
		
	}

}