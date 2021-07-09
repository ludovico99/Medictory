package logic.model;

import java.util.List;

public class SessioneFarmacia extends Sessione   {
	
	private List<FarmacoFarmacia> farmaci;
	private List<Cliente> clienti;
	private String nomeFarmacia;
	private String email;
	private String indirizzo;
	private List<EventoFarmacia> eventi;

	
	public SessioneFarmacia(String username, List<Cliente> clienti, List<FarmacoFarmacia> farmaci, List<EventoFarmacia> eventi) {
		this.username = username;
		this.farmaci = farmaci;
		this.eventi = eventi;
		this.clienti = clienti;
	}


	public List<FarmacoFarmacia> getFarmaci() {
		return farmaci;
	}

	public void setFarmaci(List<FarmacoFarmacia> farmaci) {
		this.farmaci = farmaci;
	}


	public String getNomeFarmacia() {
		return nomeFarmacia;
	}


	public void setNomeFarmacia(String nomeFarmacia) {
		this.nomeFarmacia = nomeFarmacia;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public String getIndirizzo() {
		return indirizzo;
	}


	public void setIndirizzo(String indirizzo) {
		this.indirizzo = indirizzo;
	}


	public List<Cliente> getClienti() {
		return clienti;
	}


	public void setClienti(List<Cliente> clienti) {
		this.clienti = clienti;
	}


	public List<EventoFarmacia> getEventi() {
		return eventi;
	}


	public void setEventi(List<EventoFarmacia> eventi) {
		this.eventi = eventi;
	}

}
