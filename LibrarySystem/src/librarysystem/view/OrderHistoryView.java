package librarysystem.view;

import java.util.List;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import librarysystem.model.OrderRecord;

public class OrderHistoryView {
    private Scene scene;
    private Button btnBack;
    private Button btnLogout;
    private VBox orderContainer;
    private Label lblEmpty;

    public OrderHistoryView() {
        String redwine = "#8B0000";
        String bgColor = "#F4F4F4";

        Label title = new Label("My Orders");
        title.setStyle("-fx-text-fill: " + redwine + "; -fx-font-family: 'Segoe UI'; -fx-font-weight: bold; -fx-font-size: 24;");

        HBox header = new HBox(10,
            createHeaderLabel("Title", 240),
            createHeaderLabel("Author", 160),
            createHeaderLabel("Genre", 120),
            createHeaderLabel("Ordered At", 180)
        );
        header.setPadding(new Insets(10));
        header.setStyle("-fx-background-color: #DDDDDD;");

        orderContainer = new VBox(5);
        orderContainer.setPadding(new Insets(5));

        ScrollPane scrollPane = new ScrollPane(orderContainer);
        scrollPane.setFitToWidth(true);
        scrollPane.setPrefHeight(300);
        scrollPane.setStyle("-fx-background-color: transparent; -fx-background: #FFFFFF;");

        lblEmpty = new Label("No orders yet.");
        lblEmpty.setStyle("-fx-text-fill: #666666; -fx-font-family: 'Segoe UI'; -fx-font-size: 14;");

        btnBack = new Button("Back to Library");
        btnBack.setPrefWidth(180);
        btnBack.setStyle("-fx-background-color: transparent; -fx-text-fill: " + redwine + "; -fx-border-color: " + redwine + "; -fx-border-width: 2px; -fx-cursor: hand; -fx-font-family: 'Segoe UI'; -fx-font-size: 14; -fx-font-weight: bold;");

        btnLogout = new Button("Logout");
        btnLogout.setPrefWidth(180);
        btnLogout.setStyle("-fx-background-color: " + redwine + "; -fx-text-fill: white; -fx-cursor: hand; -fx-font-family: 'Segoe UI'; -fx-font-size: 14; -fx-font-weight: bold;");

        HBox buttons = new HBox(10, btnBack, btnLogout);
        buttons.setAlignment(Pos.CENTER);

        VBox root = new VBox(15, title, header, scrollPane, lblEmpty, buttons);
        root.setPadding(new Insets(20));
        root.setAlignment(Pos.CENTER);
        root.setStyle("-fx-background-color: " + bgColor + ";");

        scene = new Scene(root, 820, 520);
    }

    private Label createHeaderLabel(String text, double width) {
        Label label = new Label(text);
        label.setPrefWidth(width);
        label.setStyle("-fx-font-family: 'Segoe UI'; -fx-font-weight: bold; -fx-font-size: 14;");
        return label;
    }

    public void renderOrders(List<OrderRecord> orders) {
        orderContainer.getChildren().clear();

        if (orders == null || orders.isEmpty()) {
            lblEmpty.setVisible(true);
            lblEmpty.setManaged(true);
            return;
        }

        lblEmpty.setVisible(false);
        lblEmpty.setManaged(false);

        for (OrderRecord order : orders) {
            HBox row = new HBox(10,
                createRowLabel(order.getTitle(), 240),
                createRowLabel(order.getAuthor(), 160),
                createRowLabel(order.getGenre(), 120),
                createRowLabel(order.getOrderedAt() == null ? "" : order.getOrderedAt().toString(), 180)
            );
            row.setPadding(new Insets(8));
            row.setStyle("-fx-border-color: #DDDDDD; -fx-border-width: 0 0 1 0;");
            orderContainer.getChildren().add(row);
        }
    }

    private Label createRowLabel(String text, double width) {
        Label label = new Label(text);
        label.setPrefWidth(width);
        label.setStyle("-fx-font-family: 'Segoe UI'; -fx-font-size: 14;");
        return label;
    }

    public void showAlert(String msg, Alert.AlertType type, String title) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(msg);
        alert.showAndWait();
    }

    public Scene getScene() { return scene; }
    public Button getBtnBack() { return btnBack; }
    public Button getBtnLogout() { return btnLogout; }
}