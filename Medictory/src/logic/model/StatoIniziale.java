package logic.model;

import java.util.ArrayList;
import java.util.List;

import logic.ingegnerizzazione.EventiUtenteBean;

//Created event, not started yet


public class StatoIniziale extends AbstractState {
	
	public StatoIniziale(Evento evento) {
		super(evento);
	}

	
	@Override
	public List<Cliente> customersToAward(String username) {
		return new ArrayList<>();
	}
	
	@Override
	public int setLivelloRichiesto(int lv) {
		return lv;
	}
	
    
	@Override
	public String setVincitore(String v) {
		return null;
	}
    
	@Override
	public String setFine(String f) {
		return f;
	}
    
	@Override
	public String setInizio(String i) {
		return i;
	}
    
	@Override
	public String setPremio(String premio) {
		return premio;
	}
    
	@Override
	public String setDescrizione(String ds) {
		return ds;
	}
    
	@Override
	public String setNome(String nome) {
		return nome;
	}
	
	@Override
	public boolean setJoined(Boolean bool) {
		return true;
	}
	
	@Override
	public boolean setDeleted(Boolean bool) {
		return true;
	}
    
	@Override
	public AbstractState nextState() {
		return new SvolgimentoEvento(evento);
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
