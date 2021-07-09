package logic.model;

public class Utente {
	private String username;
	private String password;
	private String email;
	
	
	public Utente(String user, String pwd, String em) {
		
		//nel controller chiedi prima alla dao di verificare se in persistenza esiste già questo username
		// se non esiste puoi creare una nuova classe utente e penso che tu debba anche trovare un modo di
		//mandarlo in persistenza (magari chiamando un metodo della dao)
		
		this.username = user;
		this.password = pwd;
		this.email = em;
	}
	
	
	//ATTENZIONE: NON CI VANNO TUTTE! VALUTA QUALE LASCIARE
	public void setUsername(String us){
		this.username = us;
	}
	public void setPassword(String pwd){
		this.password = pwd;
	}
	public void setEmail(String em){
		this.email = em;
	}
	public String getUsername(){
		return this.username;
	}
	public String getPassword(){
		return this.password;
	}
	public String getEmail(){
		return this.email;
	}
	
}
