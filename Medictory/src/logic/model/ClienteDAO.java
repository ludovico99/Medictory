package logic.model;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class ClienteDAO {
	private static AbstractFactory factory = new FactoryElementoUtente();
	private static Connector connector = Connector.getConnectorInstance();
	
	private static String emailString = "email";
	private static String livelloString = "livello";
	private static String puntiString = "punti";
	private static String usernameString = "username";
	
	private ClienteDAO() {
	    throw new IllegalStateException("Utility class");}
	
	public static void clientPersistence(List<Cliente> clienti) {
		
		
		 Connection conn= connector.getConnection();
		 Statement stmt = null;
		 
		 try {
			
		    for (Cliente c : clienti) {

		    	int punti = c.getPunti();
		    	int livello = c.getLivello();
		    	stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
		    	String sql = "UPDATE `Cliente` SET `livello` = '"+ livello + "' , `punti` = '" + punti + "' WHERE `username` = '" + c.getUsername() + "';";
		    	
		    	stmt.executeUpdate(sql);
		    	
		    	stmt.close();
		    }
		    	
		    } catch (SQLException se) {
		        // Errore durante l'apertura della connessione
		        se.printStackTrace();
		    } catch (Exception e) {
		        // Errore nel loading del driver
		        e.printStackTrace();
		    } finally {
		    	 if (stmt != null) {
		                try{   
		                    stmt.close();
		                } catch (SQLException se) {
		                    se.printStackTrace();
		                }
		            }
		    }	
	}
	
	
	public static Cliente creaUtenteCliente(String username, String pwd, String email, String farma) {
		Statement stmt = null;
		Statement stmtA = null;
		Statement stmtB = null;
		Statement stmtC = null;
		Connection conn= connector.getConnection();
       
        Cliente c = null;
        
        try {
        	
        	String sql = "SELECT `username` " + "FROM `Utenti` where `username` = '" + username + "';";
            String sql2 = "INSERT INTO `Utenti` (`username`, `password`, `email`) values ('"+ username + "', '" + pwd + "', '" +email + "');" ;
            String sql3 = "SELECT `username` " + "FROM `Farmacia` where `username` = '" + farma + "';";
            String sql4 = "INSERT INTO `Cliente` (`username`, `farmacia associata`) values ('"+ username + "', '" + farma + "');" ;
            
        
          
            stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            stmtA = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
            stmtB = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            stmtC = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
            
            
            ResultSet rs = stmt.executeQuery(sql);
            ResultSet rs3 = stmtB.executeQuery(sql3);
            
            if (!rs3.first()) return null;			//se rs3 non trova farmacie l'input era errato
            
            if (!rs.first()) {						// rs vuoto --> posso procedere
	
            	stmtA.executeUpdate(sql2);
            	stmtC.executeUpdate(sql4);
            	c = new Cliente(username, pwd, email);
            	c.setFarmaciaAssociata(farma);
            	return c;

            }
            rs.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
        	try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
            if (stmt != null) {
                try{   
                    stmt.close();
                } catch (SQLException se) {
                    se.printStackTrace();
                }
            }
            if (stmtA != null) {
                try{   
                    stmtA.close();
                } catch (SQLException se) {
                    se.printStackTrace();
                }
            }
            if (stmtB != null) {
                try{   
                    stmtB.close();
                } catch (SQLException se) {
                    se.printStackTrace();
                }
            }
            if (stmtC != null) {
                try{   
                    stmtC.close();
                } catch (SQLException se) {
                    se.printStackTrace();
                }
            }
        }
        return null;
	}
	
	
	public static List<Cliente> customerOfThisPharmacy(String farmaciaUsername){
		 List <Cliente> clienti = new ArrayList<>();
		 Connection conn= connector.getConnection();

		 Statement stmt = null;
		
	 
		 try {
	    	
	    	String sql = "SELECT U.`username`, U.`password`, U.`email`, C.`punti`, C.`livello` FROM `Cliente` C join `Utenti` U on C.`username` = U.`username` WHERE  C.`farmacia associata`='" + farmaciaUsername + "';";
	    	stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
	        ResultSet rs = stmt.executeQuery(sql);
	        
	        
	        if (rs.first()) {
	        	rs.first();
	        	do {
	        	
	        	  		Cliente cl = (Cliente) factory.creaUtente(rs.getString(usernameString), rs.getString("password"), rs.getString(emailString));
	        	  		cl.setFarmaAssociata(farmaciaUsername);
	        	  		cl.setPunti(rs.getInt(puntiString));
	        	  		cl.setLivello(rs.getInt(livelloString));
	              
	              	
	        	  		List<FarmacoCliente> farmaci = FarmacoClienteDAO.myFarmaciCliente(rs.getString(usernameString));
	        	  		List <EventoCliente> eventi = EventoDAO.allMyEvents(rs.getString(usernameString));

	        	  		cl.setFarmaci(farmaci);
	        	  		cl.setEventi(eventi);
	          		
	        	  		clienti.add(cl);
	        	  		
	        	  	} while(rs.next());
	        	
	        }
	        rs.close();

	        
	    
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
	    return clienti;
	}
	
	
	
	
	
	
	public static  Cliente esisteCliente(String username, String pwd) {
		
		Statement stmt = null;
		Connection conn= connector.getConnection();
        Cliente cl = null;
        
        List<FarmacoCliente> farmaci = null;
        List <EventoCliente> eventi = null;
        
        try {
        	
        	String sql = "SELECT C.`username`, C.`farmacia associata`, C.`punti`, C.`livello`, U.`password`, U.`email` " + "FROM `Cliente` C join `Utenti` U  on C.`username` = U.`username` WHERE C.`username` = '" + username + "' and U.`password` = '" + pwd + "';";
        	
            stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
              
            ResultSet rs = stmt.executeQuery(sql);
   
   
            if	(rs.first()) {
            	rs.first();
            	cl = (Cliente)factory.creaUtente(rs.getString(usernameString), rs.getString("password"), rs.getString(emailString));
            	cl.setFarmaAssociata(rs.getString("farmacia associata"));
            	cl.setPunti(rs.getInt(puntiString));
            	cl.setLivello(rs.getInt(livelloString));
            
            	rs.close();
            
            	farmaci = FarmacoClienteDAO.myFarmaciCliente(username);
            	eventi = EventoDAO.allMyEvents(username);
           
        	
        	cl.setFarmaci(farmaci);
        	cl.setEventi(eventi);
            }
        } catch (SQLException se) {
            // Error in connection opening
            se.printStackTrace();
        } catch (Exception e) {
            // Error in driver loading 
            e.printStackTrace();
        } finally {
            
        	try {
            	if(stmt != null) 
            		stmt.close();
            	conn.close();
            } catch (NullPointerException e) {
            	e.printStackTrace();
            } catch (SQLException se2) {
            	se2.printStackTrace();
            } 
        }
        return cl;
	}
	
		
	public static  List<String> datiAccount(String username) {
		
		Statement stmt = null;
		Connection conn= connector.getConnection();
        
        ArrayList<String> dati = new ArrayList<>();
        try {
        	
        	String sql = "select C.`username`, C.`farmacia associata`, C.`punti`, C.`livello`, U.`email` from `Cliente` C join `Utenti` U on C.`username` = U.`username` where C.`username` = '" + username + "';";
        	
            stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            ResultSet rs = stmt.executeQuery(sql);
            
           
            
            if	(rs.first()) {
            	rs.first();
            	dati.add(rs.getString(usernameString));
            	dati.add(rs.getString("farmacia associata"));
            	dati.add(Integer.toString(rs.getInt(puntiString)));
            	dati.add(Integer.toString(rs.getInt(livelloString)));
            	dati.add(rs.getString(emailString));
            }
          
            rs.close();
        	
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
        return dati;
	}
	
	
}
