package logic.controller;


import java.util.ArrayList;
import java.util.List;

import logic.ingegnerizzazione.EventiUtenteBean;
import logic.ingegnerizzazione.InputException;
import logic.ingegnerizzazione.RequirementException;
import logic.model.AbstractState;
import logic.model.EventoCliente;
import logic.model.SessioneCliente;
import logic.model.StatoIniziale;
import logic.model.SvolgimentoEvento;

public class ControllerCustomerEvents {
	
	
	
	
	private boolean stoPartecipando(SessioneCliente s, EventoCliente e) { 
		
		if(s.getEventi() == null) return false; 
		
		for(EventoCliente myEvent: s.getEventi()) {
			
			if(myEvent.getNome().compareToIgnoreCase(e.getNome()) == 0) {		
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
			if (e.getNome().compareToIgnoreCase(nomeEvento) == 0) { 							//Data la farmacia il nome evento è unico
				
				if(e.getLivelloRichiesto() > s.getLivello()) {
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
			AbstractState state = eventi.get(i).getState();
			if(state.getClass() == StatoIniziale.class || state.getClass() == SvolgimentoEvento.class)
				list.add(new EventiUtenteBean(eventi.get(i).getNome(), eventi.get(i).getDescrizione(), eventi.get(i).getPremio(), eventi.get(i).getInizio() , eventi.get(i).getFine())); 
		}
	
		return list;
	}
	

	public List<EventiUtenteBean> findAllActiveEvents(SessioneCliente sessione) {
		List<EventiUtenteBean> list = new ArrayList<>();

		
		List<EventoCliente> eventi = sessione.getEventiAttiviFarmaciaAssociata();
		if (eventi != null) {
			for(int i=0; i<eventi.size(); i++) {
				AbstractState state = eventi.get(i).getState();
				if(state.getClass() == StatoIniziale.class || state.getClass() == SvolgimentoEvento.class){	
					EventiUtenteBean evento = new EventiUtenteBean(eventi.get(i).getNome(), eventi.get(i).getDescrizione(),eventi.get(i).getPremio(), eventi.get(i).getInizio() , eventi.get(i).getFine());
					evento.setRequisiti(Integer.toString(eventi.get(i).getLivelloRichiesto()));
					list.add(evento);
				}
			}
		}
		return list;
	}
}
