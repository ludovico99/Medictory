package logic.model;

import java.util.List;

import logic.ingegnerizzazione.EventiUtenteBean;

public abstract class AbstractState/*State*/ {
	protected Evento evento;
	
	protected AbstractState(Evento evento2) {
		this.evento= evento2;
		
	}

	 public abstract int setLivelloRichiesto(int lv);
	 public abstract List<Cliente> customersToAward(String username);
	 public abstract String setVincitore(String v);
	 public abstract String setFine(String f);
	 public abstract String setInizio(String i);
	 public abstract String setPremio(String premio);
  	 public abstract String setDescrizione(String ds);
	 public abstract String setNome(String nome);
	 public abstract boolean setJoined(Boolean bool);
	 public abstract boolean setDeleted(Boolean bool);
	 public abstract AbstractState nextState();
	 public abstract void addEventToPartecipatingList(List<EventiUtenteBean> list);
	 public abstract void addEventToActiveEventList(List<EventiUtenteBean> list);


	
    
}
