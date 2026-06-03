package librarysystem.view;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

public class MainView {
    private Scene scene;
    private Button btnLogin;
    private Button btnRegister;
    private Hyperlink aboutLink;

    public MainView() {
        String redwine = "#8B0000";
        String bgColor = "#F4F4F4";
        String subtitleColor = "#5A5A5A";

        Text title = new Text("  Welcome to LibraryX");
        title.setFont(Font.font("Segoe UI", FontWeight.BOLD, 30));
        title.setStyle("-fx-fill: " + redwine + ";");

        Text subtitle = new Text("Your personal gateway to discovering and managing books.");
        subtitle.setFont(Font.font("Segoe UI", FontWeight.NORMAL, 16));
        subtitle.setStyle("-fx-fill: " + subtitleColor + ";");

        Text secondSubtitle = new Text("Access thousands of titles. Organize your favorites. Read smarter.");
        secondSubtitle.setFont(Font.font("Segoe UI", FontWeight.NORMAL, 15));
        secondSubtitle.setStyle("-fx-fill: " + subtitleColor + ";");

        btnLogin = createStyledButton("  Sign In", redwine);
        btnRegister = createStyledButton("  Get Started", redwine);

        aboutLink = new Hyperlink("Learn more about LibraryX");
        aboutLink.setFont(Font.font("Segoe UI", 13));
        aboutLink.setStyle("-fx-text-fill: " + redwine + ";");

        Text footer = new Text("LibraryX   Elevate your reading experience.");
        footer.setFont(Font.font("Segoe UI", FontWeight.SEMI_BOLD, 13));
        footer.setStyle("-fx-fill: " + redwine + ";");

        VBox root = new VBox(15, title, subtitle, secondSubtitle, btnLogin, btnRegister, aboutLink, footer);
        root.setAlignment(Pos.CENTER);
        root.setPadding(new Insets(60));
        root.setStyle("-fx-background-color: " + bgColor + ";");

        scene = new Scene(root, 550, 550);
    }

    private Button createStyledButton(String text, String color) {
        Button btn = new Button(text);
        btn.setFont(Font.font("Segoe UI", FontWeight.SEMI_BOLD, 16));
        btn.setPrefWidth(220);
        btn.setPrefHeight(45);
        String defaultStyle = "-fx-background-color: transparent; -fx-text-fill: " + color + "; -fx-border-color: " + color + "; -fx-border-width: 2px; -fx-border-radius: 12px; -fx-background-radius: 12px; -fx-cursor: hand;";
        String hoverStyle = "-fx-background-color: " + color + "; -fx-text-fill: white; -fx-border-color: " + color + "; -fx-border-width: 2px; -fx-border-radius: 12px; -fx-background-radius: 12px; -fx-cursor: hand;";
        btn.setStyle(defaultStyle);
        btn.setOnMouseEntered(e -> btn.setStyle(hoverStyle));
        btn.setOnMouseExited(e -> btn.setStyle(defaultStyle));
        return btn;
    }

    public Scene getScene() { return scene; }
    public Button getBtnLogin() { return btnLogin; }
    public Button getBtnRegister() { return btnRegister; }
    public Hyperlink getAboutLink() { return aboutLink; }
}