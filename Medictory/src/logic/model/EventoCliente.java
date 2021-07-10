package logic.model;

import java.util.ArrayList;
import java.util.List;

import logic.ingegnerizzazione.EventiUtenteBean;
import logic.ingegnerizzazione.Observable;
import logic.ingegnerizzazione.Observer;


public class EventoCliente implements Observable, Evento {
	private AbstractState state;
	private ArrayList<Observer> observers = new ArrayList<>();
	private String nome;
	private String descrizione;
	private String premio;
	private String inizio;
	private String fine;
	private String vincitore = null;
    private int livelloRichiesto;
    private boolean joined = false; //E' solo per l'utente
   
    public EventoCliente(String nome, String descrizione, String premio, String inizio, String fine, int lv) {
        this.state = new StatoIniziale(this);
        this.nome = nome;
        this.descrizione = descrizione;
        this.premio = premio;
        this.livelloRichiesto = lv;
        this.inizio = inizio;
        this.fine = fine;
        
    }
    
    /********************* FUNZIONI CHE CAMBIANO IMPLEMENTAZIONE IN BASE ALLO STATO ***********************************/
    
    @Override
    public void nextState() {
        this.state = this.state.nextState();
    }
    
        
	public boolean setJoined(Boolean bool) {
		
		this.joined = state.setJoined(bool);
		return this.joined;
	}
	
	public void addEventToPartecipatingList(List<EventiUtenteBean> list) {
		state.addEventToPartecipatingList(list);	
	}
	
	public void addEventToActiveEventList(List<EventiUtenteBean> list) {
		state.addEventToActiveEventList(list);
	}
	
	/********************* NON CAMBIANO IMPLEMENTAZIONE IN BASE ALLO STATO *****************************************/
	
	@Override
	public AbstractState getState() {
        return state;
    }
	
	 @Override
	 public String getVincitore() {
		 return this.vincitore;
	 }
	
	@Override
	public String getNome() {
		return nome;
	}
	
	@Override
	public String getPremio() {
		return premio;
	}
	
	@Override
	public String getDescrizione() {
		return descrizione;
	}
	
	@Override
	public String getFine() {
		return fine;
	}
	
	@Override
	public int getLivelloRichiesto() {
		return livelloRichiesto;
	}
	
	@Override
	public String getInizio() {
		return inizio;
	}

	@Override
	public void attach(Observer observer){   
		if (!observers.contains(observer))
			observers.add(observer);		
	}
	
	@Override
	public void detach(Observer observer) {
		observers.remove(observer);
		
	}
	@Override
	public void notifica(){
	    for (Observer observer : observers) {
	       observer.update();
	    }

	}

	public boolean isJoined() {
		return joined;
	}	
}
