package logic.ingegnerizzazione;


public class RisorseUtenteBean {
	private String farmaco;
	private String descrizione;
	private String quantitativo;
	private String scadenza;

	public RisorseUtenteBean(String f, String d, int q, String s) {
		this.farmaco = f;
		this.scadenza = s;
		this.quantitativo = Integer.toString(q);
		this.descrizione = d;
	}
	
	public RisorseUtenteBean(String f, String d, String q, String s) {
		this.farmaco = f;
		this.scadenza = s;
		this.quantitativo = q;
		this.descrizione = d;
	}
	
	public String getFarmaco() {
		return farmaco;
	}
	
	public void setFarmaco(String farmaco) {
		this.farmaco = farmaco;
	}
	
	public String getDescrizione() {
		return descrizione;
	}
	
	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}
	
	public String getQuantitativo() {
		return quantitativo;
	}
	
	public void setQuantitativo(String quantita) {
		this.quantitativo = quantita;
	}
	
	public String getScadenza() {
		return scadenza;
	}
	
	public void setScadenza(String scadenza) {
		this.scadenza = scadenza;
	}
}
