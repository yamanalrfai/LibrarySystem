package librarysystem.model;

public class Book {
    private int id;
    private String title;
    private String author;
    private String imageUrl;
    private String genre;
    private String virtualLink;

    public Book(int id, String title, String author, String imageUrl, String genre, String virtualLink) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.imageUrl = imageUrl;
        this.genre = genre;
        this.virtualLink = virtualLink;
    }

    public int getId() { return id; }
    public String getTitle() { return title; }
    public String getAuthor() { return author; }
    public String getImageUrl() { return imageUrl; }
    public String getGenre() { return genre; }
    public String getVirtualLink() { return virtualLink; }
}