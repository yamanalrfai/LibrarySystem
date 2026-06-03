package librarysystem.controller;

import javafx.stage.Stage;
import librarysystem.view.MainView;

public class MainController {
    private MainView view;
    private Stage stage;

    public MainController(Stage stage) {
        this.stage = stage;
        this.view = new MainView();
        
        // Listeners
        view.getBtnLogin().setOnAction(e -> {
            LoginController loginController = new LoginController(stage);
            stage.setScene(loginController.getView().getScene());
        });

        // Add your Register and About listeners here!
    }

    public MainView getView() { return view; }
}