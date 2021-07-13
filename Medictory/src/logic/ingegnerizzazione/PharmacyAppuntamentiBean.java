package logic.ingegnerizzazione;

import java.time.LocalDate;
import logic.model.RitiroCliente;

public class PharmacyAppuntamentiBean {
	
    private String utente;
    private String email;
    private String city;
    private String address;
    private LocalDate data;
    
	


	public PharmacyAppuntamentiBean(String username, String email, String citta, String indirizzo, LocalDate data) {
		this.utente = username;
		this.email = email;
		this.city = citta;
		this.address = indirizzo;
		this.data = data;
	}
	
	public PharmacyAppuntamentiBean(RitiroCliente c) {
		this.utente= c.getNome();
		this.city = c.getCitta();
		this.address = c.getIndirizzo();
		this.email = c.getEmail();
		this.data = LocalDate.parse(c.getData());
	}


	public String getUtente() {
		return utente;
	}


	public void setUtente(String utente) {
		this.utente = utente;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public String getCity() {
		return city;
	}
	

	public void setCity(String citta) {
		this.city = citta;
	}


	public String getAddress() {
		return address;
	}


	public void setAddress(String indirizzo) {
		this.address = indirizzo;
	}


	public LocalDate getData() {
		return data;
	}


	public void setData(LocalDate data) {
		this.data = data;
	}

}
