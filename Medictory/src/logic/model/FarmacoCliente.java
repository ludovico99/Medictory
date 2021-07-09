package logic.model;

import java.util.ArrayList;


import logic.ingegnerizzazione.Observable;
import logic.ingegnerizzazione.Observer;



public class FarmacoCliente extends Farmaco implements Observable{
	
	private ArrayList<Observer> observers = new ArrayList<>();
	private String stato;
	
	public FarmacoCliente(String nome, String descrizione, String scadenza, int quantita) {
			super(nome, descrizione, scadenza, quantita);
			
	}
	


	public String getStato() {
		return stato;
	}

	public void setStato(String stato) {
		this.stato = stato;
	}
	
	@Override
	public void attach(Observer observer){   //agg un osservatore alla lista
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
	
}
