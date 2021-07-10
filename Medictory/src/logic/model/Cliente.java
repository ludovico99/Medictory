package logic.model;

import java.util.ArrayList;
import java.util.List;

import logic.ingegnerizzazione.Observer;
import logic.ingegnerizzazione.Observable;

public class Cliente extends Utente implements Observable{
	private String farmaAssociata;
	private int punti;
	private int livello;
	private ArrayList<FarmacoCliente> farmaci;
	private ArrayList<EventoCliente> eventi;
	
	private ArrayList<Observer> observers = new ArrayList<>();
	
	
	public Cliente(String user, String pwd, String em) {
		super(user, pwd, em);
		this.punti = 0;
		this.livello = 0;
	}
	public Cliente(String user, String pwd, String em, String farma, int pt, int lv) {
		
		super(user, pwd, em);
		this.farmaAssociata = farma;
		this.punti = pt;
		this.livello = lv;
	}
	
	public void setPunti(int p){
		this.punti = p;
	}
	public void setLivello(int l){
		this.livello = l;
	}
	public void setFarmaciaAssociata(String f){
		this.farmaAssociata = f;
	}
	
	public int getPunti(){
		return this.punti;
	}
	public String getFarmaAssociata() {
		return farmaAssociata;
	}
	public void setFarmaAssociata(String farmaAssociata) {
		this.farmaAssociata = farmaAssociata;
	}
	public List<FarmacoCliente> getFarmaci() {
		return farmaci;
	}
	public void setFarmaci(List<FarmacoCliente> farmaci) {
		this.farmaci = (ArrayList<FarmacoCliente>) farmaci;
	}
	public List<EventoCliente> getEventi() {
		return eventi;
	}
	public void setEventi(List<EventoCliente> eventi) {
		this.eventi = (ArrayList<EventoCliente>) eventi;
	}
	public int getLivello(){
		return this.livello;
	}
		
	@Override
	public void attach(Observer obs){   //agg un osservatore alla lista
		if (!observers.contains(obs))
			observers.add(obs);		
	}
	
	@Override
	public void detach(Observer obs) {
		observers.remove(obs);
		
	}
	@Override
	public void notifica(){
	    for (Observer obs : observers) {
	    	obs.update();
	    }
	
	}
	
}
