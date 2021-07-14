package logic.model;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import logic.ingegnerizzazione.ConnectionClose;

public class FarmacoFarmaciaDAO {
	
	private static AbstractFactory factory = new FactoryElementoFarmacia();
	private static Connector connector = Connector.getConnectorInstance();
	private static Connection conn = connector.getConnection();
	
	private FarmacoFarmaciaDAO() {
	    throw new IllegalStateException("Utility class");}
	
	public static void pharmacyMedicinePersistence(SessioneFarmacia s) {
		 List <FarmacoFarmacia> farmaci = null;
		 Statement statement = null;
		
		 try {
			 if (s.getFarmaci() == null) return;
			 farmaci = s.getFarmaci();
			 for (FarmacoFarmacia f : farmaci) {
				 if (f.isAddedRuntime()) {   
				    String sql1 = "INSERT INTO  `farmaco farmacia`(`farmaco`, `possessore`, `scadenza`, `descrizione`, `quantitativo`) VALUES ('" + f.getNome() +"','" + s.getUsername() + "','" + f.getScadenza() + "','" + f.getDescrizione() + "','" + f.getQuantita() + "');";
				    
				    statement = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);

				    statement.executeUpdate(sql1);
				    
				    statement.close();
				    
				 }
				 else if (f.isChanged()) {
					 String sql1 = "UPDATE `farmaco farmacia` F SET F.`descrizione`= '" + f.getDescrizione() +"', F.`quantitativo` = '" + f.getQuantita() + "' WHERE F.`possessore` = '" + s.getUsername() + "' AND F.`farmaco`='" + f.getNome() + "' AND F.`scadenza`='" + f.getScadenza() + "';";
					 statement = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);

					 statement.executeUpdate(sql1);
					 
					 statement.close();
				 }
			 }
		    }  catch (Exception exF) {
		        exF.printStackTrace();
		    } finally {
		    	List<Statement> statements = new ArrayList<>();
	        	statements.add(statement);
 	
	        	ConnectionClose.closeStmts(statements);
	
		    }
	}
	
	
	public static List<FarmacoFarmacia>  myFarmaciFarmacia (String username) {
		
		Statement stmt = null;
	
		List<FarmacoFarmacia> farmaci = null;
	   
    
	    try {
	    	
	    	String sql = "SELECT F.`farmaco`, F.`descrizione`,F.`scadenza`,F.`quantitativo` FROM `Farmaco Farmacia`F WHERE  F.`possessore`='" + username + "';";
	    	
	    	
	        stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
  
	        ResultSet rs = stmt.executeQuery(sql);
  
	        if (rs.first()) {
	        		rs.first();
        		farmaci = new ArrayList<>();
	        		do {
	        			FarmacoFarmacia farmaco= (FarmacoFarmacia)factory.creaFarmaco(rs.getString("farmaco"), rs.getString("descrizione"), rs.getString("scadenza"), rs.getInt("quantitativo"));
	        			farmaci.add(farmaco);
	        		} while(rs.next());
	        		rs.close();
	        	}
 
	    } catch (SQLException se) {
	        se.printStackTrace();
	    } catch (Exception e) {
	        e.printStackTrace();
	    } finally {
	    	List<Statement> statements = new ArrayList<>();
        	statements.add(stmt);
 	
        	ConnectionClose.closeStmts(statements);
	    }
	    return farmaci;
	}
	
	
	
	
	
	
	
	public static void deleteExpired(String farmaco, String possessore, String scadenza) {	
		 Statement stmt = null;
		
		 try {
			
			 String sql = "DELETE FROM `Farmaco Farmacia` WHERE `farmaco` = '" + farmaco +"' and `possessore` = '" 
					 		+ possessore +"' and `scadenza` = '" + scadenza +"';";
				    
			 stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
			 stmt.executeUpdate(sql);
		
		    } catch (SQLException se) {
		        // Errore durante l'apertura della connessione
		        se.printStackTrace();
		    } catch (Exception e) {
		        // Errore nel loading del driver
		        e.printStackTrace();
		    } finally {
		    	List<Statement> statements = new ArrayList<>();
	        	statements.add(stmt);

	        	ConnectionClose.closeStmts(statements);
		    }
	}
	
	
	
	
	
	
	
}
