package test;

import static org.junit.Assert.assertThrows;

import org.junit.Test;

import logic.controller.ControllerCustomerAppuntamento;
import logic.ingegnerizzazione.RelationshipException;
import logic.model.SessioneCliente;

public class TestControllerCustomerAppuntamento {
	// Test Case Marina #1:
	// Il caso di test ha l'obiettivo di controllore l'effettivo lancio della Relationship Exception
	// quando viene prenotato un ritiro da una farmacia diversa da quella associata al cliente stesso 
	@Test 
	public void testControllerCustomerAppuntamentoPrenotaRitiro()  {
		 ControllerCustomerAppuntamento c = new ControllerCustomerAppuntamento();
		 SessioneCliente sessione = new SessioneCliente("Marina",null,null);
		 sessione.setFarmaciaAssociata("Farmacia Marini");
		 assertThrows(RelationshipException.class,()-> c.prenotaRitiro("Marina", "Roma", "Via del corso", "2022-01-01", "Farmacia Visconti", "pippo@gmail.com", sessione));
		 
		 
	}
}
