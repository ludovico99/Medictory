package logic.controller;


import logic.model.ClienteDAO;
import logic.model.EventoDAO;
import logic.model.FarmacoClienteDAO;
import logic.model.FarmacoFarmaciaDAO;
import logic.model.SessioneCliente;
import logic.model.SessioneFarmacia;

public class ControllerLogout {

	public void makeDataClientPersistent(SessioneCliente s) {
		EventoDAO.clientEventsPersistence(s);
		FarmacoClienteDAO.clientMedicinePersistence(s);
	}
	
	public void makeDataPharmacyPersistent(SessioneFarmacia s) {
			EventoDAO.pharmacyEventsPersistence(s);
			ClienteDAO.clientPersistence(s.getClienti());
			FarmacoFarmaciaDAO.pharmacyMedicinePersistence(s);
	}
}
