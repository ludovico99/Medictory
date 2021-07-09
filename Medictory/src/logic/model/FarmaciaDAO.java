package logic.model;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class FarmaciaDAO {
	private static Connector connector = Connector.getConnectorInstance();
	private static AbstractFactory factory = new FactoryElementoFarmacia();
	
	private FarmaciaDAO() {
	    throw new IllegalStateException("Utility class");}
	
	
	public static Farmacia creaUtenteFarmacia(String username, String pwd, String nome, String email, String indirizzo) {
		Statement stmt = null;
		Statement stmt2 = null;
		Statement stmt3 = null;
		Statement stmt4 = null;
		Connection conn= connector.getConnection();
        Farmacia f = null;
        
        try {
        	
        	String sql = "SELECT `username` " + "FROM `Farmacia` where `username` = '" + username + "';";
            String sql2 = "SELECT `indirizzo` " + "FROM `Farmacia` where `indirizzo` = '" + indirizzo + "';";
            String sql3 = "INSERT INTO `Utenti` (`username`, `password`, `email`) values ('"+ username + "', '" + pwd + "', '" +email + "');" ;
            String sql4 = "INSERT INTO `Farmacia` (`username`, `nome`, `indirizzo`) values ('"+ username + "', '" + nome + "', '" +indirizzo + "');" ;
            
        	//cerca nel db se esistono username e indirizzo inseriti
        	
            stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            stmt2 = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            stmt3 = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
            stmt4 = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
            
            
            ResultSet rs = stmt.executeQuery(sql);
            ResultSet rs2 = stmt2.executeQuery(sql2);
           
            if (!rs.first() && !rs2.first()) {		// rs e rs2 vuoti --> posso procedere
            	
            	
            	stmt3.executeUpdate(sql3);
            	stmt4.executeUpdate(sql4);
            	f = new Farmacia(username, pwd, email);
            	f.setNome(nome);
            	f.setIndirizzo(indirizzo);
            	return f;

            }
            rs.close();  
        }catch (Exception e) {
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
             if (stmt2 != null) {
                 try{   
                     stmt2.close();
                 } catch (SQLException se) {
                     se.printStackTrace();
                 }
             }
             if (stmt3 != null) {
                 try{   
                     stmt3.close();
                 } catch (SQLException se) {
                     se.printStackTrace();
                 }
             }
             if (stmt4 != null) {
                 try{   
                     stmt4.close();
                 } catch (SQLException se) {
                     se.printStackTrace();
                 }
                 
             }
        }
        return null;
	}
	
	public static int disponibilitaFarmaco(String farmacia, String farmaco) {
		
		Statement stmt = null;
		Connection conn= connector.getConnection();
		int quantitativo = -1;
		try {
        	
        	String sql = "SELECT sum(F.quantitativo) as quantitativo FROM `Farmaco Farmacia` as F GROUP BY F.`farmaco`, F.`possessore` HAVING F.possessore= '" + farmacia + "' and F.farmaco='"+farmaco+"';";
        	
            stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
           
            
            ResultSet rs = stmt.executeQuery(sql);
           
           
            if	(rs.first()) {
            	rs.first();
            	quantitativo = rs.getInt("quantitativo");	
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
       
		return quantitativo;
	}
	
	
	public static Farmacia esisteFarmacia(String username, String pwd) {
		
		Statement stmt = null;
		Connection conn= connector.getConnection();
        Farmacia f = null;
        
        List<FarmacoFarmacia> farmaci = null;
        List <EventoFarmacia> eventi = null;
        List <Cliente> clienti = null;
        
        try {
        	
        	String sql = "SELECT F.`username`, F.`nome`, F.`indirizzo`, U.`password`, U.`email` " + "FROM `Farmacia` F join `Utenti` U on F.`username`= U.`username` where F.`username` = '" + username + "' and U.`password` = '" + pwd + "';";
	
            stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);   
            
            
            ResultSet rs = stmt.executeQuery(sql);
           
            if	(rs.first()) {
            	rs.first();
            	f = (Farmacia) factory.creaUtente(rs.getString("username"), rs.getString("password"), rs.getString("email"));
            	f.setNome(rs.getString("nome"));
            	f.setIndirizzo(rs.getString("indirizzo"));
   
            	rs.close();
          
            	farmaci = FarmacoFarmaciaDAO.myFarmaciFarmacia(username);
            	eventi = EventoDAO.allEventsFarmacia(username);
            	clienti = ClienteDAO.customerOfThisPharmacy(username);
            	
            	f.setClienti(clienti);
            	f.setFarmaci(farmaci);
            	f.setEventi(eventi);
            }
        } catch (SQLException se) {
            se.printStackTrace();
        } catch (Exception e) {
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
        return f;
	}
	
	
	
	
	
	
	
	public static List<Farmacia> tutteLeFarmacie() {
		
		Statement stmt = null;
		Connection conn= connector.getConnection();
        ArrayList<Farmacia> list = null;
        try { 
      
    	   	String sql = "SELECT F.`username`, F.`nome`, F.`indirizzo`, U.`email`, U.`password` " + "FROM `Farmacia` F JOIN `Utenti` U on F.`username` = U.`username`;";
    	   
    	   	stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
    	   	ResultSet rs = stmt.executeQuery(sql);
       
        
    	   	if (rs.first()){
    	   		rs.first();
    	   		list = new ArrayList<>();
    	   		do {
    	   			
    	   			Farmacia f = (Farmacia) factory.creaUtente(rs.getString("username"), rs.getString("password"), rs.getString("email"));
    	   			f.setNome(rs.getString("nome"));
    	   			f.setIndirizzo(rs.getString("indirizzo"));
    	   			list.add(f);
    	   		
    	   		} while(rs.next());
    	   	
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
       return list;
	}
        
        
        
}
