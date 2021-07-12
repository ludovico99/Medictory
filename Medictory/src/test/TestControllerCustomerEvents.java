package test;


import static org.junit.Assert.assertThrows;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import logic.controller.ControllerCustomerEvents;
import logic.ingegnerizzazione.InputException;
import logic.ingegnerizzazione.RequirementException;
import logic.model.EventoCliente;
import logic.model.SessioneCliente;



public class TestControllerCustomerEvents {
	// Test Case Ludovico #1:
	//Il caso di test ha l'obiettivo di controllore l'effettivo lancio della Input Exception
	// quando viene aggiunto un evento il cui nome è una stringa vuota

	private static final String NOME_EVENTO = "Natale";
	@Test 
	public void testControllerCustomerEventsAddMyEventInputExc()  {
		 ControllerCustomerEvents c = new ControllerCustomerEvents();
		 SessioneCliente sessione = new SessioneCliente("Ludovico",null,null);
		 List<EventoCliente> eventi = new ArrayList<>();
		 EventoCliente e = new EventoCliente(NOME_EVENTO, null, null, null, null, 0);
		 eventi.add(e);
		 sessione.setEventiAttiviFarmaciaAssociata(eventi);
		 assertThrows(InputException.class,()-> c.addMyEvent(sessione, ""));
		 
		 
	}
	
	@Test 
	public void testControllerCustomerEventsAddMyEventReqExc()  {
		 ControllerCustomerEvents c = new ControllerCustomerEvents();
		 SessioneCliente sessione = new SessioneCliente("Ludovico",null,null);
		 sessione.setLivello(9);
		 List<EventoCliente> eventi = new ArrayList<>();
		 EventoCliente e = new EventoCliente(NOME_EVENTO, null, null, null, null, 10);
		 eventi.add(e);
		 sessione.setEventiAttiviFarmaciaAssociata(eventi);
		 assertThrows(RequirementException.class,()-> c.addMyEvent(sessione, NOME_EVENTO));
		 
		 
	}
		
}
