package test;

import static org.junit.Assert.*;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import logic.ingegnerizzazione.ConnectionClose;
import logic.model.Connector;
import logic.model.EventoDAO;
import logic.model.EventoFarmacia;
import logic.model.FarmaciaDAO;
import logic.model.SessioneFarmacia;

public class TestEventoDAO {
	private static Connector connector = Connector.getConnectorInstance();
	
	private static final String USERNAME = "Farmacia1";
		
	
	@Test
	public void testEventoDAOPharmacyEventsPersistence() {
		//Test Case Ludovico #3: Il test case ha lo scopo di controllare l'effettivo inserimento di un nuovo evento per la farmacia con username Farmacia1
		
		Connection conn= connector.getConnection();
	
		Statement statement = null;
	if (FarmaciaDAO.esisteFarmacia(USERNAME, "farma") != null) {
			String sql1 = "DELETE  FROM `evento`  WHERE `farmacia`='" + USERNAME + "';"; 
			
		try {
			 
			 statement = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			    
			 statement.executeUpdate(sql1);
			    
			 statement.close();
		 } catch (Exception e) {
		 
		   e.printStackTrace();
		} finally {
			List<Statement> statements = new ArrayList<>();
        	statements.add(statement);
	
        	ConnectionClose.closeConnections(conn, statements);
		}		
	}
	else {
		FarmaciaDAO.creaUtenteFarmacia(USERNAME, "farma", "farmacia1", "farmacia1@gmail.com", "ok");
	}
		
		SessioneFarmacia sessione = new SessioneFarmacia(USERNAME, null, null, null);

		EventoFarmacia evento = new EventoFarmacia("NuovoEvento", "Descrizione","Coupon", "2021-01-01","2022-01-01", 1);
		List<EventoFarmacia> eventiExpected = new ArrayList<>();
		eventiExpected.add(evento);
		
		evento.setAddedRuntime(true);
		sessione.setEventi(eventiExpected);
	
    	EventoDAO.pharmacyEventsPersistence(sessione);
    	
		List<EventoFarmacia> eventiFarmacia = EventoDAO.allEventsFarmacia(USERNAME);
		
		
		
		assertEquals(eventiExpected.get(0).getName(),eventiFarmacia.get(0).getName());
		
		
	}
}
	
