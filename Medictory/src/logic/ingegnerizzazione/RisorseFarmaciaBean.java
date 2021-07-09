package logic.ingegnerizzazione;

public class RisorseFarmaciaBean {
	private String farmaco;
	private String quantitativo;
	private String descrizione;

	
	public RisorseFarmaciaBean(String f, String q, String d) {
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

	public String getQuantitativo() {
		return quantitativo;
	}
	public void setQuantitativo(String quantita) {
		this.quantitativo = quantita;
	}

	public String getDescrizione() {
		return descrizione;
	}

	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}

}
