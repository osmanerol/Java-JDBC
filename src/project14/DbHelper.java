package project14;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbHelper {
	private String username="root";
	private String password="12345";
	private String dbUrl="jdbc:mysql://localhost:3306/world?useSSL=false&useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";

	public Connection getConnection() throws SQLException  {
		return DriverManager.getConnection(dbUrl,username,password);
	}
	
	public void showErrorMessage(SQLException exception) {
		System.out.println(exception.getMessage());
		System.out.println("Error code: "+exception.getErrorCode());
	}
	
}