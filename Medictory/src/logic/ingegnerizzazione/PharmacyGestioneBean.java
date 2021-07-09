package logic.ingegnerizzazione;


public class PharmacyGestioneBean {
	
	private String utente;
	private String farmaco;
	private String scadenza;
	private String quantitativo;
	
	public PharmacyGestioneBean(String username, String farmaco, String scadenza, String quantitativo) {
		this.utente = username;
		this.farmaco = farmaco;
		this.scadenza = scadenza;
		this.quantitativo = quantitativo;
	}
	
	
	public String getUtente() {
		return utente;
	}
	public void setUtente(String utente) {
		this.utente = utente;
	}
	public String getFarmaco() {
		return farmaco;
	}
	public void setFarmaco(String farmaco) {
		this.farmaco = farmaco;
	}


	public String getScadenza() {
		return scadenza;
	}


	public void setScadenza(String scadenza) {
		this.scadenza = scadenza;
	}


	public String getQuantitativo() {
		return quantitativo;
	}


	public void setQuantitativo(String quantitativo) {
		this.quantitativo = quantitativo;
	}

}
