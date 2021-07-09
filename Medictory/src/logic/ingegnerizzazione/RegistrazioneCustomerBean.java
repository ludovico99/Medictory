package logic.ingegnerizzazione;

public class RegistrazioneCustomerBean {
	
	private String username;
    private String password;
    private String email;
    private String farmaciaAssociata;
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getFarmaciaAssociata() {
		return farmaciaAssociata;
	}
	public void setFarmaciaAssociata(String farmaciaAssociata) {
		this.farmaciaAssociata = farmaciaAssociata;
	}
}
