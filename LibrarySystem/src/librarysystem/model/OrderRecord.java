package librarysystem.model;

import java.sql.Timestamp;

public class OrderRecord {
    private int id;
    private int userId;
    private int bookId;
    private String title;
    private String author;
    private String genre;
    private Timestamp orderedAt;

    public OrderRecord(int id, int userId, int bookId, String title, String author, String genre, Timestamp orderedAt) {
        this.id = id;
        this.userId = userId;
        this.bookId = bookId;
        this.title = title;
        this.author = author;
        this.genre = genre;
        this.orderedAt = orderedAt;
    }

    public int getId() { return id; }
    public int getUserId() { return userId; }
    public int getBookId() { return bookId; }
    public String getTitle() { return title; }
    public String getAuthor() { return author; }
    public String getGenre() { return genre; }
    public Timestamp getOrderedAt() { return orderedAt; }
}