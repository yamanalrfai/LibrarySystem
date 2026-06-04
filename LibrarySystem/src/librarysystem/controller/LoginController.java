package librarysystem.controller;

import javafx.scene.control.Alert;
import javafx.stage.Stage;
import librarysystem.dao.UserDAO;
import librarysystem.model.User;
import librarysystem.session.UserSession;
import librarysystem.view.LoginView;

public class LoginController {
    private LoginView view;
    private UserDAO userDAO;
    private Stage stage;

    public LoginController(Stage stage) {
        this.stage = stage;
        this.view = new LoginView();
        this.userDAO = new UserDAO();

        view.getBtnLogin().setOnAction(e -> handleLogin());
        
        view.getBtnBack().setOnAction(e -> {
            MainController mainController = new MainController(stage);
            stage.setScene(mainController.getView().getScene());
        });
    }

    private void handleLogin() {
        String email = view.getEmail();
        String password = view.getPassword();

        if (email.isEmpty() || password.isEmpty()) {
            view.showAlert("Please fill in all fields.", Alert.AlertType.WARNING, "Login");
            return;
        }

        if (!email.contains("@") || !email.contains(".")) {
            view.showAlert("Enter a valid email address.", Alert.AlertType.WARNING, "Login");
            return;
        }

        User user = userDAO.authenticateAndGetUser(email, password);

        if (user != null) {
            UserSession.setCurrentUser(user.getId(), user.getUsername(), user.getEmail());
            ViewLibraryController libraryController = new ViewLibraryController(stage);
            stage.setScene(libraryController.getView().getScene());
        } else {
            view.showAlert("Invalid email or password.", Alert.AlertType.WARNING, "Login Failed");
        }
    }

    public LoginView getView() { return view; }
}