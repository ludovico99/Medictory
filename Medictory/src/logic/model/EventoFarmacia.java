package logic.model;

import java.util.ArrayList;
import java.util.List;

import logic.ingegnerizzazione.Observable;
import logic.ingegnerizzazione.Observer;


public class EventoFarmacia implements Observable, Evento {
	
	private AbstractState state;
	private ArrayList<Observer> observers = new ArrayList<>();
	private String nome;
	private String descrizione;
	private String premio;
	private String inizio;
	private String fine;
	private String vincitore = null;
    private int livelloRichiesto;
    private boolean addedRuntime = false; //E' solo per la farmacia
    private boolean changed = false;
    private boolean deleted = false; // E' solo per la farmacia
   

    public EventoFarmacia(String nome, String descrizione, String premio, String inizio, String fine, int lv) {
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
    
	public List<Cliente> customersToAward(String username) {
		return this.state.customersToAward(username);
	}
    

    public boolean setLivelloRichiesto(int livelloRichiesto) {
		if(state.setLivelloRichiesto(livelloRichiesto) > 0) {
			this.livelloRichiesto = state.setLivelloRichiesto(livelloRichiesto);
			return true;
		}
		else return false;
	}
    
    

	public String setVincitore(String vincitore) {
		this.vincitore = state.setVincitore(vincitore);
		return this.vincitore;
	}
    

	public boolean setFine(String fine) {
		if( state.setFine(fine) != null) {
			this.fine = state.setFine(fine);
			this.changed = true;
			return true;
		}
		else return false;
	}
    
 
	public boolean setInizio(String inizio) {
		
		if(state.setInizio(inizio) != null) {
			this.inizio = state.setInizio(inizio);
			return true;
		}
		else return false;
	}
	
  
	public boolean setPremio(String premio) {
		if(state.setPremio(premio) != null) {
			this.premio = state.setPremio(premio);
			return true;
		}
		else return false;
	}
    
  
	public boolean setDescrizione(String descrizione) {
		if( state.setDescrizione(descrizione) != null) {
			this.descrizione = state.setDescrizione(descrizione);
			return true;
		}
		else return false;
	}
    
    
	public boolean setNome(String nome) {
		if( state.setNome(nome) != null) {
			this.nome = state.setNome(nome);
			return true;
		}
		else return false;
	}
	
    
	public boolean setDeleted(boolean deleted) {
		
		this.deleted = state.setDeleted(deleted);
		return this.deleted;

	}
	
	
	/********************* NON CAMBIANO IMPLEMENTAZIONE IN BASE ALLO STATO *****************************************/
	@Override
	public AbstractState getState() {
        return state;
    }
	
	//@Override
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
	
	public boolean isAddedRuntime() {
			return addedRuntime;
	}

	public void setAddedRuntime(boolean addedRuntime) {
		this.addedRuntime = addedRuntime;
	}

	public boolean isChanged() {
		return changed;
	}

	public void setChanged(boolean changed) {
		this.changed = changed;
	}

	public boolean isDeleted() {
		return deleted;
	}

}

