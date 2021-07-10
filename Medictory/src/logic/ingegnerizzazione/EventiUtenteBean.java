package logic.ingegnerizzazione;

public class EventiUtenteBean {
	private String evento;
	private String descrizione;
	private String premio;
	private String dataInizio;
	private String dataFine;
	private String requisiti;
	
	
	public EventiUtenteBean(String e, String d, String p, String di, String df) {
		this.evento=e;
		this.descrizione=d;
		this.premio=p;
		this.dataInizio=di;
		this.dataFine=df;
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



	public String getPremio() {
		return premio;
	}



	public void setPremio(String premio) {
		this.premio = premio;
	}



	public String getDataInizio() {
		return dataInizio;
	}



	public void setDataInizio(String dataInizio) {
		this.dataInizio = dataInizio;
	}



	public String getDataFine() {
		return dataFine;
	}



	public void setDataFine(String dataFine) {
		this.dataFine = dataFine;
	}
	
	
	public String getRequisiti() {
		return requisiti;
	}



	public void setRequisiti(String requisiti) {
		this.requisiti = requisiti;
	}


}
