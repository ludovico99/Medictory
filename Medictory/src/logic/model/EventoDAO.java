package logic.model;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import logic.ingegnerizzazione.ConnectionClose;

public class EventoDAO {
	private static Connector connector = Connector.getConnectorInstance();
	private static Connection conn = connector.getConnection();
	
	private static String descrizioneString = "descrizione";
	private static String inizioString = "inizio";
	private static String livelloRichiestoString = "livello richiesto";
	private static String premioString = "premio";
	private static String dateString = "yyyy-MM-dd";
	
	private EventoDAO() {
	    throw new IllegalStateException("Utility class");}
	
	
	public static List<Cliente> findPartecipants( String vincitore , String organizzatore){
		 List <Cliente> clienti = null;
		 Statement stmt = null;
		 
		 try {
			 String sql = "SELECT U.`username`, U.`email` FROM `Adesioni evento` as A  JOIN `utenti` as U ON A.`cliente` = U.`username` WHERE  A.`evento`='" + vincitore + "' and A.`organizzatore`= '" + organizzatore + "';";
			
			 	stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
		        ResultSet rs = stmt.executeQuery(sql);
		
		    	if (rs.first()) {
		    		rs.first();
		    		clienti = new ArrayList<>();
		    	do {
		    		 
		    	   	Cliente c = new Cliente(rs.getString("username"),null,rs.getString("email"));
					
		    	   	clienti.add(c);
		    	} while(rs.next());
		    	rs.close();
		    	}
			 } catch (SQLException sqlExc) {
			        // Errore durante l'apertura della connessione
				 sqlExc.printStackTrace();
			    } catch (Exception exc) {
			        // Errore nel loading del driver
			    	exc.printStackTrace();
			    } finally {
			    	List<Statement> statements = new ArrayList<>();
		        	statements.add(stmt);
        	
		        	ConnectionClose.closeStmts(statements);
			    }
		 return clienti;
	}
	
	
	public static void pharmacyEventsPersistence (SessioneFarmacia s) {
		 List <EventoFarmacia> eventi = null;
		 Statement stmt = null;
		 
		 try {
			 if(s.getEventi() == null) return;
			 eventi = s.getEventi();
			 
				
			 for (EventoFarmacia e : eventi) {
				 if (e.isAddedRuntime() && !e.isDeleted()) {
				   
				    String sql1 = "INSERT INTO  `evento`  VALUES ('" + e.getName() + "','" + s.getUsername() + "','" + e.getDescription() + "','" + e.getStartDate() + "','" + e.getEndDate() + "','" + e.getAward() + "','" + e.getRequiredLevel() + "', null);";
				   
				    stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
				    
				    stmt.executeUpdate(sql1);
				    
				    stmt.close();
				    
				 }
				 else if (e.isChanged() && !e.isDeleted()) { 
					   
					 	String sql1 = "UPDATE `evento`  SET `descrizione`= '" + e.getDescription() + "', `inizio` = '" + e.getStartDate() + "', `fine` = '" +  e.getEndDate() + "', `premio` = '" + e.getAward() + "', `livello richiesto` = '" + e.getRequiredLevel() + "', `vincitore` = '" + e.getWinner() + "' WHERE `nome` = '" + e.getName() + "' AND `farmacia`='" + s.getUsername() + "';";
					 	 stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
						    
						 stmt.executeUpdate(sql1);
						    
						 stmt.close();
					    
					 }
				 else if (e.isDeleted()) {
					 String sql1 = "DELETE  FROM `evento`  WHERE `nome` = '" + e.getName() + "' AND `farmacia`='" + s.getUsername() + "';"; // impostare on delete cascade se è un evento al quale si è aderito
					 
					 stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
					    
					 stmt.executeUpdate(sql1);
					    
					 stmt.close();
				 }
			 }
		    	
		    } catch (Exception ex) {
		        // Errore nel loading del driver
		    	ex.printStackTrace();
		    } finally {
		    	List<Statement> statements = new ArrayList<>();
	        	statements.add(stmt);
	        	
	        	ConnectionClose.closeStmts(statements);
		    }		
	}
		
	public static void clientEventsPersistence(SessioneCliente s) {
		 List <EventoCliente> eventi = null;
		 Statement stmt = null;
		 String farmaciaAssociata = null;
		
		 try {
		    
			 eventi = s.getEventi();
			 if (s.getEventi() == null) return;
			 
			 for (EventoCliente e : eventi) {
				 if (e.isJoined()) {
				    farmaciaAssociata = s.getFarmaciaAssociata();
				    String sql1 = "INSERT INTO  `Adesioni evento` VALUES ('" + s.getUsername() +"','" + e.getName() + "','" + farmaciaAssociata + "');";
				    
				    stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
				    
				    stmt.executeUpdate(sql1);
				    
				    stmt.close();
				    
				 }
			 }
		    	
		    }  catch (Exception e) {
		        // Errore nel loading del driver
		        e.printStackTrace();
		    } finally {
		    	List<Statement> statements = new ArrayList<>();
	        	statements.add(stmt);
	            	
	        	ConnectionClose.closeStmts(statements);
		    }
		  
	}
	
	public static List<EventoFarmacia> allEventsFarmacia(String username){
		 List <EventoFarmacia> eventi = null;
		 Date oggi = new Date();
		 Statement stmtEventiFarmacia = null;
		
	 
	    try {
	    	
	    	String sqlEventiFarmacia = "SELECT E.`nome`, E.`descrizione`, E.`inizio`, E.`fine`, E.`premio`, E.`livello richiesto`, E.`vincitore` FROM `Evento` E  WHERE  E.`farmacia`='" + username + "';";
	    	
	    	
	        stmtEventiFarmacia = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
	
	        ResultSet rsEventiFarmacia = stmtEventiFarmacia.executeQuery(sqlEventiFarmacia);
	
	    	if (rsEventiFarmacia.first()) {
	    		rsEventiFarmacia.first();
	    		eventi = new ArrayList<>();
	    	do {
	    		 
	    		EventoFarmacia ev = new EventoFarmacia(rsEventiFarmacia.getString("nome"),rsEventiFarmacia.getString(descrizioneString),rsEventiFarmacia.getString(premioString),(rsEventiFarmacia.getDate(inizioString)).toString(), (rsEventiFarmacia.getDate("fine")).toString(), rsEventiFarmacia.getInt(livelloRichiestoString));
	    			
	    		SimpleDateFormat sdf = new  SimpleDateFormat(dateString);
	    		Date inizio = sdf.parse((rsEventiFarmacia.getDate(inizioString)).toString());
	    		Date fine = sdf.parse((rsEventiFarmacia.getDate("fine")).toString());
	    		if (inizio.before(oggi))
	    			ev.nextState();
	    			
	    		if (fine.before(oggi)) { 
	    			ev.nextState();
	    			ev.setWinner(rsEventiFarmacia.getString("vincitore"));
	    			
	    			//trovo i partecipanti solo per gli eventi che non hanno un vincitore 
	    			//e che quindi devono ancora effettuare la premiazione
	    			
	    			if(ev.getWinner() == null || ev.getWinner().compareTo("") == 0) {
	    				FineEvento state = (FineEvento) ev.getState();
	    				state.setInfoPartecipanti(findPartecipants(ev.getName(), username));
	    			}
	    		}
	    	
	    	  eventi.add(ev);
	    	} while(rsEventiFarmacia.next());
	    	rsEventiFarmacia.close();
	    	}
	    	
	    
	    }catch (Exception exception) {
	    	exception.printStackTrace();
	    } finally {
	    	List<Statement> statements = new ArrayList<>();
        	statements.add(stmtEventiFarmacia);
        	
        	ConnectionClose.closeStmts(statements);
	    }
	    return eventi;
	}
	
	
	public static 	List<EventoCliente> allMyEvents(String username){
		 List <EventoCliente> eventi = null;
		 Date oggi = new Date();
		 Statement stmtMyevents = null;
		
	  
	     try {
	     	
	     	String sql="SELECT A.`evento`, E.`descrizione`, E.`inizio`,E.`fine`, E.`premio`, E.`livello richiesto`, E.`vincitore` FROM `Adesioni evento` A JOIN `Evento` E ON E.`nome`=A.`evento` and E.`Farmacia`= A.`Organizzatore` WHERE  A.`cliente`='" + username + "';";
	     	
	     	
	         stmtMyevents = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
	
	         ResultSet rsMyEVents = stmtMyevents.executeQuery(sql);
	
	     	if (rsMyEVents.first()) {
	     		rsMyEVents.first();
	     		eventi = new ArrayList<>();
	     	do {
	     		 
	     	   	EventoCliente evCliente = new EventoCliente(rsMyEVents.getString("evento"),rsMyEVents.getString(descrizioneString),rsMyEVents.getString(premioString),(rsMyEVents.getDate(inizioString)).toString(), (rsMyEVents.getDate("fine")).toString(), rsMyEVents.getInt(livelloRichiestoString));
	     	   
	     		   
	     		 
	   			SimpleDateFormat sdf = new  SimpleDateFormat(dateString);
	   			Date dataInizio = sdf.parse((rsMyEVents.getDate(inizioString)).toString());
	   			Date dataFine = sdf.parse((rsMyEVents.getDate("fine")).toString());
	   			if (dataInizio.before(oggi))
	   				evCliente.nextState();
	   			
	   			if (dataFine.before(oggi)) 
	   				evCliente.nextState();
	   			
	     	   	
	     	   	eventi.add(evCliente);
	     	} while(rsMyEVents.next());
	     	rsMyEVents.close();
	     	}
	     } catch (Exception i) {
	         i.printStackTrace();
	     }finally {
	    	 List<Statement> statements = new ArrayList<>();
	        	statements.add(stmtMyevents);
	        	ConnectionClose.closeStmts(statements);
	     }
	     return eventi;
	}
		
	
		public static  List<EventoCliente> allActiveEvents(String farmacia) {
			
			Statement stmt = null;
			Date oggi = new Date();
	       
	        
			List<EventoCliente> eventi = null;
	        try {
	        
	        	String sql = "select * from `evento` E where  E.`farmacia` = '" + farmacia + "';";
	            stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
	            ResultSet rs = stmt.executeQuery(sql);
	            
	           
	            
	            if (rs.first()) {
	                rs.first();
	                eventi = new ArrayList<>();
	            	do {
	            		 
	            		EventoCliente ev = new EventoCliente(rs.getString("nome"),rs.getString(descrizioneString),rs.getString(premioString),(rs.getDate(inizioString)).toString(), (rs.getDate("fine")).toString(), rs.getInt(livelloRichiestoString));
	            	   
	               		SimpleDateFormat sdf = new  SimpleDateFormat(dateString);
	               		Date inizio = sdf.parse((rs.getDate(inizioString)).toString());
	               		Date fine = sdf.parse((rs.getDate("fine")).toString());
	               		
	               		if (inizio.before(oggi))
	               			ev.nextState();
	               			
	               		if (fine.before(oggi)) 
	               			ev.nextState();
	              
	            	   	eventi.add(ev);
	            	  
	            	} while(rs.next());
	            	
	            	
	            	rs.close();
	            }
	          
	        	
	        } catch (SQLException se) {
	            // Errore durante l'apertura della connessione
	            se.printStackTrace();
	        } catch (ParseException pe) {
     		     pe.printStackTrace();
     			
	        } catch (Exception e) {
	            // Errore nel loading del driver
	            e.printStackTrace();
	        } finally {
	        	List<Statement> statements = new ArrayList<>();
	        	statements.add(stmt);
	        	
	        	ConnectionClose.closeStmts(statements);
	        }
	        return eventi;
		}	
	}
