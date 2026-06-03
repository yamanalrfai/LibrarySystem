package librarysystem;

import javafx.application.Application;
import javafx.stage.Stage;
import librarysystem.controller.MainController;

public class Main extends Application {
    @Override
    public void start(Stage primaryStage) {
        // MVC Start! Initialize the Main Controller
        MainController mainController = new MainController(primaryStage);
        
        primaryStage.setTitle("LibraryX");
        primaryStage.setScene(mainController.getView().getScene());
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}