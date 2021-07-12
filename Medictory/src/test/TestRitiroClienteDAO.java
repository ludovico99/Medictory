package test;

import static org.junit.Assert.assertEquals;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import logic.ingegnerizzazione.ConnectionClose;
import logic.model.ClienteDAO;
import logic.model.Connector;
import logic.model.FarmaciaDAO;
import logic.model.RitiroCliente;
import logic.model.RitiroClienteDAO;

public class TestRitiroClienteDAO {
	
private static Connector connector = Connector.getConnectorInstance();
	
	
	
	@Test
	public void testRitiroClienteDAOPersistence() {
		//Test Case Marina #3: Il test case controlla il corretto inserimento di un nuovo ritiro cliente per il cliente con Username Cliente3.
		final String USERNAMEF = "Farmacia3";
		final String USERNAMEC = "Cliente3";
		final String EMAIL = "cliente3@gmail.com";
		
		Connection conn3= connector.getConnection();
		Statement statement3 = null;
		if (FarmaciaDAO.esisteFarmacia(USERNAMEF, "farma") == null) 
			 FarmaciaDAO.creaUtenteFarmacia(USERNAMEF, "farma", "farmacia3", "farmacia3@gmail.com", "ok3");
	
		if (ClienteDAO.esisteCliente(USERNAMEC,"cliente3") != null) {
			String sql = "DELETE  FROM  `ritiro`  WHERE `nome`='" + USERNAMEC + "';"; 
			
		try {
			 
			 statement3 = conn3.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			    
			 statement3.executeUpdate(sql);
			    
			 statement3.close();
		 }  catch (Exception e3) {
		 
		   e3.printStackTrace();
		} finally {
			List<Statement> statements = new ArrayList<>();
        	statements.add(statement3);
	
        	ConnectionClose.closeConnections(conn3, statements);
		}		
	}
	else {
		ClienteDAO.creaUtenteCliente(USERNAMEC, "cliente3", EMAIL, USERNAMEF);
	}
	
		
		RitiroCliente ritiro = new RitiroCliente(USERNAMEC,"CittaTest", "IndirizzoTest", null,USERNAMEF, EMAIL);
		
		List<RitiroCliente> ritiriExpected = new ArrayList<>();
		ritiriExpected.add(ritiro);
		RitiroClienteDAO.creaRitiro(USERNAMEC, "CittaTest", "IndirizzoTest", "2022-02-03", USERNAMEF,EMAIL);
		
		
		
		
		List<RitiroCliente> ritiriEffettivi = RitiroClienteDAO.myRitiriCliente(USERNAMEC);
		
		
		
		assertEquals(ritiriExpected.get(0).getNome(),ritiriEffettivi.get(0).getNome());
		
		
	}
	
}

