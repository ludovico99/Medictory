package test;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertThrows;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.junit.Test;
import logic.controller.ControllerPharmacyEvent;
import logic.ingegnerizzazione.DateException;
import logic.ingegnerizzazione.PharmacyAllEventBean;
import logic.model.EventoFarmacia;
import logic.model.SessioneFarmacia;

public class TestControllerPharmacyEvent {
		
		private ControllerPharmacyEvent controller = new ControllerPharmacyEvent();
		private static final String NOME_EVENTO = "Natale";
		
		
		
		@Test 
		public void testControllerPharmacyEventAddMyEvent()  {
			//Test Case Ludovico #2
			//Il caso di test ha l'obiettivo di controllore l'effettivo lancio della Date Exception
			// quando viene aggiunto un evento con data di inizio precedente a quella attuale

			SessioneFarmacia sessione = new SessioneFarmacia("Ludovico",null,null,null);
			List<String> informazioni = new ArrayList<>();
			informazioni.add(NOME_EVENTO);
			informazioni.add("ok");
			informazioni.add("Coupon");
			informazioni.add("2020-01-01");
			informazioni.add("2021-01-01");
			assertThrows(DateException.class,()-> controller.addEvent(sessione, informazioni, 10));
			 
			 
		}
		
		
		@Test
		public void testFindEventConEventoConcluso(){
			
			//Test Case Elisa #3:
			//controllo se nella sezione Eventi Attivi della farmacia il sistema mostra
			//solo gli eventi prossimi e/o in corso (nell'esempio solo e1 ed e2)
		
			Date oggi = new Date();
			int i=0;
			int j=0;
			
			EventoFarmacia e1 = new EventoFarmacia("e1", "in corso", "prova1", "2020-10-10", "2022-10-10", 8);
			EventoFarmacia e2 = new EventoFarmacia("e2", "prossimo", "prova2", "2021-10-10", "2022-10-10", 8);
			EventoFarmacia e3 = new EventoFarmacia("e3", "concluso", "prova3", "2020-10-10", "2020-12-12", 8);
			
			
			ArrayList<EventoFarmacia> eventi = new ArrayList<>();
			eventi.add(e1);
			eventi.add(e2);
			eventi.add(e3);
			
			
			for(j=0; j<eventi.size(); j++) {
				
				try {
					SimpleDateFormat sdf = new  SimpleDateFormat("yyyy-MM-dd");
		    		Date inizio = sdf.parse(eventi.get(j).getInizio());
		    		Date fine = sdf.parse(eventi.get(j).getFine());
					
					if (inizio.before(oggi))
						eventi.get(j).nextState();
		    			
		    		if (fine.before(oggi)) { 
		    			eventi.get(j).nextState();
		
		    		}
				} catch(Exception e) {
					e.printStackTrace();
				}
	    	}
			
			
			SessioneFarmacia sf = new SessioneFarmacia ("usernameFarma", null ,null, eventi);
			ArrayList<PharmacyAllEventBean> eventiMostrati = (ArrayList<PharmacyAllEventBean>) controller.findEvent(sf);
			
			
			ArrayList<String> expected = new ArrayList<>();
			ArrayList<String> actual = new ArrayList<>();
			
			expected.add("e1");
			expected.add("e2");
			for(i=0; i<eventiMostrati.size(); i++) {
				actual.add(eventiMostrati.get(i).getEvento());
			}
			
			assertArrayEquals(expected.toArray(), actual.toArray());

			
		}
}
