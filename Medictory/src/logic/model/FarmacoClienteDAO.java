package logic.model;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import logic.ingegnerizzazione.ConnectionClose;



public class FarmacoClienteDAO {
	private static AbstractFactory factory = new FactoryElementoUtente();
	private static Connector connector = Connector.getConnectorInstance();
	private static Connection conn = connector.getConnection();
	
	
	private FarmacoClienteDAO() {
	    throw new IllegalStateException("Utility class");}
	
	public static void clientMedicinePersistence(SessioneCliente s) {
		 List <FarmacoCliente> farmaci = null;
		 Statement stmtC = null;
		
		 try {
		     if(s.getFarmaci() == null) return;
			 farmaci = s.getFarmaci();
			 for (FarmacoCliente f : farmaci) {
				 if (f.isAddedRuntime()) {   
				    String sql1 = "INSERT INTO  `farmaco cliente` VALUES ('" + f.getNome() +"','" + s.getUsername() + "','" + f.getScadenza() + "','" + f.getDescrizione() + "','" + f.getQuantita() + "','" + f.getStato() + "');";
				    
				    stmtC = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);

				    stmtC.executeUpdate(sql1);
				    
				    stmtC.close();
				    		    
				 }
				 else if (f.isChanged()) {
					 String sql1 = "UPDATE `farmaco cliente` F SET F.`descrizione`= '" + f.getDescrizione() +"', F.`quantitativo` = '" + f.getQuantita() + "', F.`stato` = '" + f.getStato () + "' WHERE F.`possessore` = '" + s.getUsername() + "' AND F.`farmaco`='" + f.getNome() + "' AND F.`scadenza`='" + f.getScadenza() + "';";
					 stmtC = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);

					 stmtC.executeUpdate(sql1);
					 
					 stmtC.close();
				 
				 }
			 }
		    	
		    }  catch (Exception exC) {
		        // Errore nel loading del driver
		        exC.printStackTrace();
		    } finally {
		    	List<Statement> statements = new ArrayList<>();
	        	statements.add(stmtC);
	        
	        	ConnectionClose.closeStmts(statements);
		    }
	}
	
	public static List<FarmacoCliente> myFarmaciCliente(String username) {
		
		List<FarmacoCliente> farmaci =  null;
		
		Statement stmt = null;
	
    
        
        try {
        	
        	String sql = "SELECT `farmaco`, `descrizione`, `scadenza`, `quantitativo`, `stato` " + "FROM `Farmaco Cliente` where `possessore` = '" + username + "';";
            stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);       
            
            ResultSet resultSet = stmt.executeQuery(sql);
            
           
            if (resultSet.first()) {		// rs non vuoto, posso procedere
            	resultSet.first();
            	farmaci = new ArrayList<>();
            	do {
            		FarmacoCliente f =(FarmacoCliente)factory.creaFarmaco(resultSet.getString("farmaco"), resultSet.getString("descrizione"), resultSet.getString("scadenza"), resultSet.getInt("quantitativo"));
             		f.setStato(resultSet.getString("stato"));
            		farmaci.add(f);
            		
            	} while (resultSet.next());
            	
            }

            else{
            	resultSet.close();
                         
            }
        }catch (Exception eC) {
            // Errore nel loading del driver
        	eC.printStackTrace();
        } finally {
        	List<Statement> statements = new ArrayList<>();
        	statements.add(stmt);

        	ConnectionClose.closeStmts(statements);
        }
		return farmaci;
	}
	
	
	public static void scriviFarmacoClienteNelDb(FarmacoCliente farmaco, String possessore) {

		Statement stmtFarmacoCliente = null;
		
		try {
	        	
	      
	        String sql = "UPDATE `Farmaco Cliente` SET `stato` = '"+ farmaco.getStato() + "' , `quantitativo` = '" + farmaco.getQuantita() + "' WHERE `farmaco` = '" + farmaco.getNome() + 
	        		"' and `descrizione` = '" + farmaco.getDescrizione() + "'and `scadenza`='" + farmaco.getScadenza() + "'and `possessore`='"+ possessore +"';";
	    
	     
	        stmtFarmacoCliente = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);       
	            
	        stmtFarmacoCliente.executeUpdate(sql);
         
	        
		} catch (Exception exFarmacoCliente) {
	            // Errore nel loading del driver
			exFarmacoCliente.printStackTrace();
	    } finally {
	    	List<Statement> statementsFarmacoCliente = new ArrayList<>();
        	statementsFarmacoCliente.add(stmtFarmacoCliente);
       	
        	ConnectionClose.closeStmts(statementsFarmacoCliente);
	    }
	}
	
	
	
	public static void cambiaStato(String farmaco, String possessore, String scadenza, String stato) {
		
		Statement statementFarmacoCliente = null;
		
		try {
        	
	        String sql = "UPDATE `Farmaco Cliente` SET `stato` = '"+ stato + "' WHERE `farmaco` = '" + farmaco + 
	        		"' and `scadenza` = '" + scadenza + "' and `possessore`='"+ possessore +"';";
	    
	     
	        statementFarmacoCliente = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);       
	            
	        statementFarmacoCliente.executeUpdate(sql);
	            

	   		
		} catch (Exception eFarmacoCliente) {
            
			eFarmacoCliente.printStackTrace();
		} finally {
			List<Statement> stmtsFarmacoCliente = new ArrayList<>();
			stmtsFarmacoCliente.add(statementFarmacoCliente);
        		
        	ConnectionClose.closeStmts(stmtsFarmacoCliente);
		}
	}
		
}
