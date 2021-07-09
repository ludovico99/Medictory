package logic.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import logic.ingegnerizzazione.DataNotFoundException;
import logic.ingegnerizzazione.DateException;
import logic.ingegnerizzazione.InputException;
import logic.ingegnerizzazione.RisorseFarmaciaBean;
import logic.model.AbstractFactory;
import logic.model.FactoryElementoFarmacia;
import logic.model.FarmacoFarmacia;
import logic.model.SessioneFarmacia;

public class ControllerPharmacyResource {
	
	private AbstractFactory factory = new FactoryElementoFarmacia();
	
	
	private void unisciFarmaci(List<RisorseFarmaciaBean> list,FarmacoFarmacia f) {
		for(RisorseFarmaciaBean tm: list) {
			if(tm.getFarmaco().compareToIgnoreCase(f.getNome()) == 0) {
				int qta = f.getQuantita() + Integer.parseInt(tm.getQuantitativo());
				tm.setQuantitativo(Integer.toString(qta));
			}
		}
		
	}
	
	private int decrementa(FarmacoFarmacia f, String farmaco, int qtaDaTogliere) {
		if(f.getNome().compareToIgnoreCase(farmaco) == 0 && f.getQuantita()>0) {
			if(qtaDaTogliere <= f.getQuantita()) {
				
				f.setQuantita(f.getQuantita() - qtaDaTogliere);
				qtaDaTogliere = 0;
				f.setChanged(true);
				f.notifica();
			
			} else {
			
				qtaDaTogliere -= f.getQuantita();
				f.setQuantita(0);
				f.setChanged(true);
				f.notifica();
			}
		}
		return qtaDaTogliere;
	}
	
	
	
	public  List<RisorseFarmaciaBean> findResources(SessioneFarmacia s) {
		
		List<FarmacoFarmacia> farmaci;
	    ArrayList<RisorseFarmaciaBean> list = new ArrayList<>();		
	    List<String> nomiPresenti = new ArrayList<>();
	    
	    if(s.getFarmaci() != null) {
			
		    farmaci = s.getFarmaci();
		
			for(FarmacoFarmacia f: farmaci) {
				
				String farmacoCorrente = f.getNome();
				
				if(nomiPresenti.contains(farmacoCorrente)) {
					
					unisciFarmaci(list, f);
					
				} else {
					list.add(new RisorseFarmaciaBean(f.getNome(), Integer.toString(f.getQuantita()), f.getDescrizione())); 
					nomiPresenti.add(f.getNome());
				}
				
			}
		
		}
	    
	    
		return list;
	}
	
	
	
	public void cambiaQuantita(SessioneFarmacia s, String farmaco, int deltaQta) throws DataNotFoundException, InputException {
		
		List<FarmacoFarmacia> listaFarmaci = s.getFarmaci();
		
		if(listaFarmaci == null) {
			throw new DataNotFoundException("Non hai nessun farmaco a cui cambiare la quantità");
		}
		
		if(deltaQta<0) {
		
			int qtaDaTogliere = -deltaQta;
			for(FarmacoFarmacia f: listaFarmaci){
				
				qtaDaTogliere = this.decrementa(f, farmaco, qtaDaTogliere);
				if (qtaDaTogliere == 0) return;
			}		
			if(qtaDaTogliere>0) throw new InputException("Impossibile rimuovere altre " + qtaDaTogliere + " unità");
		
		} else if(deltaQta>0) {
			
			for(FarmacoFarmacia f: listaFarmaci){
				if(f.getNome().compareToIgnoreCase(farmaco) == 0) {
					f.setQuantita(f.getQuantita() + deltaQta);
					f.setChanged(true);
					f.notifica();
					return;
				}
			}
		}
	}
	
	
	public FarmacoFarmacia addMedicine(SessioneFarmacia s, String nome, int quantitativo,  String descrizione, String scadenza) throws DateException {
		
		FarmacoFarmacia f = null;
		
		Date oggi = new Date();
		
		
		try {
				
			SimpleDateFormat sdf = new  SimpleDateFormat("yyyy-MM-dd");
			Date scad = sdf.parse(scadenza);
			if (scad.before(oggi)) throw new DateException("Non puoi inserire un medicinale scaduto");
			
		} catch (ParseException e) {
		      e.printStackTrace();
		}
		
		
		f = (FarmacoFarmacia) factory.creaFarmaco(nome.toUpperCase(), descrizione, scadenza, quantitativo);
		f.setAddedRuntime(true);
		if(s.getFarmaci()==null) s.setFarmaci(new ArrayList<>());
		s.getFarmaci().add(f);
		f.notifica();
		
		return f;
	}
}
