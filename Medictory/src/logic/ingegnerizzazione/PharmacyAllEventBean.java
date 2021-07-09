package logic.ingegnerizzazione;

public class PharmacyAllEventBean {
	private String evento;
	private String descrizione;
	private String requisiti;
	private String premio;
	private String inizio;
	private String fine;
	
	public PharmacyAllEventBean(String evento , String descrizione, String requisiti, String premio, String inizio, String fine) {
		this.evento = evento ;
		this.descrizione = descrizione;
		this.requisiti = requisiti;
		this.premio = premio;
		this.inizio = inizio;
		this.fine = fine;
	}

	public String getEvento() {
		return evento;
	}

	public void setEvento(String evento) {
		this.evento = evento;
	}

	public String getDescrizione() {
		return descrizione;
	}

	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}

	public String getRequisiti() {
		return requisiti;
	}

	public void setRequisiti(String requisiti) {
		this.requisiti = requisiti;
	}

	public String getPremio() {
		return premio;
	}

	public void setPremio(String premio) {
		this.premio = premio;
	}

	public String getInizio() {
		return inizio;
	}

	public void setInizio(String inizio) {
		this.inizio = inizio;
	}

	public String getFine() {
		return fine;
	}

	public void setFine(String fine) {
		this.fine = fine;
	}
	
}
