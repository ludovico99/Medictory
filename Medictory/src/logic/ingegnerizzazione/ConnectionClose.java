package logic.ingegnerizzazione;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;


public class ConnectionClose {

	 private ConnectionClose() {
		    throw new IllegalStateException("Utility class");
		  }

	
	public static void closeConnections(Connection conn, List<Statement> stmt) {
		try {
			conn.close();
			for (Statement i : stmt) {
	            if (i != null)
	                i.close();
			}
            
        } catch (SQLException se) {
            se.printStackTrace();
        }
	}

}
