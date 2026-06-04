package librarysystem.view;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import librarysystem.model.Book;

public class ViewLibraryView {
    private Scene scene;
    private Button btnLogout;
    private TableView<Book> table;

    public ViewLibraryView() {
        Label title = new Label("Library Collection");
        title.setFont(new Font("Segoe UI", 24));

        table = new TableView<>();
        
        TableColumn<Book, String> titleCol = new TableColumn<>("Title");
        titleCol.setCellValueFactory(new PropertyValueFactory<>("title"));
        titleCol.setPrefWidth(200);

        TableColumn<Book, String> authorCol = new TableColumn<>("Author");
        authorCol.setCellValueFactory(new PropertyValueFactory<>("author"));
        authorCol.setPrefWidth(150);

        TableColumn<Book, String> genreCol = new TableColumn<>("Genre");
        genreCol.setCellValueFactory(new PropertyValueFactory<>("genre"));
        genreCol.setPrefWidth(100);

        table.getColumns().addAll(titleCol, authorCol, genreCol);

        btnLogout = new Button("Logout");
        btnLogout.setStyle("-fx-background-color: #8B0000; -fx-text-fill: white; -fx-cursor: hand;");

        VBox root = new VBox(15, title, table, btnLogout);
        root.setPadding(new Insets(20));

        scene = new Scene(root, 600, 500);
    }

    public Scene getScene() { return scene; }
    public Button getBtnLogout() { return btnLogout; }
    public TableView<Book> getTable() { return table; }
}