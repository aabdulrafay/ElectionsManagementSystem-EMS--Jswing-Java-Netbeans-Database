package ElectionManagementSystem;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
    private static Connection connection;

    public static Connection getConnection() {
        try {
            if (connection == null) {
                String url = "jdbc:mysql://localhost:3306/ElectionDB";
                String user = "root";
                String password = "abdulrafay";
                connection = DriverManager.getConnection(url, user, password);
                System.out.println("Database connected successfully.");
            }
        } catch (SQLException e) {
            System.out.println("Error connecting to DB: " + e.getMessage());
        }
        return connection;
    }
}