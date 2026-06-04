package librarysystem.model;
import java.sql.Timestamp;

public class Book {
    private int id;
    private String title;
    private String author;
    private String imageUrl;
    private String genre;
    private String virtualLink;
    private int publishedYear;
    private Timestamp createdAt;

    public Book(int id, String title, String author, String imageUrl, String genre, String virtualLink, int publishedYear, Timestamp createdAt) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.imageUrl = imageUrl;
        this.genre = genre;
        this.virtualLink = virtualLink;
        this.publishedYear = publishedYear;
        this.createdAt = createdAt;
    }

    public int getId() { return id; }
    public String getTitle() { return title; }
    public String getAuthor() { return author; }
    public String getImageUrl() { return imageUrl; }
    public String getGenre() { return genre; }
    public String getVirtualLink() { return virtualLink; }
    public int getPublishedYear() { return publishedYear; }
    public Timestamp getCreatedAt() { return createdAt; }
}