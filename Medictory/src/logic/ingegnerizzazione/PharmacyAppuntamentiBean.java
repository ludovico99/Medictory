package logic.ingegnerizzazione;

import java.time.LocalDate;
import logic.model.RitiroCliente;

public class PharmacyAppuntamentiBean {
	
    private String utente;
    private String email;
    private String citta;
    private String indirizzo;
    private LocalDate data;
    
	


	public PharmacyAppuntamentiBean(String username, String email, String citta, String indirizzo, LocalDate data) {
		this.utente = username;
		this.email = email;
		this.citta = citta;
		this.indirizzo = indirizzo;
		this.data = data;
	}
	
	public PharmacyAppuntamentiBean(RitiroCliente c) {
		this.utente= c.getNome();
		this.citta = c.getCitta();
		this.indirizzo = c.getIndirizzo();
		this.email = c.getEmail();
		this.data = c.getData();
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


	public String getCitta() {
		return citta;
	}


	public void setCitta(String citta) {
		this.citta = citta;
	}


	public String getIndirizzo() {
		return indirizzo;
	}


	public void setIndirizzo(String indirizzo) {
		this.indirizzo = indirizzo;
	}


	public LocalDate getData() {
		return data;
	}


	public void setData(LocalDate data) {
		this.data = data;
	}

}
