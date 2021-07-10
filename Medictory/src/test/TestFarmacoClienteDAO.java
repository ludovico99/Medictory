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
import logic.model.FarmacoCliente;
import logic.model.FarmacoClienteDAO;
import logic.model.SessioneCliente;

public class TestFarmacoClienteDAO {
	
private static Connector connector = Connector.getConnectorInstance();
	
	private static final String USERNAME = "Username";
		
	
	@Test
	public void testFarmacoDAOClientMedicinePersistence() {
		//Test Case Marina #2:
		
		Connection conn= connector.getConnection();
	
		Statement stmt = null;
	if (ClienteDAO.esisteCliente(USERNAME,"username") != null) {
			String sql = "DELETE  FROM  `farmaco cliente`  WHERE `possessore`='" + USERNAME + "';"; 
			
		try {
			 
			 stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			    
			 stmt.executeUpdate(sql);
			    
			 stmt.close();
		 }  catch (Exception ex) {
		 
		   ex.printStackTrace();
		} finally {
			List<Statement> statements = new ArrayList<>();
        	statements.add(stmt);
	
        	ConnectionClose.closeConnections(conn, statements);
		}		
	}
	else {
		ClienteDAO.creaUtenteCliente(USERNAME, "username", "username@gmail.com", "NuovaFarmacia");
	}
		
		SessioneCliente sessione = new SessioneCliente(USERNAME, null, null);

		FarmacoCliente farmaco = new FarmacoCliente("NuovoFarmaco", "Descrizione","2025-01-01", 1);
		List<FarmacoCliente> farmaciExcpected = new ArrayList<>();
		farmaciExcpected.add(farmaco);
		
		farmaco.setAddedRuntime(true);
		farmaco.setStato("utilizzabile");
		sessione.setFarmaci(farmaciExcpected);
	
		FarmacoClienteDAO.clientMedicinePersistence(sessione);
		List<FarmacoCliente> farmaciCliente = FarmacoClienteDAO.myFarmaciCliente(USERNAME);
		
		
		
		assertEquals(farmaciExcpected.get(0).getNome(),farmaciCliente.get(0).getNome());
		
		
	}

}
