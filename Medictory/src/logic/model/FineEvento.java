package logic.model;

import java.util.List;

//Closed event

public class FineEvento extends AbstractState{
	
	//new
	private List<Cliente> infoPartecipanti;

	FineEvento(Evento evento) {
		super(evento);
	}
	
	//new
	public void setInfoPartecipanti(List<Cliente> infoPartecipanti) {
		this.infoPartecipanti = infoPartecipanti;
	}

	
	
	@Override
	public List<Cliente> customersToAward(String username) {
		return infoPartecipanti;
	}
	
	
	@Override
	public int setLivelloRichiesto(int lv) {
		return -1;
	}
    
	@Override
	public String setVincitore(String v) {
		return v;
	}
    
	@Override
	public String setFine(String f) {
		return null;
	}
    
	@Override
	public String setInizio(String i) {
		return null;
	}
    
	@Override
	public String setPremio(String premio) {
		return null;
	}
    
	@Override
	public String setDescrizione(String ds) {
		return null;
	}
    
	@Override
	public String setNome(String nome) {
		return null;
	}
	
	@Override
	public boolean setJoined(Boolean bool) {
		return false;
	}
	
	@Override
	public boolean setDeleted(Boolean bool) {
		return false;
	}
    
	@Override
	public AbstractState nextState() {
		return null;
	}
}
