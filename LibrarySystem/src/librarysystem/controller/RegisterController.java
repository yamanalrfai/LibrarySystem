package librarysystem.controller;

import javafx.scene.control.Alert;
import javafx.stage.Stage;
import librarysystem.dao.UserDAO;
import librarysystem.view.RegisterView;

public class RegisterController {
    private RegisterView view;
    private UserDAO userDAO;
    private Stage stage;

    public RegisterController(Stage stage) {
        this.stage = stage;
        this.view = new RegisterView();
        this.userDAO = new UserDAO();
        view.getBtnRegister().setOnAction(e -> handleRegistration());
        view.getBtnBack().setOnAction(e -> {
            MainController mainController = new MainController(stage);
            stage.setScene(mainController.getView().getScene());
        });
    }

    private void handleRegistration() {
        String username = view.getUsername();
        String email = view.getEmail();
        String password = view.getPassword();

        if (username.isEmpty() || email.isEmpty() || password.isEmpty()) {
            view.showAlert("Please fill in all fields.", Alert.AlertType.WARNING, "Registration");
            return;
        }
        if (!email.contains("@") || !email.contains(".")) {
            view.showAlert("Enter a valid email address.", Alert.AlertType.WARNING, "Registration");
            return;
        }
        if (userDAO.registerUser(username, email, password)) {
            view.showAlert("Registration successful! You can now log in.", Alert.AlertType.INFORMATION, "Success");
            LoginController loginController = new LoginController(stage);
            stage.setScene(loginController.getView().getScene());
        } else {
            view.showAlert("Registration failed. Email or Username might already exist.", Alert.AlertType.ERROR, "Error");
        }
    }

    public RegisterView getView() { return view; }
}