package logic.controller;

import java.util.ArrayList;
import java.util.List;

import logic.ingegnerizzazione.ListaClientiBean;
import logic.model.Cliente;
import logic.model.SessioneFarmacia;

public class ControllerPharmacyAccount {
	private List<Cliente> clienti = null; 
	
	
	
	public List<ListaClientiBean> findListOfCustomers(SessioneFarmacia sessione/*, GcPharmacyAccount controllerGrafico*/) {
		
		ArrayList<ListaClientiBean> list = new ArrayList<>();
		
		if(clienti == null) {
			clienti = sessione.getClienti();
		}
		
		for(Cliente c: clienti) {
			list.add(new ListaClientiBean(c));
			//c.attach(controllerGrafico);				//non cancellare, nel controller grafico DEVE ancora essere fatto l'attach
		}

		return list;
		
	}
}
