package logic.model;

import java.time.LocalDate;

public class RitiroCliente {
	private String nome;
	private String citta;
	private String indirizzo;
	private LocalDate data;
	private String farmacia;
	private String email;
	
	public RitiroCliente(String citta, String indirizzo, LocalDate data, String farmacia, String email) {
			
		this.citta = citta;
		this.indirizzo = indirizzo;
		this.data = data;
		this.farmacia = farmacia;
		this.email = email;
	}


	public String getFarmacia() {
		return farmacia;
	}


	public void setFarmacia(String farmacia) {
		this.farmacia = farmacia;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public String getNome() {
		return nome;
	}


	public void setNome(String nome) {
		this.nome = nome;
	}


	public String getCitta() {
		return citta;
	}


	public void setCitta(String citta) {
		this.citta = citta;
	}


	public String getIndirizzo() {
		return indirizzo;
	}


	public void setIndirizzo(String indirizzo) {
		this.indirizzo = indirizzo;
	}


	public LocalDate getData() {
		return data;
	}


	public void setData(LocalDate data) {
		this.data = data;
	}
	
	
	
	
}
