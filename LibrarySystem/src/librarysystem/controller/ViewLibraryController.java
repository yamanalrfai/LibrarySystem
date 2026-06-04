package librarysystem.controller;

import javafx.collections.FXCollections;
import javafx.stage.Stage;
import librarysystem.dao.BookDAO;
import librarysystem.model.Book;
import librarysystem.view.ViewLibraryView;
import java.util.List;

public class ViewLibraryController {
    private ViewLibraryView view;
    private BookDAO bookDAO;
    private Stage stage;

    public ViewLibraryController(Stage stage) {
        this.stage = stage;
        this.view = new ViewLibraryView();
        this.bookDAO = new BookDAO();

        loadBooks();

        view.getBtnLogout().setOnAction(e -> {
            MainController mainController = new MainController(stage);
            stage.setScene(mainController.getView().getScene());
        });
    }

    private void loadBooks() {
        List<Book> books = bookDAO.getAllBooks();
        view.getTable().setItems(FXCollections.observableArrayList(books));
    }

    public ViewLibraryView getView() { return view; }
}