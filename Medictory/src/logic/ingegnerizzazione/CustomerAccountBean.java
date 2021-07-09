package logic.ingegnerizzazione;

import logic.model.SessioneCliente;

public class CustomerAccountBean {
	private String username;
	private String email;
	private String farmaciaAssociata;
	private String punti;
	private String livello;
	
	public CustomerAccountBean(SessioneCliente sessione) {
		this.username = sessione.getUsername();
		this.email = sessione.getEmail();
		this.farmaciaAssociata = sessione.getFarmaciaAssociata();
		this.setPunti(Integer.toString(sessione.getPunteggio()));
		this.setLivello(Integer.toString(sessione.getLivello()));
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
	public String getFarmaciaAssociata() {
		return farmaciaAssociata;
	}
	public void setFarmaciaAssociata(String farmaciaAssociata) {
		this.farmaciaAssociata = farmaciaAssociata;
	}
	public String getPunti() {
		return punti;
	}
	public void setPunti(String punti) {
		this.punti = punti;
	}
	public String getLivello() {
		return livello;
	}
	public void setLivello(String livello) {
		this.livello = livello;
	}
	
}
