package librarysystem.view;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

public class RegisterView {
    private Scene scene;
    private TextField txtUsername;
    private TextField txtEmail;
    private PasswordField txtPassword;
    private Button btnRegister;
    private Button btnBack;

    public RegisterView() {
        String redwine = "#8B0000";
        String bgColor = "#F4F4F4";
        String subtitleColor = "#5A5A5A";

        Text title = new Text("  Create Account");
        title.setFont(Font.font("Segoe UI", FontWeight.EXTRA_BOLD, 26));
        title.setStyle("-fx-fill: " + redwine + ";");

        Text subtitle = new Text("Join LibraryX today");
        subtitle.setFont(Font.font("Segoe UI", 15));
        subtitle.setStyle("-fx-fill: " + subtitleColor + ";");

        VBox titleBox = new VBox(5, title, subtitle);
        titleBox.setAlignment(Pos.CENTER);

        // Form Fields
        Label lblUsername = new Label("Username:");
        lblUsername.setStyle("-fx-text-fill: " + redwine + "; -fx-font-size: 13;");
        txtUsername = new TextField();
        txtUsername.setPromptText("Enter username");

        Label lblEmail = new Label("Email:");
        lblEmail.setStyle("-fx-text-fill: " + redwine + "; -fx-font-size: 13;");
        txtEmail = new TextField();
        txtEmail.setPromptText("Enter email");

        Label lblPassword = new Label("Password:");
        lblPassword.setStyle("-fx-text-fill: " + redwine + "; -fx-font-size: 13;");
        txtPassword = new PasswordField();
        txtPassword.setPromptText("Create password");

        VBox fieldBox = new VBox(10, lblUsername, txtUsername, lblEmail, txtEmail, lblPassword, txtPassword);
        fieldBox.setAlignment(Pos.CENTER_LEFT);
        fieldBox.setMaxWidth(300);

        btnRegister = createStyledButton("Register", redwine);
        btnBack = createStyledButton("  Back", redwine);

        VBox buttons = new VBox(15, btnRegister, btnBack);
        buttons.setAlignment(Pos.CENTER);
        buttons.setMaxWidth(300);

        VBox root = new VBox(25, titleBox, fieldBox, buttons);
        root.setAlignment(Pos.CENTER);
        root.setPadding(new Insets(50));
        root.setStyle("-fx-background-color: " + bgColor + ";");

        scene = new Scene(root, 550, 550);
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

    public Scene getScene() { return scene; }
    public String getUsername() { return txtUsername.getText().trim(); }
    public String getEmail() { return txtEmail.getText().trim(); }
    public String getPassword() { return txtPassword.getText().trim(); }
    public Button getBtnRegister() { return btnRegister; }
    public Button getBtnBack() { return btnBack; }

    public void showAlert(String msg, Alert.AlertType type, String title) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(msg);
        alert.showAndWait();
    }
}