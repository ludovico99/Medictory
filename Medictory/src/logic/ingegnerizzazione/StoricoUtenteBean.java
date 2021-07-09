package logic.ingegnerizzazione;


public class StoricoUtenteBean {
	private String farmaco;
	private String descrizione;
	private String verifica;
	
	public StoricoUtenteBean(String f, String d, String s) {
		this.farmaco = f;
		this.descrizione = d;
		this.verifica = s;
	}
	
	
	public String getVerifica() {
		return verifica;
	}


	public void setVerifica(String v) {
		this.verifica = v;
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

}
