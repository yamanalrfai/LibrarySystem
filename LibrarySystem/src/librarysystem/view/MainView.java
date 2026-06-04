package librarysystem.view;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import librarysystem.controller.ThemeController;

public class MainView {
    private Scene scene;
    private Button btnLogin;
    private Button btnRegister;
    private Hyperlink aboutLink;
    private Button btnTheme;

    private Text title;
    private Text subtitle;
    private Text secondSubtitle;
    private javafx.scene.layout.VBox root;

    public MainView() {
        String redwine = ThemeController.redwine;
        String bgColor = ThemeController.bgColor;
        String subtitleColor = ThemeController.subtitleColor;

        title = new Text("  Welcome to LibraryX");
        title.setStyle("-fx-fill: " + redwine + "; -fx-font-family: 'Segoe UI'; -fx-font-weight: bold; -fx-font-size: 30;");

        subtitle = new Text("Your personal gateway to discovering and managing books.");
        subtitle.setStyle("-fx-fill: " + subtitleColor + "; -fx-font-family: 'Segoe UI'; -fx-font-weight: normal; -fx-font-size: 16;");

        secondSubtitle = new Text("Access thousands of titles. Organize your favorites. Read smarter.");
        secondSubtitle.setStyle("-fx-fill: " + subtitleColor + "; -fx-font-family: 'Segoe UI'; -fx-font-weight: normal; -fx-font-size: 15;");

        btnLogin = createStyledButton("  Sign In", redwine);
        btnRegister = createStyledButton("  Get Started", redwine);
        btnTheme = createStyledButton("Change Color", redwine);
        btnTheme.setOnAction(e -> { ThemeController.switchToDarkMode(); applyTheme(); });

        aboutLink = new Hyperlink("Learn more about LibraryX");
        aboutLink.setStyle("-fx-text-fill: " + redwine + "; -fx-font-family: 'Segoe UI'; -fx-font-size: 13;");

        Text footer = new Text("LibraryX   Elevate your reading experience.");
        footer.setStyle("-fx-fill: " + redwine + "; -fx-font-family: 'Segoe UI'; -fx-font-weight: 600; -fx-font-size: 13;"); // 600 maps to Semi-Bold

        VBox buttons = new VBox(10, btnLogin, btnRegister, btnTheme);
        buttons.setAlignment(Pos.CENTER);

        root = new VBox(15, title, subtitle, secondSubtitle, buttons, aboutLink, footer);
        root.setAlignment(Pos.CENTER);
        root.setPadding(new Insets(60));
        root.setStyle("-fx-background-color: " + bgColor + ";");

        scene = new Scene(root, 550, 550);
    }

    private Button createStyledButton(String text, String color) {
        Button btn = new Button(text);
        btn.setPrefWidth(220);
        btn.setPrefHeight(45);
        
        String fontStyle = "-fx-font-family: 'Segoe UI'; -fx-font-weight: 600; -fx-font-size: 16; ";
        String defaultStyle = fontStyle + "-fx-background-color: transparent; -fx-text-fill: " + color + "; -fx-border-color: " + color + "; -fx-border-width: 2px; -fx-border-radius: 12px; -fx-background-radius: 12px; -fx-cursor: hand;";
        String hoverStyle = fontStyle + "-fx-background-color: " + color + "; -fx-text-fill: white; -fx-border-color: " + color + "; -fx-border-width: 2px; -fx-border-radius: 12px; -fx-background-radius: 12px; -fx-cursor: hand;";
        
        btn.setStyle(defaultStyle);
        btn.setOnMouseEntered(e -> btn.setStyle(hoverStyle));
        btn.setOnMouseExited(e -> btn.setStyle(defaultStyle));
        return btn;
    }

    private void updateButtonStyle(Button btn, String color) {
        String fontStyle = "-fx-font-family: 'Segoe UI'; -fx-font-weight: 600; -fx-font-size: 16; ";
        String defaultStyle = fontStyle + "-fx-background-color: transparent; -fx-text-fill: " + color + "; -fx-border-color: " + color + "; -fx-border-width: 2px; -fx-border-radius: 12px; -fx-background-radius: 12px; -fx-cursor: hand;";
        String hoverStyle = fontStyle + "-fx-background-color: " + color + "; -fx-text-fill: white; -fx-border-color: " + color + "; -fx-border-width: 2px; -fx-border-radius: 12px; -fx-background-radius: 12px; -fx-cursor: hand;";
        btn.setStyle(defaultStyle);
        btn.setOnMouseEntered(e -> btn.setStyle(hoverStyle));
        btn.setOnMouseExited(e -> btn.setStyle(defaultStyle));
    }

    private void applyTheme() {
        String redwine = ThemeController.redwine;
        String bgColor = ThemeController.bgColor;
        String subtitleColor = ThemeController.subtitleColor;

        title.setStyle("-fx-fill: " + redwine + "; -fx-font-family: 'Segoe UI'; -fx-font-weight: bold; -fx-font-size: 30;");
        subtitle.setStyle("-fx-fill: " + subtitleColor + "; -fx-font-family: 'Segoe UI'; -fx-font-weight: normal; -fx-font-size: 16;");
        secondSubtitle.setStyle("-fx-fill: " + subtitleColor + "; -fx-font-family: 'Segoe UI'; -fx-font-weight: normal; -fx-font-size: 15;");
        root.setStyle("-fx-background-color: " + bgColor + ";");

        updateButtonStyle(btnLogin, redwine);
        updateButtonStyle(btnRegister, redwine);
        updateButtonStyle(btnTheme, redwine);
        aboutLink.setStyle("-fx-text-fill: " + redwine + "; -fx-font-family: 'Segoe UI'; -fx-font-size: 13;");
    }

    public Scene getScene() { return scene; }
    public Button getBtnLogin() { return btnLogin; }
    public Button getBtnRegister() { return btnRegister; }
    public Hyperlink getAboutLink() { return aboutLink; }
}