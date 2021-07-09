package logic.ingegnerizzazione;

public abstract class AbstractEventiUtenteBean {
	protected String evento;
	protected String descrizione;
	protected String premio;
	protected String dataInizio;
	protected String dataFine;
	
	
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
}
