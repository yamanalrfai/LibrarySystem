package librarysystem.view;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import librarysystem.controller.ThemeController;

public class LoginView {
    private Scene scene;
    private TextField txtEmail;
    private PasswordField txtPassword;
    private Button btnLogin;
    private Button btnBack;
    private Button btnTheme;

    private Text title;
    private Text subtitle;
    private Label lblEmail;
    private Label lblPassword;
    private javafx.scene.layout.VBox root;

    public LoginView() {
        String redwine = ThemeController.redwine;
        String bgColor = ThemeController.bgColor;
        String subtitleColor = ThemeController.subtitleColor;

        title = new Text("  Welcome Back");
        title.setFont(Font.font("Segoe UI", FontWeight.EXTRA_BOLD, 26));
        title.setStyle("-fx-fill: " + redwine + ";");

        subtitle = new Text("Log in to your LibraryX account");
        subtitle.setFont(Font.font("Segoe UI", 15));
        subtitle.setStyle("-fx-fill: " + subtitleColor + ";");

        VBox titleBox = new VBox(5, title, subtitle);
        titleBox.setAlignment(Pos.CENTER);

        lblEmail = new Label("Email:");
        lblEmail.setStyle("-fx-text-fill: " + redwine + "; -fx-font-size: 13;");
        txtEmail = new TextField();
        txtEmail.setPromptText("Enter email");

        lblPassword = new Label("Password:");
        lblPassword.setStyle("-fx-text-fill: " + redwine + "; -fx-font-size: 13;");
        txtPassword = new PasswordField();
        txtPassword.setPromptText("Enter password");

        VBox fieldBox = new VBox(10, lblEmail, txtEmail, lblPassword, txtPassword);
        fieldBox.setAlignment(Pos.CENTER_LEFT);
        fieldBox.setMaxWidth(300);

        btnLogin = createStyledButton("Login", redwine);
        btnBack = createStyledButton("  Back", redwine);
        btnTheme = createStyledButton("Change Color", redwine);
        btnTheme.setOnAction(e -> {
            ThemeController.switchToDarkMode();
            applyTheme();
        });

        VBox buttons = new VBox(15, btnLogin, btnBack);
        buttons.setAlignment(Pos.CENTER);
        buttons.setMaxWidth(300);

        buttons.getChildren().add(btnTheme);

        root = new VBox(25, titleBox, fieldBox, buttons);
        root.setAlignment(Pos.CENTER);
        root.setPadding(new Insets(50));
        root.setStyle("-fx-background-color: " + bgColor + ";");

        scene = new Scene(root, 550, 520);
    }

    private Button createStyledButton(String text, String color) {
        Button btn = new Button(text);
        btn.setFont(Font.font("Segoe UI", FontWeight.SEMI_BOLD, 16));
        btn.setPrefWidth(300);
        String defaultStyle = "-fx-background-color: transparent; -fx-text-fill: " + color + "; -fx-border-color: " + color + "; -fx-border-width: 2px; -fx-border-radius: 12px; -fx-background-radius: 12px; -fx-cursor: hand;";
        String hoverStyle = "-fx-background-color: " + color + "; -fx-text-fill: white; -fx-border-color: " + color + "; -fx-border-width: 2px; -fx-border-radius: 12px; -fx-background-radius: 12px; -fx-cursor: hand;";
        btn.setStyle(defaultStyle);
        btn.setOnMouseEntered(e -> btn.setStyle(hoverStyle));
        btn.setOnMouseExited(e -> btn.setStyle(defaultStyle));
        return btn;
    }

    private void updateButtonStyle(Button btn, String color) {
        String defaultStyle = "-fx-background-color: transparent; -fx-text-fill: " + color + "; -fx-border-color: " + color + "; -fx-border-width: 2px; -fx-border-radius: 12px; -fx-background-radius: 12px; -fx-cursor: hand;";
        String hoverStyle = "-fx-background-color: " + color + "; -fx-text-fill: white; -fx-border-color: " + color + "; -fx-border-width: 2px; -fx-border-radius: 12px; -fx-background-radius: 12px; -fx-cursor: hand;";
        btn.setStyle(defaultStyle);
        btn.setOnMouseEntered(e -> btn.setStyle(hoverStyle));
        btn.setOnMouseExited(e -> btn.setStyle(defaultStyle));
    }

    private void applyTheme() {
        String redwine = ThemeController.redwine;
        String bgColor = ThemeController.bgColor;
        String subtitleColor = ThemeController.subtitleColor;

        title.setStyle("-fx-fill: " + redwine + ";");
        subtitle.setStyle("-fx-fill: " + subtitleColor + ";");
        lblEmail.setStyle("-fx-text-fill: " + redwine + "; -fx-font-size: 13;");
        lblPassword.setStyle("-fx-text-fill: " + redwine + "; -fx-font-size: 13;");
        root.setStyle("-fx-background-color: " + bgColor + ";");

        updateButtonStyle(btnLogin, redwine);
        updateButtonStyle(btnBack, redwine);
        updateButtonStyle(btnTheme, redwine);
    }

    public Scene getScene() { return scene; }
    public String getEmail() { return txtEmail.getText().trim(); }
    public String getPassword() { return txtPassword.getText().trim(); }
    public Button getBtnLogin() { return btnLogin; }
    public Button getBtnBack() { return btnBack; }

    public void showAlert(String msg, Alert.AlertType type, String title) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(msg);
        alert.showAndWait();
    }
}