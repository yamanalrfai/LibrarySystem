package librarysystem.view;

import java.util.List;
import java.util.function.Consumer;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import librarysystem.model.Book;

public class ViewLibraryView {
    private Scene scene;
    private Button btnLogout;
    private Button btnCheckout;
    private Button btnViewOrders;
    private VBox bookContainer;
    private VBox cartContainer;
    private Label lblUser;
    private Label lblCartEmpty;

    public ViewLibraryView() {
        String redwine = "#8B0000";
        String bgColor = "#F4F4F4";

        Label title = new Label("Library Collection");
        title.setStyle("-fx-text-fill: " + redwine + "; -fx-font-family: 'Segoe UI'; -fx-font-weight: bold; -fx-font-size: 24;");

        lblUser = new Label("Signed in user");
        lblUser.setStyle("-fx-text-fill: #555555; -fx-font-size: 13; -fx-font-family: 'Segoe UI';");

        HBox header = createRow("Title", "Author", "Genre", "Action", true, null);
        header.setStyle("-fx-background-color: #DDDDDD; -fx-padding: 10;");

        bookContainer = new VBox(5);
        bookContainer.setPadding(new Insets(5));

        ScrollPane bookScrollPane = new ScrollPane(bookContainer);
        bookScrollPane.setFitToWidth(true);
        bookScrollPane.setPrefHeight(270);
        bookScrollPane.setStyle("-fx-background-color: transparent; -fx-background: #FFFFFF;");

        VBox bookSection = new VBox(10, header, bookScrollPane);
        bookSection.setPrefWidth(520);

        Label cartTitle = new Label("Cart");
        cartTitle.setStyle("-fx-text-fill: " + redwine + "; -fx-font-family: 'Segoe UI'; -fx-font-weight: bold; -fx-font-size: 18;");

        cartContainer = new VBox(5);
        cartContainer.setPadding(new Insets(5));

        lblCartEmpty = new Label("Your cart is empty.");
        lblCartEmpty.setStyle("-fx-text-fill: #666666; -fx-font-family: 'Segoe UI';");

        ScrollPane cartScrollPane = new ScrollPane(cartContainer);
        cartScrollPane.setFitToWidth(true);
        cartScrollPane.setPrefHeight(180);
        cartScrollPane.setStyle("-fx-background-color: transparent; -fx-background: #FFFFFF;");

        btnCheckout = new Button("Checkout Cart");
        btnCheckout.setPrefWidth(180);
        btnCheckout.setStyle("-fx-background-color: " + redwine + "; -fx-text-fill: white; -fx-cursor: hand; -fx-font-family: 'Segoe UI';");

        btnViewOrders = new Button("View My Orders");
        btnViewOrders.setPrefWidth(180);
        btnViewOrders.setStyle("-fx-background-color: transparent; -fx-text-fill: " + redwine + "; -fx-border-color: " + redwine + "; -fx-border-width: 2px; -fx-cursor: hand; -fx-font-family: 'Segoe UI';");

        btnLogout = new Button("Logout");
        btnLogout.setPrefWidth(180);
        btnLogout.setStyle("-fx-background-color: " + redwine + "; -fx-text-fill: white; -fx-cursor: hand; -fx-font-family: 'Segoe UI';");

        VBox cartSection = new VBox(10, cartTitle, cartScrollPane, lblCartEmpty, btnCheckout, btnViewOrders, btnLogout);
        cartSection.setPrefWidth(260);

        HBox mainContent = new HBox(15, bookSection, cartSection);
        mainContent.setAlignment(Pos.CENTER);

        VBox root = new VBox(15, title, lblUser, mainContent);
        root.setPadding(new Insets(20));
        root.setAlignment(Pos.CENTER);
        root.setStyle("-fx-background-color: " + bgColor + ";");

        scene = new Scene(root, 850, 560);
    }

    public void renderBooks(List<Book> books, Consumer<Book> onAddToCart) {
        bookContainer.getChildren().clear();

        if (books == null) {
            return;
        }

        for (Book book : books) {
            bookContainer.getChildren().add(createRow(book.getTitle(), book.getAuthor(), book.getGenre(), "Add", false, onAddToCart == null ? null : () -> onAddToCart.accept(book)));
        }
    }

    public void renderCart(List<Book> books, Consumer<Book> onRemoveFromCart) {
        cartContainer.getChildren().clear();

        if (books == null || books.isEmpty()) {
            lblCartEmpty.setVisible(true);
            lblCartEmpty.setManaged(true);
            return;
        }

        lblCartEmpty.setVisible(false);
        lblCartEmpty.setManaged(false);

        for (Book book : books) {
            cartContainer.getChildren().add(createRow(book.getTitle(), book.getAuthor(), book.getGenre(), "Remove", false, onRemoveFromCart == null ? null : () -> onRemoveFromCart.accept(book)));
        }
    }

    public HBox createRow(String titleStr, String authorStr, String genreStr, boolean isHeader) {
        return createRow(titleStr, authorStr, genreStr, "", isHeader, null);
    }

    public HBox createRow(String titleStr, String authorStr, String genreStr, String actionText, boolean isHeader, Runnable action) {
        Label lblTitle = new Label(titleStr);
        lblTitle.setPrefWidth(220);

        Label lblAuthor = new Label(authorStr);
        lblAuthor.setPrefWidth(150);

        Label lblGenre = new Label(genreStr);
        lblGenre.setPrefWidth(120);

        if (isHeader) {
            lblTitle.setStyle("-fx-font-family: 'Segoe UI'; -fx-font-weight: bold; -fx-font-size: 14;");
            lblAuthor.setStyle("-fx-font-family: 'Segoe UI'; -fx-font-weight: bold; -fx-font-size: 14;");
            lblGenre.setStyle("-fx-font-family: 'Segoe UI'; -fx-font-weight: bold; -fx-font-size: 14;");
            Label lblAction = new Label(actionText);
            lblAction.setPrefWidth(120);
            lblAction.setStyle("-fx-font-family: 'Segoe UI'; -fx-font-weight: bold; -fx-font-size: 14;");
            HBox row = new HBox(10, lblTitle, lblAuthor, lblGenre, lblAction);
            row.setPadding(new Insets(5));
            return row;
        } else {
            lblTitle.setStyle("-fx-font-family: 'Segoe UI'; -fx-font-size: 14;");
            lblAuthor.setStyle("-fx-font-family: 'Segoe UI'; -fx-font-size: 14;");
            lblGenre.setStyle("-fx-font-family: 'Segoe UI'; -fx-font-size: 14;");
        }

        Button actionButton = new Button(actionText);
        actionButton.setPrefWidth(120);
        actionButton.setStyle("-fx-background-color: #8B0000; -fx-text-fill: white; -fx-cursor: hand; -fx-font-family: 'Segoe UI';");
        if (action != null) {
            actionButton.setOnAction(e -> action.run());
        }

        HBox row = new HBox(10, lblTitle, lblAuthor, lblGenre, actionButton);
        row.setPadding(new Insets(5));

        if (!isHeader) {
            row.setStyle("-fx-border-color: #CCCCCC; -fx-border-width: 0 0 1 0;"); 
        }

        return row;
    }

    public Scene getScene() { return scene; }
    public Button getBtnLogout() { return btnLogout; }
    public Button getBtnCheckout() { return btnCheckout; }
    public Button getBtnViewOrders() { return btnViewOrders; }
    public void setUserLabel(String text) { lblUser.setText(text); }

    public void showAlert(String msg, Alert.AlertType type, String title) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(msg);
        alert.showAndWait();
    }
}