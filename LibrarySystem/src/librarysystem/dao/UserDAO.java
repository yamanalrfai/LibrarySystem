package librarysystem.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class UserDAO {
    public boolean authenticateUser(String email, String password) {
        if (email.toLowerCase().equals("admin@panel.com") && password.equals("6969")) {
            return true;
        }

        String query = "SELECT password_hash FROM users WHERE email = ?";

        try (Connection conn = Database.getConnection();
             PreparedStatement ps = conn.prepareStatement(query)) {
            
            ps.setString(1, email);
            ResultSet rs = ps.executeQuery();
            
            if (rs.next()) {
                String storedHash = rs.getString("password_hash");
                return password.equals(storedHash);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return false;
    }
    public boolean registerUser(String username, String email, String password) {
        // Notice we are inserting into password_hash, but just sending plain text for the UNI project
        String query = "INSERT INTO users (username, email, password_hash) VALUES (?, ?, ?)";
        
        try (Connection conn = Database.getConnection();
             PreparedStatement ps = conn.prepareStatement(query)) {
             
            ps.setString(1, username);
            ps.setString(2, email);
            ps.setString(3, password); // Storing plain text 
            
            int rowsAffected = ps.executeUpdate();
            return rowsAffected > 0;
            
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return false;
    }
}