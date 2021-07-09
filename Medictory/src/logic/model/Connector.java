package logic.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Connector {
	private static String user = "root";
	private static String pass = "";
	private static String dbUrl = "jdbc:mysql://localhost:3306/medictory?serverTimezone=Europe/Rome";
	private static String driverClassName = "com.mysql.cj.jdbc.Driver";
	private static Connector connectorInstance=null;
	private Connection connection;
	
	public static Connector getConnectorInstance() {
		if (connectorInstance==null) {
			connectorInstance = new Connector();
			
		}
		return connectorInstance;
	}

	public Connection getConnection() {
		try {
			
        	Class.forName(driverClassName);
        	connection = DriverManager.getConnection(dbUrl, user, pass);
           
            
            
        } catch (SQLException se) {
            // Errore durante l'apertura della connessione
            se.printStackTrace();
        } catch (Exception e) {
            // Errore nel loading del driver
            e.printStackTrace();
        } 
		return connection;
	}
		
}
