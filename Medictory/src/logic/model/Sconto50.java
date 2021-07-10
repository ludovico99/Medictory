package logic.model;

import java.util.ArrayList;
import java.util.Random;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Transport;

public class Sconto50 extends Decorator {
	
	
	private Random r = new Random();
	
	public Sconto50(Premiazione premiazione){
		this.prem=premiazione;
	}

	@Override
	public Message premia() {
		Message mex = this.prem.premia();
		try {
			mex.setText(this.getMessageHeader() + randomSconto()+ ".\nLa Tua Farmacia");
		} catch (MessagingException ex) {
			ex.printStackTrace();
		}
		
		try {
			Transport.send(mex);
		} catch (MessagingException exception) {
			
			exception.printStackTrace();
		}
		
            return mex;
	}
	
	
	private String randomSconto() {
		
		ArrayList<String> text = new ArrayList<>();
		

		text.add("Hai ottenuto il 50% di sconto sulla linea di cosmetici PuroBio");
		text.add("Hai ottenuto il 50% di sconto sulla linea di cosmetici YvesRocher");
		text.add("Hai ottenuto il 50% di sconto sulla linea di cosmetici X");
		text.add("Hai ottenuto il 50% di sconto sulla linea di cosmetici Y");
		
		
		int selected = r.nextInt(10);
		
		return text.get(selected);
		
	}
	

}
