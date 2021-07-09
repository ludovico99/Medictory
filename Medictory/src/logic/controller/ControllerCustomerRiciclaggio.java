package logic.controller;


import java.util.ArrayList;
import java.util.List;

import logic.ingegnerizzazione.InputException;
import logic.ingegnerizzazione.RiciclaggioUtenteBean;
import logic.ingegnerizzazione.StoricoUtenteBean;
import logic.model.FarmacoCliente;
import logic.model.FarmacoClienteDAO;
import logic.model.SessioneCliente;


public class ControllerCustomerRiciclaggio {
	
	public List<RiciclaggioUtenteBean> findResources(SessioneCliente s) {
		int i;
	
		ArrayList<RiciclaggioUtenteBean> list = new ArrayList<>();
		
		List<FarmacoCliente> farmaci = s.getFarmaci();
		if (farmaci != null) {
			for(i=0; i<farmaci.size(); i++) {
				if(farmaci.get(i).getStato().compareTo("scaduto") == 0) {
					list.add(new RiciclaggioUtenteBean(farmaci.get(i).getNome(), farmaci.get(i).getDescrizione(), farmaci.get(i).getQuantita()));} 
			}
		}
		return list;
		
	}
	
	

	
	public List<StoricoUtenteBean> findResourcesBis(SessioneCliente s) {
		int i;
	
		ArrayList<StoricoUtenteBean> list = new ArrayList<>();
		
		List<FarmacoCliente> farmaci = s.getFarmaci();
		if (farmaci != null) {
		for(i=0; i<farmaci.size(); i++) {
			if(farmaci.get(i).getStato().compareTo("verificato") == 0)
				list.add(new StoricoUtenteBean(farmaci.get(i).getNome(), farmaci.get(i).getDescrizione(), "\u2713")); 
			else if (farmaci.get(i).getStato().compareTo("smaltito") == 0 )
				list.add(new StoricoUtenteBean(farmaci.get(i).getNome(), farmaci.get(i).getDescrizione(), "\uD83D\uDD52")); 
		}
		
		}
		return list;
		
	}
	
	
	public void ricicla(SessioneCliente s, String nomeFarmaco) throws InputException  {
		if(nomeFarmaco.compareTo("") == 0) {
			throw new InputException("Non hai inserito nessun parametro");
		}
		if (s.getFarmaci()!= null) {
			for (FarmacoCliente f: s.getFarmaci()) {
				if(f.getNome().compareToIgnoreCase(nomeFarmaco) == 0) {
					
					if(f.getStato().compareTo("scaduto") == 0) {
						f.setStato("smaltito");
						FarmacoClienteDAO.scriviFarmacoClienteNelDb(f, s.getUsername());
						f.notifica();
						return;
					} else {
						throw new InputException("Il farmaco inserito non pu&ograve essere smaltito");
						
					}
				}
				
		}
		throw new InputException("Farmaco non trovato");
		}
	}
	
	
	public void incrementaPunteggio(SessioneCliente s) {
	
		int livello;
		int punti;
		int deltaQta;
		int qta = 0; 
		
		//calcola gli eventuali incrementi nelle quantita' dopo il refresh
		
		if(s.getFarmaci() != null) {
			for(FarmacoCliente f: s.getFarmaci()) {

				if(f.getStato().compareTo("verificato") == 0) {
					qta += f.getQuantita();	
				}
			}
		}
		
		//se ci sono stati degli incrementi allora la farmacia ha 
		//verificato qualche farmaco quindi il punteggio del cliente si alza
		
		deltaQta = qta - s.getQtaVerificate();
		
		if(deltaQta>0) {				
			
			livello = s.getLivello();
			punti = s.getPunteggio();
						
			punti += 10*deltaQta;
			if(punti >= livello*100) {
							
				punti = punti-livello*100;				//azzero i punti
				livello += 1;							//aumento il livello
				s.setLivello(livello);
				
			}
						
			s.setPunteggio(punti);
			s.setQtaVerificate(qta);
		}
	}
}
