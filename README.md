# Library Management System

A Java-based Library Management System that allows users to register, log in, browse a catalog of books, and check out items. The system uses a MySQL/SQL database for persistence and employs the Data Access Object (DAO) pattern to cleanly separate database operations from business logic.

## Features

* **User Authentication:** * Users can register with a username, email, and password.
  * Secure login with session tracking.
  * Hardcoded Admin panel access.
* **Book Management:** * View a complete catalog of available books (including titles, authors , and genres).
* **Order Processing (Checkout):** * Users can check out multiple books at once.
  * Transactional database operations: When a book is checked out, an order record is generated, and the book is dynamically removed from the available pool.
* **Order History:** * Users can view their past checkout records and history.
* **Session Management:** * Global session tracking handles logged-in user state across different views of the application.

## Project Structure

The project follows a modular architecture divided into the following key packages:

* `librarysystem.model`: Contains the core data structures (`Book`, `User`, `OrderRecord`).
* `librarysystem.dao`: Contains the Data Access Objects (`BookDAO`, `UserDAO`, `OrderDAO`) responsible for executing SQL queries and database transactions.
* `librarysystem.session`: Contains the `UserSession` utility for tracking the currently authenticated user globally.

## Class Diagram

Below is the Basic class diagram

```mermaid
classDiagram
    class User {
        -int id
        -String username
        -String email
        -String passwordHash
        -Timestamp createdAt
    }

    class Book {
        -int id
        -String title
        -String author
        -String imageUrl
        -String genre
        -String virtualLink
    }

    class OrderRecord {
        -int id
        -int userId
        -int bookId
        -String title
        -String author
        -String genre
        -Timestamp orderedAt
    }

    %% Relationships
    User "1" <-- "*" OrderRecord : placed by (userId)
    Book "1" <-- "*" OrderRecord : references (bookId)
