import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class UserDAO {

    // Method for user registration
    public void register(Scanner scanner) {
        System.out.println("Enter a username:");
        String username = scanner.nextLine();

        System.out.println("Enter an email:");
        String email = scanner.nextLine();

        System.out.println("Enter a password:");
        String password = scanner.nextLine();

        // SQL statement to insert a new user into the database
        String sql = "INSERT INTO users (username, email, password) VALUES (?, ?, ?)";

        try (Connection conn = DatabaseConnection.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            // Set the values for the prepared statement
            pstmt.setString(1, username);
            pstmt.setString(2, email);
            pstmt.setString(3, password);

            // Execute the insert statement
            int rowsInserted = pstmt.executeUpdate();

            if (rowsInserted > 0) {
                System.out.println("Registration successful!");
            } else {
                System.out.println("Registration failed.");
            }

        } catch (SQLException e) {
            System.out.println("An error occurred during registration: " + e.getMessage());
        }
    }

    // Method for user login
    public String login(Scanner scanner) {
        System.out.println("Enter email:");
        String email = scanner.nextLine();

        System.out.println("Enter password:");
        String password = scanner.nextLine();

        String sql = "SELECT username FROM users WHERE email = ? AND password = ?";
        try (Connection conn = DatabaseConnection.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, email);
            pstmt.setString(2, password);

            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                return rs.getString("username");
            } else {
                System.out.println("Invalid email or password.");
                return null;
            }

        } catch (SQLException e) {
            System.out.println("An error occurred during login: " + e.getMessage());
            return null;
        }
    }

    // Additional methods can be added here...

}
