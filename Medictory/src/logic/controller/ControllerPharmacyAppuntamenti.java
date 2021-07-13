package logic.controller;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import logic.ingegnerizzazione.PharmacyAppuntamentiBean;
import logic.model.Cliente;
import logic.model.RitiroCliente;
import logic.model.RitiroClienteDAO;
import logic.model.SessioneFarmacia;


public class ControllerPharmacyAppuntamenti  {
	
	public List<PharmacyAppuntamentiBean> findListOfRitiri(SessioneFarmacia sessione) {
		
		
		ArrayList<PharmacyAppuntamentiBean> list = new ArrayList<>();
		List<Cliente> clienti = sessione.getClienti();
	
		LocalDate currentDate = LocalDate.now();
		for(Cliente c: clienti) {
			List<RitiroCliente> ritiri= RitiroClienteDAO.myRitiriCliente(c.getUsername());
			
			if(ritiri != null) {
				for(RitiroCliente rc: ritiri) {
					
					if((LocalDate.parse(rc.getData())).isAfter(currentDate)) {
						PharmacyAppuntamentiBean modelTable = new PharmacyAppuntamentiBean(c.getUsername(),rc.getEmail(),rc.getCitta(),rc.getIndirizzo(),LocalDate.parse(rc.getData()));
						list.add(modelTable);
					}
				}
			}
		}
		return list;
    }
}


	

 