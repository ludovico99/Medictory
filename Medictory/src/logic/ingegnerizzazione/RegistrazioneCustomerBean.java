package logic.ingegnerizzazione;

public class RegistrazioneCustomerBean {
	
	private String nomeUtente;
    private String pwd;
    private String emailAddress;
    private String farmaciaAssociata;
    
	public String getNomeUtente() {
		return nomeUtente;
	}
	
	public void setNomeUtente(String username) {
		this.nomeUtente = username;
	}
	
	public String getPwd() {
		return pwd;
	}
	
	public void setPwd(String password) {
		this.pwd = password;
	}
	
	public String getEmailAddress() {
		return emailAddress;
	}
	
	public void setEmailAddress(String email) {
		this.emailAddress = email;
	}
	
	public String getFarmaciaAssociata() {
		return farmaciaAssociata;
	}
	
	public void setFarmaciaAssociata(String farmaciaAssociata) {
		this.farmaciaAssociata = farmaciaAssociata;
	}
}
