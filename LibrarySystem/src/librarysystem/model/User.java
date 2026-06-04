package librarysystem.model;
import java.sql.Timestamp;

public class User {
    private int id;
    private String username;
    private String email;
    private String passwordHash;
    private Timestamp createdAt;

    public User(int id, String username, String email, String passwordHash, Timestamp createdAt) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.passwordHash = passwordHash;
        this.createdAt = createdAt;
    }

    public int getId() { return id; }
    public String getUsername() { return username; }
    public String getEmail() { return email; }
    public String getPasswordHash() { return passwordHash; }
    public Timestamp getCreatedAt() { return createdAt; }
}