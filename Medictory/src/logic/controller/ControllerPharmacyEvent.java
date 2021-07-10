package logic.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;
import logic.ingegnerizzazione.DateException;
import logic.ingegnerizzazione.InputException;
import logic.ingegnerizzazione.PharmacyAllEventBean;
import logic.model.AbstractState;
import logic.model.Cliente;
import logic.model.EventoFarmacia;
import logic.model.SessioneFarmacia;
import logic.model.StatoIniziale;
import logic.model.SvolgimentoEvento;

public class ControllerPharmacyEvent{
	private Random r = new Random();
	
	public void premiazione (SessioneFarmacia s) {
		List<Cliente> infoPartecipanti = null;
		List<EventoFarmacia> eventi = null;
		if(s.getEventi() == null) return;
		eventi = s.getEventi();
		for (EventoFarmacia e: eventi) {
			infoPartecipanti = e.customersToAward(s.getUsername());
			//controller behaves according to the state of the entity EVENT
			if (infoPartecipanti != null && !infoPartecipanti.isEmpty() && e.getWinner()==null) {
				int dimensione = infoPartecipanti.size();
			    int selected = r.nextInt(dimensione);
				e.setChanged(true);
				e.nextState();
				e.notifica();	
				Runnable prem = new ThreadPremiazioneEvento(infoPartecipanti.get(selected).getUsername(),infoPartecipanti.get(selected).getEmail(),e.getAward(),e);
				new Thread(prem).start();
			}
		}	
	}
	
	
	
	
	public EventoFarmacia deleteEvent (SessioneFarmacia s, String nomeEvento) throws InputException {
		if (s.getEventi() == null) return null;
		if (nomeEvento.compareToIgnoreCase("") == 0) throw new InputException("Non hai inserito nessun parametro");
		List<EventoFarmacia> eventi = s.getEventi();
		for (EventoFarmacia e : eventi) {
			if (e.getName().compareToIgnoreCase(nomeEvento) == 0) { //Data la farmacia il nome evento è unico
				
				if(e.setDeleted(true)) {
					return e;
				} else {
					throw new InputException("Impossibile eliminare un evento in corso o concluso");
				}
			}
		}
		
		throw new InputException("Impossibile trovare l'evento indicato");
	}
	
	
	
	public EventoFarmacia addEvent(SessioneFarmacia sessione, List<String> informazioni, int livello) throws InputException {
		
		EventoFarmacia ev = null;		
		Date oggi = new Date();
		
		
		String infoNome = informazioni.get(0); 
		String infoDettagli = informazioni.get(1);  
		String infoPremio = informazioni.get(2);
		String infoInizio = informazioni.get(3); 
		String infoFine = informazioni.get(4);
		
		
		
		
		if (sessione.getEventi() != null) {
			for(EventoFarmacia e: sessione.getEventi()) {
				if(e.getName().compareToIgnoreCase(infoNome) == 0) {
					throw new InputException("Hai gi&agrave creato un evento con questo nome");
				}
			}
		}
		try {
				
			SimpleDateFormat sdf = new  SimpleDateFormat("yyyy-MM-dd");
			Date start = sdf.parse(infoInizio);
			
			
			if (start.before(oggi)) {
				throw new DateException("Non puoi inserire un evento iniziato");
			}		
		} catch (ParseException e) {
		     e.printStackTrace();
		}
		
		ev = new EventoFarmacia(infoNome, infoDettagli, infoPremio, infoInizio, infoFine, livello);
		
		ev.setAddedRuntime(true);
		if(sessione.getEventi()== null) sessione.setEventi(new ArrayList<>());
		sessione.getEventi().add(ev);
		return ev;
	}
	
	
	public List<PharmacyAllEventBean> findEvent(SessioneFarmacia sessione) {	
		List<PharmacyAllEventBean> list =new ArrayList<>();

		List<EventoFarmacia> eventi = sessione.getEventi() ;
		if (eventi ==  null) return list;
		for(int i=0; i<eventi.size(); i++) {
			AbstractState state = eventi.get(i).getState();
		 if(!eventi.get(i).isDeleted() && (state.getClass() == StatoIniziale.class || state.getClass() == SvolgimentoEvento.class)){	
				list.add(new PharmacyAllEventBean(eventi.get(i).getName(), eventi.get(i).getDescription(), Integer.toString(eventi.get(i).getRequiredLevel()), eventi.get(i).getAward(), eventi.get(i).getStartDate() , eventi.get(i).getEndDate())); 
			}
		}
	
	return list;	
	
	}
		
}
	

