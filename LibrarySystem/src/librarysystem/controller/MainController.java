package librarysystem.controller;

import javafx.stage.Stage;
import librarysystem.view.MainView;

public class MainController {
    private MainView view;
    private Stage stage;

    public MainController(Stage stage) {
        this.stage = stage;
        this.view = new MainView();
        
        view.getBtnLogin().setOnAction(e -> {
            LoginController loginController = new LoginController(stage);
            stage.setScene(loginController.getView().getScene());
        });
        view.getBtnRegister().setOnAction(e -> {
            RegisterController registerController = new RegisterController(stage);
            stage.setScene(registerController.getView().getScene());
        });
    }

    public MainView getView() { return view; }
}