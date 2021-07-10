package logic.ingegnerizzazione;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;


public class ConnectionClose {

	 private ConnectionClose() {
		    throw new IllegalStateException("Utility class");
		  }

	
	public static void closeAConnection(Connection conn, Statement stmt) {
		try {
            if (stmt != null)
                stmt.close();
            conn.close();
        } catch (SQLException se) {
            se.printStackTrace();
        }
	}

}

