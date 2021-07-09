package test;
import static org.junit.Assert.*;
import java.util.ArrayList;
import org.junit.Test;
import logic.model.Cliente;
import logic.model.FarmacoCliente;
import logic.model.SessioneCliente;
import logic.controller.ControllerCustomerRiciclaggio;

public class TestControllerCustomerRiciclaggio {
	
	private ControllerCustomerRiciclaggio controller = new ControllerCustomerRiciclaggio();
	
	@Test
	public void testIncrementaPunteggioConUpgrade() {
		
		//Test Case Elisa #1:
		//controllo se il punteggio di un cliente viene incrementato
		//correttamente dopo la verifica da parte della farmacia
		
		ArrayList<FarmacoCliente> farmaci = new ArrayList<>();
		Cliente cl = new Cliente("username", "password", "email");
		SessioneCliente sessione;
		
		cl.setFarmaAssociata("farmacia");
    	cl.setPunti(10);
    	cl.setLivello(1);
    	
    	//riciclando 13 quantità il cliente ottiene 130 punti
    	FarmacoCliente farmaco = new FarmacoCliente("nome", "descrizione", "scadenza", 13);
    	farmaco.setStato("verificato");
    	farmaci.add(farmaco);
    	cl.setFarmaci(farmaci);
    	
    	sessione = new SessioneCliente(cl.getUsername(), cl.getFarmaci(), null);
    	sessione.setQtaVerificate(0);
    	sessione.setPunteggio(cl.getPunti());
    	sessione.setLivello(cl.getLivello());
    	
    	/*dopo il login il cliente ha tutte queste informazioni...
    	quando preme il pulsante di refresh in STORICO RICICLI
    	viene chiamato incrementaPunteggio() che aggiorna i punti*/
    	
    	controller.incrementaPunteggio(sessione);
    	int[] expected = {2,40};						//coppia livello-punteggio
    	int[] actual = {sessione.getLivello(), sessione.getPunteggio()};
		
		assertArrayEquals(expected, actual);
	}
}
