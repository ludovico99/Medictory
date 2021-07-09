package logic.model;

import java.util.List;

public class Farmacia extends Utente{
	private String nome;
	private String indirizzo;
	private List<FarmacoFarmacia> farmaci;
	private List<EventoFarmacia> eventi;
	private List<Cliente> clienti;
	

	public Farmacia(String user, String pwd, String em) {
		
		super(user, pwd, em);
		
	}
	
	public List<FarmacoFarmacia> getFarmaci() {
		return farmaci;
	}

	public void setFarmaci(List<FarmacoFarmacia> farmaci) {
		this.farmaci = farmaci;
	}

	public List<EventoFarmacia> getEventi() {
		return eventi;
	}

	public void setEventi(List<EventoFarmacia> eventi) {
		this.eventi = eventi;
	}

	public void setNome(String name){
		this.nome = name;
	}
	public void setIndirizzo(String i){
		this.indirizzo = i;
	}
	
	public String getNome(){
		return this.nome;
	}
	public String getIndirizzo(){
		return this.indirizzo;
	}

	public List<Cliente> getClienti() {
		return clienti;
	}

	public void setClienti(List<Cliente> clienti) {
		this.clienti = clienti;
	}

}
