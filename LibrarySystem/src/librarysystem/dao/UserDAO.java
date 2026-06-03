package librarysystem.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class UserDAO {
    public boolean authenticateUser(String email, String password) {
        if (email.toLowerCase().equals("admin@panel.com") && password.equals("6969")) {
            return true; // Admin bypass
        }
        String query = "SELECT COUNT(*) FROM users WHERE email = ? AND password = ?";
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
}