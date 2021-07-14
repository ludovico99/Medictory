package logic.ingegnerizzazione;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;


public class ConnectionClose {

	 private ConnectionClose() {
		    throw new IllegalStateException("Utility class");
		  }

	
	public static void closeStmts(List<Statement> stmt) {
		try {
			
			for (Statement i : stmt) {
	            if (i != null)
	                i.close();
			}
            
        } catch (SQLException se) {
            se.printStackTrace();
        }
	}
	
	public static void closeConn(Connection conn) {
		try {
		
			conn.close();
            
        } catch (SQLException s) {
            s.printStackTrace();
        }
	}

}
