package logic.ingegnerizzazione;

public class RiciclaggioUtenteBean {
	private String drug;
	private String detail;
	private String amount;
	
	
	public RiciclaggioUtenteBean(String f, String d, int q) {
		this.drug = f;
		this.amount = Integer.toString(q);
		this.detail = d;
	}
	
	public RiciclaggioUtenteBean(String f, String d, String q) {
		this.drug = f;
		this.amount = q;
		this.detail = d;
	}
	
	
	public String getDrug() {
		return drug;
	}
	
	public void setDrug(String farmaco) {
		this.drug = farmaco;
	}
	
	public String getDetail() {
		return detail;
	}
	
	public void setDetail(String descrizione) {
		this.detail = descrizione;
	}
	
	public String getAmount() {
		return amount;
	}
	
	public void setAmount(String quantita) {
		this.amount = quantita;
	}
	
}
	
