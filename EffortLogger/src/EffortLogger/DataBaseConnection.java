package application;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DataBaseConnection {
	 // JDBC URL, username, and password of MySQL server
    private static final String JDBC_URL = "jdbc:mysql://localhost:3306/sql_project";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "Appliance@123";

    public static void saveParametersToDatabase(String param1, String param2, String param3,String param4, String param5, long param6, long param7) {
        try {
            // Establish a connection
            Connection connection = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD);

            // Define the SQL query with placeholders (?)
            String sql = "INSERT INTO effortlog (category, language, project, lifeCycleStep, domain, timeStart, timeStop) VALUES (?, ?, ?, ?, ?, ?, ?)";

            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                // Assuming categoty is the variable you want to use
            	preparedStatement.setString(1, param1);
                preparedStatement.setString(2, param2);
                preparedStatement.setString(3, param3);
                preparedStatement.setString(4, param4);  // Assuming lifeCycleStep is the variable you want to use
                preparedStatement.setString(5, param5);
                preparedStatement.setLong(6, param6);
                preparedStatement.setLong(7, param7);
            
                
                // Execute the query
                preparedStatement.executeUpdate();
            }


            // Close the connection
            connection.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
