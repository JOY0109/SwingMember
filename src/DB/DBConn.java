package DB;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConn {
	
	private static final String driver ="oracle.jdbc.OracleDriver";
	private static final String url="jdbc:oracle:thin:@localhost:1521:xe"; 
	private static String id="joy01";
	private static String pw="1111";
	private static Connection conn=null;
	
	public static Connection getConnDB() {
		if(conn!=null)
			   return conn;
		try {
			Class.forName(driver);
			conn=DriverManager.getConnection(url, id, pw);
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return conn;
		
	}


}