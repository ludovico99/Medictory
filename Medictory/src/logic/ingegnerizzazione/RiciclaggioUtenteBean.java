package logic.ingegnerizzazione;

public class RiciclaggioUtenteBean {
	private String farmaco;
	private String descrizione;
	private String quantitativo;
	
	
	public RiciclaggioUtenteBean(String f, String d, int q) {
		this.farmaco = f;
		this.quantitativo = Integer.toString(q);
		this.descrizione = d;
	}
	
	public RiciclaggioUtenteBean(String f, String d, String q) {
		this.farmaco = f;
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
}
	
