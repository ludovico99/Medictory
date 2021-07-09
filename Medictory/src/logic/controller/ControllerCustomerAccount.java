package logic.controller;

import java.util.ArrayList;
import java.util.List;

import logic.ingegnerizzazione.FarmaciaBean;
import logic.model.FarmaciaDAO;
import logic.model.Farmacia;

public class ControllerCustomerAccount {
	
	private ArrayList<Farmacia> farmacie = null; //
	
	public List<FarmaciaBean> findListOfPharmacy() {
		
		List<FarmaciaBean> list = new ArrayList<>();
		
		if(farmacie == null) {
			farmacie = new ArrayList<>();
			farmacie = (ArrayList<Farmacia>) FarmaciaDAO.tutteLeFarmacie();
		}
		
		for(Farmacia f: farmacie) {
			list.add(new FarmaciaBean(f));
		}
		
		return list;
		
	}

}
