package logic.model;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;



public class FarmacoClienteDAO {
	private static AbstractFactory factory = new FactoryElementoUtente();
	private static Connector connector = Connector.getConnectorInstance();
	
	
	private FarmacoClienteDAO() {
	    throw new IllegalStateException("Utility class");}
	
	public static void clientMedicinePersistence(SessioneCliente s) {
		 List <FarmacoCliente> farmaci = null;
		 Connection conn= connector.getConnection();
		 Statement stmt = null;
		
		 try {
		     if(s.getFarmaci() == null) return;
			 farmaci = s.getFarmaci();
			 for (FarmacoCliente f : farmaci) {
				 if (f.isAddedRuntime()) {   
				    String sql1 = "INSERT INTO  `farmaco cliente` VALUES ('" + f.getNome() +"','" + s.getUsername() + "','" + f.getScadenza() + "','" + f.getDescrizione() + "','" + f.getQuantita() + "','" + f.getStato() + "');";
				    
				    stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);

				    stmt.executeUpdate(sql1);
				    
				    stmt.close();
				    
				 }
				 else if (f.isChanged()) {
					 String sql1 = "UPDATE `farmaco cliente` F SET F.`descrizione`= '" + f.getDescrizione() +"', F.`quantitativo` = '" + f.getQuantita() + "', F.`stato` = '" + f.getStato () + "' WHERE F.`possessore` = '" + s.getUsername() + "' AND F.`farmaco`='" + f.getNome() + "' AND F.`scadenza`='" + f.getScadenza() + "';";
					 stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);

					 stmt.executeUpdate(sql1);
					 
					 stmt.close();
					 
				 }
			 }
		    	
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
		  
	}
	
	public static List<FarmacoCliente> myFarmaciCliente(String username) {
		
		List<FarmacoCliente> farmaci =  null;
		
		Statement stmt = null;
		Connection conn= connector.getConnection();
     
        
        try {
        	
        	String sql = "SELECT `farmaco`, `descrizione`, `scadenza`, `quantitativo`, `stato` " + "FROM `Farmaco Cliente` where `possessore` = '" + username + "';";
            stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);       
            
            ResultSet rs = stmt.executeQuery(sql);
            
           
            if (rs.first()) {		// rs non vuoto, posso procedere
            	rs.first();
            	farmaci = new ArrayList<>();
            	do {
            		FarmacoCliente f =(FarmacoCliente)factory.creaFarmaco(rs.getString("farmaco"), rs.getString("descrizione"), rs.getString("scadenza"), rs.getInt("quantitativo"));
             		f.setStato(rs.getString("stato"));
            		farmaci.add(f);
            		
            	} while (rs.next());
            	
            }

            else{
            	rs.close();
                stmt.close();
                conn.close();
            }
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
		return farmaci;
	}
	
	
	public static void scriviFarmacoClienteNelDb(FarmacoCliente farmaco, String possessore) {

		Statement stmt = null;
		Connection conn= connector.getConnection();
		

		try {
	        	
	      
	        String sql = "UPDATE `Farmaco Cliente` SET `stato` = '"+ farmaco.getStato() + "' , `quantitativo` = '" + farmaco.getQuantita() + "' WHERE `farmaco` = '" + farmaco.getNome() + 
	        		"' and `descrizione` = '" + farmaco.getDescrizione() + "'and `scadenza`='" + farmaco.getScadenza() + "'and `possessore`='"+ possessore +"';";
	    
	     
	        stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);       
	            
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
	    	} catch (SQLException se2) {
	    		se2.printStackTrace();
	        }
	    	try {
	    		if (conn != null)
	    		   conn.close();
	        } catch (SQLException se) {
	               se.printStackTrace();
	        }
	    }
	}
	
	
	
	public static void cambiaStato(String farmaco, String possessore, String scadenza, String stato) {
		
		Statement stmt = null;
		Connection conn= connector.getConnection();
		
		try {
        	
	        String sql = "UPDATE `Farmaco Cliente` SET `stato` = '"+ stato + "' WHERE `farmaco` = '" + farmaco + 
	        		"' and `scadenza` = '" + scadenza + "' and `possessore`='"+ possessore +"';";
	    
	     
	        stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);       
	            
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
	}
		
}
