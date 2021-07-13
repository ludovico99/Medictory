package logic.controller;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import logic.ingegnerizzazione.DateException;
import logic.ingegnerizzazione.InputException;
import logic.ingegnerizzazione.RelationshipException;
import logic.model.RitiroCliente;
import logic.model.RitiroClienteDAO;
import logic.model.SessioneCliente;


public class ControllerCustomerAppuntamento{

	public void prenotaRitiro(String nome, String citta, String indirizzo, String d, String farmacia, String email, SessioneCliente sessione) throws InputException{
		if (nome.compareTo("")==0 || citta.compareTo("")==0 || indirizzo.compareTo("")==0 || d.compareTo("")==0 || farmacia.compareTo("")==0 || email.compareTo("")==0) 
			throw new InputException("Parametri mancanti");
		
		Date oggi = new Date();
		
		
		try {
				
			SimpleDateFormat sdf = new  SimpleDateFormat("yyyy-MM-dd");
			Date scad = sdf.parse(d);
			if (scad.before(oggi)) throw new DateException("Data inserita non valida");
			
			} catch (ParseException e) {
		      e.printStackTrace();
			}
		if (sessione.getFarmaciaAssociata().compareToIgnoreCase(farmacia) !=0) throw new RelationshipException("Inserisci la TUA farmacia");
		
		RitiroCliente ritiro = new RitiroCliente(nome,citta, indirizzo, d,farmacia,email);
		
		RitiroClienteDAO.creaRitiro(ritiro);
		
	}

}

