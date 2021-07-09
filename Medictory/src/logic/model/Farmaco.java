package logic.model;


public class Farmaco {
	private String nome;
	private String descrizione;
	private String scadenza;
	private int quantita;
	private boolean addedRuntime = false;
	private boolean changed = false;
	
	
	public Farmaco(String nome, String descrizione, String scadenza, int quantita) {
		this.nome = nome;
		this.descrizione = descrizione;
		this.scadenza = scadenza;
		this.quantita = quantita;
	}
	
	

	public boolean isAddedRuntime() {
		return addedRuntime;
	}

	public void setAddedRuntime(boolean addedRuntime) {
		this.addedRuntime = addedRuntime;
	}

	public boolean isChanged() {
		return changed;
	}

	public void setChanged(boolean changed) {
		this.changed = changed;
	}

	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getDescrizione() {
		return descrizione;
	}
	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}
	public String getScadenza() {
		return scadenza;
	}
	public void setScadenza(String scadenza) {
		this.scadenza = scadenza;
	}
	public int getQuantita() {
		return quantita;
	}
	public void setQuantita(int quantita) {
		this.quantita = quantita;
	}
	
}
