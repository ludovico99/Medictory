package logic.controller;

import logic.ingegnerizzazione.DisponibilitaBean;
import logic.model.FarmaciaDAO;

public class ControllerDisponibilita {
	
	
	public DisponibilitaBean cercaDisponibilitaInFarmacia(String farmacia, String farmaco) {
		int num = FarmaciaDAO.disponibilitaFarmaco(farmacia, farmaco);
		if(num == -1) num = 0;
		return new DisponibilitaBean(Integer.toString(num));
	}
	
	
}
