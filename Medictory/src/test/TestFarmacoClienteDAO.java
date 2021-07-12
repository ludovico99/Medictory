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
import logic.model.FarmacoCliente;
import logic.model.FarmacoClienteDAO;
import logic.model.SessioneCliente;

public class TestFarmacoClienteDAO {
	
	/*Test Case Marina #1: Il seguente test case controlla il corretto
	 * inserimento del farmaco-cliente "NuovoFarmaco"
	 * relativo al cliente con username "Cliente2".*/
	
	private static Connector connector = Connector.getConnectorInstance();

	@Test
	public void testFarmacoDAOClientMedicinePersistence() {
		
		Connection conn= connector.getConnection();
		Statement stmt = null;
		final String USERNAMEF = "Farmacia2";
		final String USERNAME = "Cliente2";
		
		if (FarmaciaDAO.esisteFarmacia(USERNAMEF, "farma") == null) {
			FarmaciaDAO.creaUtenteFarmacia(USERNAMEF, "farma", "farmacia2", "farmacia2@gmail.com", "via Ettore Viola,30");
		}
		
		if (ClienteDAO.esisteCliente(USERNAME,"cliente2") != null) {
			String sql = "DELETE  FROM  `farmaco cliente`  WHERE `possessore`='" + USERNAME + "';"; 
			
			try {
			 
				stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
				stmt.executeUpdate(sql);
				stmt.close();
			 
			} catch (Exception ex) {
				ex.printStackTrace();
			 
			   } finally {
			
				List<Statement> statements = new ArrayList<>();
				statements.add(stmt);
	
				ConnectionClose.closeConnections(conn, statements);
		          }		
		 } else {
			 ClienteDAO.creaUtenteCliente(USERNAME, "cliente2", "cliente2@gmail.com", USERNAMEF);
		   }
		
		SessioneCliente sessione = new SessioneCliente(USERNAME, null, null);

		FarmacoCliente farmaco = new FarmacoCliente("NuovoFarmaco", "Descrizione","2025-01-01", 1);
		List<FarmacoCliente> farmaciExpected = new ArrayList<>();
		farmaciExpected.add(farmaco);
		
		farmaco.setAddedRuntime(true);
		farmaco.setStato("utilizzabile");
		sessione.setFarmaci(farmaciExpected);
	
		FarmacoClienteDAO.clientMedicinePersistence(sessione);
		List<FarmacoCliente> farmaciCliente = FarmacoClienteDAO.myFarmaciCliente(USERNAME);
		
		assertEquals(farmaciExpected.get(0).getNome(),farmaciCliente.get(0).getNome());
		
	}

}
