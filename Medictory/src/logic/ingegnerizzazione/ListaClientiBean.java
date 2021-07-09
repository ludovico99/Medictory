package logic.ingegnerizzazione;

import logic.model.Cliente;

public class ListaClientiBean {
	private String username;
	private String email;
	private String livello;
	private String punti;
	
	public ListaClientiBean(String username, String email, String livello, String punti) {
		this.setUsername(username);
		this.setEmail(email);
		this.setLivello(livello);
		this.setPunti(punti);
	}
	public ListaClientiBean(Cliente c) {
		this.username = c.getUsername();
		this.livello = Integer.toString(c.getLivello());
		this.punti = Integer.toString(c.getPunti());
		this.email = c.getEmail();
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getLivello() {
		return livello;
	}

	public void setLivello(String livello) {
		this.livello = livello;
	}

	public String getPunti() {
		return punti;
	}

	public void setPunti(String punti) {
		this.punti = punti;
	}
}
