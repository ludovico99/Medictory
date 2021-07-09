package logic.model;

import logic.ingegnerizzazione.Observer;

public interface Evento {
	 public void nextState();
	 public String getVincitore();
	 public AbstractState getState();
	 public String getNome();
	 public String getPremio();
	 public String getDescrizione();
	 public String getFine();
	 public int getLivelloRichiesto();
	 public String getInizio();
	 public void attach(Observer observer);
	 public void detach(Observer observer);
	 public void notifica();
}
