package logic.ingegnerizzazione;
import logic.model.Farmacia;

public class FarmaciaBean {
	private String username;
	private String nome;
	private String indirizzo;
	private String email;
	

	public FarmaciaBean(Farmacia f) {
		this.username = f.getUsername();
		this.nome = f.getNome();
		this.indirizzo = f.getIndirizzo();
		this.email = f.getEmail();
	}
	
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getIndirizzo() {
		return indirizzo;
	}

	public void setIndirizzo(String indirizzo) {
		this.indirizzo = indirizzo;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
}
