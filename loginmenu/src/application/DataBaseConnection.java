package application;
import java.sql.Connection;
import java.sql.DriverManager;
// making the connection to the database that was made to hold username
// and password of user
public class DataBaseConnection {
	public Connection dataBaseLink;
	
	public Connection getConnection() {
		String databaseName = "Logindata";
		String databaseUser = "root";
		String databasePassword = "Ja1234!";
		String url = "jdbc:mysql://localhost/" + databaseName;
		
		try {
			Class.forName("com.sql.cj.jdbc.Driver");
			dataBaseLink = DriverManager.getConnection(url,databaseUser,databasePassword);
		} catch (Exception e) {
			e.printStackTrace();
			e.getCause();
		}
		return dataBaseLink;
	}
	
	
}
