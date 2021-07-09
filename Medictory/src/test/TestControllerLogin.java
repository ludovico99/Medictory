package test;
import static org.junit.Assert.*;
import java.util.ArrayList;
import org.junit.Test;
import logic.controller.ControllerLogin;
import logic.model.FarmacoCliente;
import logic.model.SessioneCliente;

public class TestControllerLogin {
	
	private ControllerLogin controller = new ControllerLogin();
	
	@Test
	public void testLoginClienteConFarmaciNonConsistenti() {
		
		//Test Case Elisa #2:
		//controllo se il sistema rileva correttamente i farmaci che hanno lo stato
		//inconsistente con la data di scadenza. Eventualmente vanno modificati
		
		SessioneCliente sessione;
		ArrayList<FarmacoCliente> farmaci = new ArrayList<>();
		FarmacoCliente farmaco = new FarmacoCliente("nome", "descrizione", "2000-01-01", 13);
		
		
		//il farmaco è scaduto -> rendo lo stato inconsistente indicando il farmaco "utilizzabile"
    	farmaco.setStato("utilizzabile");
    	farmaci.add(farmaco);		
		sessione = new SessioneCliente("username", farmaci, null);
		
		//dopo il check il farmaco deve avere lo stato modificato
		controller.check(sessione);
		String expected = "scaduto";
		String actual = farmaco.getStato();
		
		assertEquals(expected, actual);
		
		
		
	}
}
