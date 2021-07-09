package logic.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import logic.ingegnerizzazione.DateException;
import logic.ingegnerizzazione.InputException;
import logic.ingegnerizzazione.RisorseUtenteBean;
import logic.model.AbstractFactory;
import logic.model.FactoryElementoUtente;
import logic.model.FarmacoCliente;
import logic.model.SessioneCliente;


public class ControllerCustomerResource {
	private AbstractFactory factory = new FactoryElementoUtente();
	private String utilizzabile = "utilizzabile";
	
	
	public List<RisorseUtenteBean> findResources(SessioneCliente s) {
		int i;
	
		ArrayList<RisorseUtenteBean> list = new ArrayList<>();
		List<FarmacoCliente> farmaci =  s.getFarmaci();
		if (farmaci != null) {
			for(i=0; i<farmaci.size(); i++) {
				if(farmaci.get(i).getStato().compareTo(utilizzabile) == 0)
					list.add(new RisorseUtenteBean(farmaci.get(i).getNome(), farmaci.get(i).getDescrizione(), farmaci.get(i).getQuantita(), farmaci.get(i).getScadenza())); 
			}
		}
		return list;
	}
	
	
	private void modificaQtaFarmacoSpecifico(FarmacoCliente farmaco, int quantitativo) throws InputException {
        
        
        int deltaQta = quantitativo;
        if(deltaQta<0) {
            int qtaDaTogliere = -deltaQta;
            if(qtaDaTogliere <= farmaco.getQuantita()) {
                
                farmaco.setQuantita(farmaco.getQuantita() - qtaDaTogliere);
                qtaDaTogliere = 0;
                farmaco.setChanged(true);
                farmaco.notifica();
            
            } else {
            
                qtaDaTogliere -= farmaco.getQuantita();
                farmaco.setQuantita(0);
                farmaco.setChanged(true);
                farmaco.notifica();
            }
            if(qtaDaTogliere>0) throw new InputException("Impossibile rimuovere altre " + qtaDaTogliere + " unità");
            
        } else {
            
            
            farmaco.setQuantita(farmaco.getQuantita() + deltaQta);
            farmaco.setChanged(true);
            farmaco.notifica();
                
        }
        
    }
    
    public FarmacoCliente addMedicine(SessioneCliente s, String nome, String descrizione, String scadenza, int quantitativo) throws InputException {

        FarmacoCliente f = null;
        
        Date oggi = new Date();
              
        try {
                
            SimpleDateFormat sdf = new  SimpleDateFormat("yyyy-MM-dd");
            Date scad = sdf.parse(scadenza);
            if (scad.before(oggi)) {
                throw new DateException("Non puoi inserire un medicinale scaduto");
            }
            
            } catch (ParseException e) {
              e.printStackTrace();
            }
        
        
        if(s.getFarmaci() != null) {
            for(FarmacoCliente farmaco: s.getFarmaci()) {
                if(farmaco.getNome().compareToIgnoreCase(nome) == 0 && farmaco.getScadenza().compareToIgnoreCase(scadenza) == 0 && farmaco.getStato().compareToIgnoreCase(utilizzabile)==0) {
                    modificaQtaFarmacoSpecifico(farmaco, quantitativo);
                    return null;

                }
            }
        }
        
        
        f = (FarmacoCliente) factory.creaFarmaco(nome, descrizione, scadenza, quantitativo);
        if(s.getFarmaci()==null) s.setFarmaci(new ArrayList<>());
        s.getFarmaci().add(f);
        f.setStato(utilizzabile);
        f.setAddedRuntime(true);
        
        return f;
    }
}
