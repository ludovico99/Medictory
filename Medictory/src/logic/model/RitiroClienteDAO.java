package logic.model;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import logic.ingegnerizzazione.ConnectionClose;

public class RitiroClienteDAO {

	private static Connector connector = Connector.getConnectorInstance();
	private static Connection conn = connector.getConnection();
	
	private RitiroClienteDAO() {
	    throw new IllegalStateException("Utility class");}
	
	
	public static void creaRitiro(RitiroCliente ritiro) {
		Statement stmtRitiro = null;
	
        try {
        	
        	
            String sql = "INSERT INTO `Ritiro` (`nome`, `citta`, `indirizzo`, `data`, `farmacia`, `email`) VALUES ('"+ ritiro.getNome() + "', '" + ritiro.getCitta() + "','" + ritiro.getIndirizzo() + "', '" + ritiro.getData() + "','" + ritiro.getFarmacia() + "','" + ritiro.getEmail() + "');" ;
           
            stmtRitiro = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            
            
            stmtRitiro.executeUpdate(sql);
            
            
            stmtRitiro.close();
           
        } catch (Exception excRitiro) {
            // Errore nel loading del driver
        	excRitiro.printStackTrace();
        } finally {
        	List<Statement> statementsRitiro = new ArrayList<>();
        	statementsRitiro.add(stmtRitiro);

        	ConnectionClose.closeStmts(statementsRitiro);
        }
	}
	
 public static List<RitiroCliente> myRitiriCliente(String username) {
		
	 List<RitiroCliente> ritiri = null;
		
		Statement stmtRetreat = null;
     
        
        try {
        	
        	String sql = "SELECT `nome`, `citta`, `indirizzo`, `data`, `farmacia`, `email` " + "FROM `Ritiro` where `nome` = '" + username + "';";
            stmtRetreat = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);       
            
            ResultSet rsRitiro = stmtRetreat.executeQuery(sql);
            
            if (rsRitiro.first()) {		// rs non vuoto, posso procedere
            	rsRitiro.first();
            	ritiri = new ArrayList<>();
               
            	do {
            		RitiroCliente r = new RitiroCliente(rsRitiro.getString("nome"),rsRitiro.getString("citta"), rsRitiro.getString("indirizzo"), (rsRitiro.getDate("data")).toString(), rsRitiro.getString("farmacia"), rsRitiro.getString("email"));
             		
            		ritiri.add(r);
            		
            	} while (rsRitiro.next());
            	
            }

            else{
            	rsRitiro.close();
                stmtRetreat.close();
            }
        } catch (Exception excRetreat) {
        	excRetreat.printStackTrace();
        } finally {
        	List<Statement> statementsRetreat = new ArrayList<>();
        	statementsRetreat.add(stmtRetreat);

        	ConnectionClose.closeStmts(statementsRetreat);
        }
		return ritiri;
	}
	
}