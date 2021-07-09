package logic.ingegnerizzazione;

import logic.model.SessioneFarmacia;

public class PharmacyAccountBean {
	private String username;
	private String email;
	private String nomeFarmacia;
	private String indirizzo;
	private String numeroClienti;
	
	
	public PharmacyAccountBean(SessioneFarmacia sessione) {
		this.setUsername(sessione.getUsername());
		this.setEmail(sessione.getEmail());
		this.setNomeFarmacia(sessione.getNomeFarmacia());
		this.setIndirizzo(sessione.getIndirizzo());
		this.setNumeroClienti(Integer.toString(sessione.getClienti().size()));
	}


	public String getUsername() {
		return username;
	}


	public void setUsername(String username) {
		this.username = username;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public String getNomeFarmacia() {
		return nomeFarmacia;
	}


	public void setNomeFarmacia(String nomeFarmacia) {
		this.nomeFarmacia = nomeFarmacia;
	}


	public String getIndirizzo() {
		return indirizzo;
	}


	public void setIndirizzo(String indirizzo) {
		this.indirizzo = indirizzo;
	}


	public String getNumeroClienti() {
		return numeroClienti;
	}


	public void setNumeroClienti(String numeroClienti) {
		this.numeroClienti = numeroClienti;
	}
}
