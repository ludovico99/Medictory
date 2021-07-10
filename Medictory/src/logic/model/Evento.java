package logic.model;

import logic.ingegnerizzazione.Observer;

public interface Evento {
	 public void nextState();
	 public String getWinner();
	 public AbstractState getState();
	 public String getName();
	 public String getAward();
	 public String getDescription();
	 public String getEndDate();
	 public int getRequiredLevel();
	 public String getStartDate();
	 public void attach(Observer observer);
	 public void detach(Observer observer);
	 public void notifica();
}
