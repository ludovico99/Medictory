package logic.model;

import java.util.ArrayList;

import logic.ingegnerizzazione.Observable;
import logic.ingegnerizzazione.Observer;

public class FarmacoFarmacia extends Farmaco implements Observable{
	
	private ArrayList<Observer> observers = new ArrayList<>();
	private int totale;
	
	public FarmacoFarmacia(String nome, String descrizione, String scadenza, int quantita) {
		super(nome,descrizione,scadenza,quantita);
		
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

	public int getTotale() {
		return totale;
	}

	public void setTotale(int totale) {
		this.totale = totale;
	}
	
	
}
