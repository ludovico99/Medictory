package logic.model;

import java.util.ArrayList;
import java.util.List;


public class SessioneCliente extends Sessione  {
	
	private ArrayList<FarmacoCliente> farmaci;
	private ArrayList<EventoCliente> eventiAttiviFarmaciaAssociata;
	private int punteggio;
	private int livello;
	private String email;
	private String farmaciaAssociata;
	private int qtaVerificate = 0;
	private ArrayList<EventoCliente> eventi;
		
		
	public SessioneCliente(String username, List<FarmacoCliente> farmaci, List<EventoCliente> eventi) {
		this.username = username;
		this.farmaci = (ArrayList<FarmacoCliente>) farmaci;
		this.setEventi(eventi);
	}
	
	
	public int getPunteggio() {
		return punteggio;
	}


	public void setPunteggio(int punteggio) {
		this.punteggio = punteggio;
	}


	public List<FarmacoCliente> getFarmaci() {
		return farmaci;
	}

	public void setFarmaci(List<FarmacoCliente> farmaci) {
		this.farmaci = (ArrayList<FarmacoCliente>) farmaci;
	}


	public String getFarmaciaAssociata() {
		return farmaciaAssociata;
	}


	public void setFarmaciaAssociata(String farmaciaAssociata) {
		this.farmaciaAssociata = farmaciaAssociata;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public int getLivello() {
		return livello;
	}


	public void setLivello(int livello) {
		this.livello = livello;
	}


	public int getQtaVerificate() {
		return qtaVerificate;
	}


	public void setQtaVerificate(int qtaVerificate) {
		this.qtaVerificate = qtaVerificate;
	}


	public List<EventoCliente> getEventiAttiviFarmaciaAssociata() {
		return eventiAttiviFarmaciaAssociata;
	}


	public void setEventiAttiviFarmaciaAssociata(List<EventoCliente> eventiFarmaciaAssociata) {
		this.eventiAttiviFarmaciaAssociata = (ArrayList<EventoCliente>) eventiFarmaciaAssociata;
	}


	public List<EventoCliente> getEventi() {
		return eventi;
	}


	public void setEventi(List<EventoCliente> eventi) {
		this.eventi = (ArrayList<EventoCliente>) eventi;
	}
	

}
