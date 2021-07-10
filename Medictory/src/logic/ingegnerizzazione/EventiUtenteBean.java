package logic.ingegnerizzazione;

public class EventiUtenteBean  extends MyEventiUtenteBean{
	
	private String requisiti;
	
	
	public EventiUtenteBean(String e, String d, String p, String di, String df) {
		super(e,d,p,di,df);
	}
	
	
	public String getRequisiti() {
		return requisiti;
	}



	public void setRequisiti(String requisiti) {
		this.requisiti = requisiti;
	}


}
