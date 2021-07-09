package logic.model;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class RitiroClienteDAO {

	private static Connector connector = Connector.getConnectorInstance();
	
	private RitiroClienteDAO() {
	    throw new IllegalStateException("Utility class");}
	
	
	public static RitiroCliente creaRitiro(String nome, String citta, String indirizzo, String d, String farmacia, String email) {
		Statement stmt = null;
		Connection conn= connector.getConnection();
		 
        
        
        try {
        	
            String sql = "INSERT INTO `Ritiro` (`nome`, `citta`, `indirizzo`, `data`, `farmacia`, `email`) VALUES ('"+ nome + "', '" + citta + "','" + indirizzo + "', '" + d + "','" + farmacia + "','" + email + "');" ;
           
            stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            
            
            stmt.executeUpdate(sql);
            
            
            stmt.close();
            
            conn.close();
        } catch (SQLException se) {
            // Errore durante l'apertura della connessione
            se.printStackTrace();
        } catch (Exception e) {
            // Errore nel loading del driver
            e.printStackTrace();
        } finally {
            try {
                if (stmt != null)
                    stmt.close();
                conn.close();
            } catch (SQLException se) {
                se.printStackTrace();
            }
        }
        return null;
	}
	
 public static List<RitiroCliente> myRitiriCliente(String username) {
		
	 List<RitiroCliente> ritiri = null;
		
		Statement stmt = null;
		Connection conn= connector.getConnection();
     
        
        try {
        	
        	String sql = "SELECT `citta`, `indirizzo`, `data`, `farmacia`, `email` " + "FROM `Ritiro` where `nome` = '" + username + "';";
            stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);       
            
            ResultSet rs = stmt.executeQuery(sql);
            
            if (rs.first()) {		// rs non vuoto, posso procedere
            	rs.first();
            	ritiri = new ArrayList<>();
               
            	do {
            		RitiroCliente r = new RitiroCliente(/*rs.getString("nome"),*/rs.getString("citta"), rs.getString("indirizzo"), (rs.getDate("data")).toLocalDate(), rs.getString("farmacia"), rs.getString("email"));
             		
            		ritiri.add(r);
            		
            	} while (rs.next());
            	
            }

            else{
            	rs.close();
                stmt.close();
                conn.close();
            }
        }  catch (SQLException se) {
            // Errore durante l'apertura della connessione
            se.printStackTrace();
        } catch (Exception e) {
            // Errore nel loading del driver
            e.printStackTrace();
        } finally {
            try {
                if (stmt != null)
                    stmt.close();
                conn.close();
            } catch (SQLException se) {
                se.printStackTrace();
            }
        }
		return ritiri;
	}
	
}