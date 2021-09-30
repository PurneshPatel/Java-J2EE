package utils;

import java.sql.*;

public class DBUtils {
	private static Connection connection;

	// singleton pattern : ensures creation of single instance of the Connection
	// throughout the JVM
	// add a static method to return the Connection instance(=> imple class instance
	// : JDBC JAR)
	public static Connection fetchDBConnection() throws ClassNotFoundException, SQLException {
		if (connection == null) {
			// load JDBC driver : optional in standalone DB apps BUT mandatory in web apps
			Class.forName("com.mysql.cj.jdbc.Driver");
			// get fixed db connection
			String dbURL = "jdbc:mysql://localhost:3306/iacsd2?useSSL=false&allowPublicKeyRetrieval=true";
			connection = DriverManager.getConnection(dbURL, "root", "1234");
			System.out.println("singleton instance of db cn created");
		}
		return connection;
	}
	//add a static method to close db connection
	public static void closeConnection() throws SQLException
	{
		if(connection != null)
			connection.close();
		System.out.println("db cn closed");
	}

}
