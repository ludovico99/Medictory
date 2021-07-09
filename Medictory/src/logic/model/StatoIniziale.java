package logic.model;

import java.util.ArrayList;
import java.util.List;

//Created event, not started yet


public class StatoIniziale extends AbstractState {
	
	public StatoIniziale(Evento evento) {
		super(evento);
	}

	
	@Override
	public List<Cliente> customersToAward(String username) {
		return new ArrayList<>();
	}
	
	@Override
	public int setLivelloRichiesto(int lv) {
		return lv;
	}
	
    
	@Override
	public String setVincitore(String v) {
		return null;
	}
    
	@Override
	public String setFine(String f) {
		return f;
	}
    
	@Override
	public String setInizio(String i) {
		return i;
	}
    
	@Override
	public String setPremio(String premio) {
		return premio;
	}
    
	@Override
	public String setDescrizione(String ds) {
		return ds;
	}
    
	@Override
	public String setNome(String nome) {
		return nome;
	}
	
	@Override
	public boolean setJoined(Boolean bool) {
		return true;
	}
	
	@Override
	public boolean setDeleted(Boolean bool) {
		return true;
	}
    
	@Override
	public AbstractState nextState() {
		return new SvolgimentoEvento(evento);
	}
	
}
