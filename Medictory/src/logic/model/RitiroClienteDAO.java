package logic.model;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import logic.ingegnerizzazione.ConnectionClose;

public class RitiroClienteDAO {

	private static Connector connector = Connector.getConnectorInstance();
	
	private RitiroClienteDAO() {
	    throw new IllegalStateException("Utility class");}
	
	
	public static RitiroCliente creaRitiro(String nome, String citta, String indirizzo, String d, String farmacia, String email) {
		Statement stmtRitiro = null;
		Connection conn= connector.getConnection();
		 
        
        
        try {
        	
            String sql = "INSERT INTO `Ritiro` (`nome`, `citta`, `indirizzo`, `data`, `farmacia`, `email`) VALUES ('"+ nome + "', '" + citta + "','" + indirizzo + "', '" + d + "','" + farmacia + "','" + email + "');" ;
           
            stmtRitiro = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            
            
            stmtRitiro.executeUpdate(sql);
            
            
            stmtRitiro.close();
            
            conn.close();
        } catch (Exception excRitiro) {
            // Errore nel loading del driver
        	excRitiro.printStackTrace();
        } finally {
        	List<Statement> statementsRitiro = new ArrayList<>();
        	statementsRitiro.add(stmtRitiro);

        	ConnectionClose.closeConnections(conn, statementsRitiro);
        }
        return null;
	}
	
 public static List<RitiroCliente> myRitiriCliente(String username) {
		
	 List<RitiroCliente> ritiri = null;
		
		Statement stmtRetreat = null;
		Connection conn= connector.getConnection();
     
        
        try {
        	
        	String sql = "SELECT `citta`, `indirizzo`, `data`, `farmacia`, `email` " + "FROM `Ritiro` where `nome` = '" + username + "';";
            stmtRetreat = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);       
            
            ResultSet rsRitiro = stmtRetreat.executeQuery(sql);
            
            if (rsRitiro.first()) {		// rs non vuoto, posso procedere
            	rsRitiro.first();
            	ritiri = new ArrayList<>();
               
            	do {
            		RitiroCliente r = new RitiroCliente(/*rs.getString("nome"),*/rsRitiro.getString("citta"), rsRitiro.getString("indirizzo"), (rsRitiro.getDate("data")).toLocalDate(), rsRitiro.getString("farmacia"), rsRitiro.getString("email"));
             		
            		ritiri.add(r);
            		
            	} while (rsRitiro.next());
            	
            }

            else{
            	rsRitiro.close();
                stmtRetreat.close();
                conn.close();
            }
        } catch (Exception excRetreat) {
        	excRetreat.printStackTrace();
        } finally {
        	List<Statement> statementsRetreat = new ArrayList<>();
        	statementsRetreat.add(stmtRetreat);

        	ConnectionClose.closeConnections(conn, statementsRetreat);
        }
		return ritiri;
	}
	
}