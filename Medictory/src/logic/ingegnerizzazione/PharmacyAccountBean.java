package logic.ingegnerizzazione;

import logic.model.SessioneFarmacia;

public class PharmacyAccountBean {
	private String username;
	private String emailFarmacia;
	private String namePharmacy;
	private String address;
	private String numeroClienti;
	
	
	public PharmacyAccountBean(SessioneFarmacia sessione) {
		this.username = (sessione.getUsername());
		this.emailFarmacia = (sessione.getEmail());
		this.namePharmacy= (sessione.getNomeFarmacia());
		this.address = (sessione.getIndirizzo());
		this.numeroClienti = (Integer.toString(sessione.getClienti().size()));
	}


	public String getUsername() {
		return username;
	}


	public void setUsername(String username) {
		this.username = username;
	}


	public String getEmailFarmacia() {
		return emailFarmacia;
	}


	public void setEmailFarmacia(String email) {
		this.emailFarmacia = email;
	}


	public String getNamePharmacy() {
		return namePharmacy;
	}


	public void setNamePharmacy(String nomeFarmacia) {
		this.namePharmacy = nomeFarmacia;
	}


	public String getAddress() {
		return address;
	}


	public void setAddress(String indirizzo) {
		this.address = indirizzo;
	}


	public String getNumeroClienti() {
		return numeroClienti;
	}


	public void setNumeroClienti(String numeroClienti) {
		this.numeroClienti = numeroClienti;
	}
}
