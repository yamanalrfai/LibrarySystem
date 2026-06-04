package librarysystem.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import librarysystem.model.User;

public class UserDAO {

    public User authenticateAndGetUser(String email, String password) {

        String query = "SELECT id, username, email, password_hash, created_at FROM users WHERE email = ? AND password_hash = ?";
        try (Connection conn = Database.getConnection();
             PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setString(1, email);
            ps.setString(2, password);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return new User(
                    rs.getInt("id"),
                    rs.getString("username"),
                    rs.getString("email"),
                    rs.getString("password_hash"),
                    rs.getTimestamp("created_at")
                );
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return null;
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
            ex.printStackTrace();
        }
        return false;
    }
}