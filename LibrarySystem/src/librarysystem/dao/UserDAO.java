package librarysystem.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class UserDAO {
    
    public boolean authenticateUser(String email, String password) {
        if (email.toLowerCase().equals("admin@panel.com") && password.equals("6969")) {
            return true; // Admin bypass
        }
        // Using password_hash to match the SQL database we built
        String query = "SELECT COUNT(*) FROM users WHERE email = ? AND password_hash = ?";
        try (Connection conn = Database.getConnection();
             PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setString(1, email);
            ps.setString(2, password);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getInt(1) == 1;
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return false;
    }

    public boolean registerUser(String username, String email, String password) {
        String query = "INSERT INTO users (username, email, password_hash) VALUES (?, ?, ?)";
        try (Connection conn = Database.getConnection();
             PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setString(1, username);
            ps.setString(2, email);
            ps.setString(3, password); 
            return ps.executeUpdate() > 0;
        } catch (Exception ex) {
            ex.printStackTrace(); // Usually fails if email/username already exists
        }
        return false;
    }
}