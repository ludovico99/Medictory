package logic.model;

import java.util.ArrayList;
import java.util.List;

import logic.ingegnerizzazione.Observable;
import logic.ingegnerizzazione.Observer;


public class EventoFarmacia implements Observable, Evento {
	
	private AbstractState stato;
	private ArrayList<Observer> observers = new ArrayList<>();
	private String name;
	private String description;
	private String award;
	private String startDate;
	private String endDate;
	private String winner = null;
    private int requiredLevel;
    private boolean addedRuntime = false; //E' solo per la farmacia
    private boolean changed = false;
    private boolean deleted = false; // E' solo per la farmacia
   

    public EventoFarmacia(String eventName, String description, String award, String startDate, String endDate, int level) {
        this.stato = new StatoIniziale(this);
        this.name = eventName;
        this.description = description;
        this.award = award;
        this.requiredLevel = level;
        this.startDate = startDate;
        this.endDate = endDate;
        
    }
    
    /********************* FUNZIONI CHE CAMBIANO IMPLEMENTAZIONE IN BASE ALLO STATO ***********************************/
    
    @Override
    public void nextState() {
        this.stato = this.stato.nextState();
    }
    
	public List<Cliente> customersToAward(String username) {
		return this.stato.customersToAward(username);
	}
    

    public boolean setRequiredLevel(int livelloRichiesto) {
		if(stato.setLivelloRichiesto(livelloRichiesto) > 0) {
			this.requiredLevel = stato.setLivelloRichiesto(livelloRichiesto);
			return true;
		}
		else return false;
	}
    
    

	public String setWinner(String vincitore) {
		this.winner = stato.setVincitore(vincitore);
		return this.winner;
	}
    

	public boolean setEndDate(String fine) {
		if( stato.setFine(fine) != null) {
			this.endDate = stato.setFine(fine);
			this.changed = true;
			return true;
		}
		else return false;
	}
    
 
	public boolean setStartDate(String inizio) {
		
		if(stato.setInizio(inizio) != null) {
			this.startDate = stato.setInizio(inizio);
			return true;
		}
		else return false;
	}
	
  
	public boolean setAward(String premio) {
		if(stato.setPremio(premio) != null) {
			this.award = stato.setPremio(premio);
			return true;
		}
		else return false;
	}
    
  
	public boolean setDescription(String descrizione) {
		if( stato.setDescrizione(descrizione) != null) {
			this.description = stato.setDescrizione(descrizione);
			return true;
		}
		else return false;
	}
    
    
	public boolean setEventName(String nome) {
		if( stato.setNome(nome) != null) {
			this.name = stato.setNome(nome);
			return true;
		}
		else return false;
	}
	
    
	public boolean setDeleted(boolean deleted) {
		
		this.deleted = stato.setDeleted(deleted);
		return this.deleted;

	}
	
	
	/********************* NON CAMBIANO IMPLEMENTAZIONE IN BASE ALLO STATO *****************************************/
	@Override
	public AbstractState getState() {
        return stato;
    }
	
	//@Override
    public String getWinner() {
    	return this.winner;
	}
    
	
	@Override
	public String getName() {
		return name;
	}
	
	@Override
	public String getAward() {
		return award;
	}
	
	@Override
	public String getDescription() {
		return description;
	}
	
	@Override
	public String getEndDate() {
		return endDate;
	}
	
	@Override
	public int getRequiredLevel() {
		return requiredLevel;
	}
	@Override
	public String getStartDate() {
		return startDate;
	}

	@Override
	public void attach(Observer observe){   
		if (!observers.contains(observe))
			observers.add(observe);		
	}
	
	@Override
	public void detach(Observer observe) {
		observers.remove(observe);
		
	}
	@Override
	public void notifica(){
	    for (Observer observe : observers) {
	    	observe.update();
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

