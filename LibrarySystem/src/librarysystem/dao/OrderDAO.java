package librarysystem.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import librarysystem.model.Book;
import librarysystem.model.OrderRecord;

public class OrderDAO {
    public boolean checkoutBooks(int userId, List<Book> books) {
        if (books == null || books.isEmpty()) {
            return false;
        }

        String insertOrder = "INSERT INTO orders (user_id, book_id, title, author, genre) VALUES (?, ?, ?, ?, ?)";
        String deleteBook = "DELETE FROM books WHERE id = ?";

        try (Connection conn = Database.getConnection()) {
            conn.setAutoCommit(false);

            try (PreparedStatement insertStatement = conn.prepareStatement(insertOrder);
                 PreparedStatement deleteStatement = conn.prepareStatement(deleteBook)) {
                for (Book book : books) {
                    insertStatement.setInt(1, userId);
                    insertStatement.setInt(2, book.getId());
                    insertStatement.setString(3, book.getTitle());
                    insertStatement.setString(4, book.getAuthor());
                    insertStatement.setString(5, book.getGenre());
                    insertStatement.addBatch();

                    deleteStatement.setInt(1, book.getId());
                    deleteStatement.addBatch();
                }

                insertStatement.executeBatch();
                deleteStatement.executeBatch();
                conn.commit();
                return true;
            } catch (Exception ex) {
                conn.rollback();
                ex.printStackTrace();
            } finally {
                conn.setAutoCommit(true);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return false;
    }

    public List<OrderRecord> getOrdersForUser(int userId) {
        List<OrderRecord> orders = new ArrayList<>();
        String query = "SELECT id, user_id, book_id, title, author, genre, ordered_at FROM orders WHERE user_id = ? ORDER BY ordered_at DESC, id DESC";

        try (Connection conn = Database.getConnection();
             PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setInt(1, userId);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                orders.add(new OrderRecord(
                    rs.getInt("id"),
                    rs.getInt("user_id"),
                    rs.getInt("book_id"),
                    rs.getString("title"),
                    rs.getString("author"),
                    rs.getString("genre"),
                    rs.getTimestamp("ordered_at")
                ));
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return orders;
    }
}