package logic.model;
import javax.mail.*;
import java.util.ArrayList;
import java.util.Random;



public class Coupon extends Decorator {
	
	
	private Random r = new Random();
	
	


	
	public Coupon(Premiazione premiazione) {
		this.prem = premiazione;
	}

	@Override
	public Message premia() {
		Message mex = this.prem.premia();
		try {
			mex.setText(this.prem.getMessageHeader() + randomCoupon()+ ".\nLa Tua Farmacia");
		} catch (MessagingException e1) {
			e1.printStackTrace();
		}
		
		try {
			Transport.send(mex);
		} catch (MessagingException e) {
			
			e.printStackTrace();
		}
	
        return mex;

		
	}
	
	
	private String randomCoupon() {
		
		
		ArrayList<String> text = new ArrayList<>();
		
		text.add("Hai ottenuto un profumo in omaggio, passa in farmacia per ritirarlo");
		text.add("Hai ottenuto il copupon PRENDI 3 PAGHI 2, passa in farmacia per utilizzarlo");
		text.add("Hai ottenuto un set per la manicure in omaggio, passa in farmacia per ritirarlo");
		text.add("Hai ottenuto la nuovissima crema mani Yves Rocher in omaggio, passa in farmacia per ritirarlo");
		text.add("Hai ottenuto un prodotto a scelta in omaggio, passa in farmacia per ritirarlo");
		text.add("Hai ottenuto un set di shampoo in omaggio, passa in farmacia per ritirarlo");
		text.add("Hai ottenuto il 30% di sconto sul tuo prossimo acquisto (articoli della parafarmacia)");
		text.add("Hai ottenuto il 25% di sconto sul tuo prossimo acquisto (articoli della parafarmacia)");
		text.add("Hai ottenuto il 15% di sconto sul tuo prossimo acquisto (articoli della parafarmacia)");
		text.add("Hai ottenuto il 5% di sconto sul tuo prossimo acquisto (articoli della parafarmacia)");
		
		
		int selected = r.nextInt(10);
		
		return text.get(selected);
		
	}
	
	

}