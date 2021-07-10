package logic.controller;


import java.util.ArrayList;
import java.util.List;

import logic.ingegnerizzazione.EventiUtenteBean;
import logic.ingegnerizzazione.InputException;
import logic.ingegnerizzazione.RequirementException;
import logic.model.EventoCliente;
import logic.model.SessioneCliente;


public class ControllerCustomerEvents {
	
	
	
	
	private boolean stoPartecipando(SessioneCliente s, EventoCliente e) { 
		
		if(s.getEventi() == null) return false; 
		
		for(EventoCliente myEvent: s.getEventi()) {
			
			if(myEvent.getName().compareToIgnoreCase(e.getName()) == 0) {		
				return true;
		
		
			}
		}
		return false;
	}
	

	public EventoCliente addMyEvent( SessioneCliente s, String nomeEvento) throws InputException,RequirementException {
		
		if (s.getEventiAttiviFarmaciaAssociata() == null) return null;
		List<EventoCliente> eventi = s.getEventiAttiviFarmaciaAssociata();
		if (nomeEvento.compareTo("")==0) throw new InputException("Non hai inserito nessun parametro");
		
		for (EventoCliente e : eventi) {
			if (e.getName().compareToIgnoreCase(nomeEvento) == 0) { 							//Data la farmacia il nome evento è unico
				
				if(e.getRequiredLevel() > s.getLivello()) {
					throw new RequirementException("Requisiti insoddisfatti: non puoi partecipare");
					
				
				} else {
					
					if (this.stoPartecipando(s, e)) throw new InputException("Stai gi&agrave partecipando a questo evento");
			
					return e;
				}
			}
		}
		throw new InputException("Evento inserito non trovato");
	}
	
	public List<EventiUtenteBean> findEvents(SessioneCliente s) {
	
		List<EventiUtenteBean> list = new ArrayList<>();
		if (s.getEventi() == null) return list;
		List<EventoCliente> eventi = s.getEventi() ;
	
		for(int i=0; i<eventi.size(); i++) {
			
			//chiamo questa funzione che ha un'implementazione diversa per ogni stato 
			//nella tabella verranno effettivamente inseriti solo gli eventi che non sono conclusi
			
			eventi.get(i).addEventToPartecipatingList(list);
		}
	
		return list;
	}
	

	public List<EventiUtenteBean> findAllActiveEvents(SessioneCliente sessione) {
		List<EventiUtenteBean> list = new ArrayList<>();

		
		List<EventoCliente> eventi = sessione.getEventiAttiviFarmaciaAssociata();
		if (eventi != null) {
			for(int i=0; i<eventi.size(); i++) {
				eventi.get(i).addEventToActiveEventList(list);
			}
		}
		return list;
	}
}
