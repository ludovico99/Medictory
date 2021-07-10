package logic.model;

import java.util.ArrayList;
import java.util.List;

import logic.ingegnerizzazione.EventiUtenteBean;

//Ongoing event

public class SvolgimentoEvento extends AbstractState{
	
	SvolgimentoEvento(Evento evento) {
		super(evento);
	}
	
	@Override
	public List<Cliente> customersToAward(String username) {
		return new ArrayList<>();
	}
	
	
	@Override
	public int setLivelloRichiesto(int lv) {
		return -1;
	}
    
    
	@Override
	public String setVincitore(String v) {
		return null;
	}
    
	@Override
	public String setFine(String f) {
		return null;
	}
    
	@Override
	public String setInizio(String inizio) {
		return null;
	}
    
	@Override
	public String setPremio(String reward) {
		return null;
	}
    
	@Override
	public String setDescrizione(String description) {
		return null;
	}
    
	@Override
	public String setNome(String name) {
		return null;
	}
	
	@Override
	public boolean setJoined(Boolean bool) {
		return true;
	}
    
	@Override
	public boolean setDeleted(Boolean bool) {
		return false;
	}
	
	@Override
	public AbstractState nextState() {
		return new FineEvento(evento);
	}
	
	@Override
	public void addEventToPartecipatingList(List<EventiUtenteBean> list) {
		list.add(new EventiUtenteBean(evento.getName(), evento.getDescription(), evento.getAward(), evento.getStartDate() , evento.getEndDate())); 
		
	}
	
	@Override
	public void addEventToActiveEventList(List<EventiUtenteBean> list) {
		EventiUtenteBean event = new EventiUtenteBean(evento.getName(), evento.getDescription(),evento.getAward(), evento.getStartDate() , evento.getEndDate());
		event.setRequisiti(Integer.toString(evento.getRequiredLevel()));
		list.add(event);
	}

}
