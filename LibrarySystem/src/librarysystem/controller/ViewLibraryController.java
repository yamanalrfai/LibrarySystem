package librarysystem.controller;

import java.util.ArrayList;
import java.util.List;
import javafx.scene.control.Alert;
import javafx.stage.Stage;
import librarysystem.dao.BookDAO;
import librarysystem.dao.OrderDAO;
import librarysystem.model.Book;
import librarysystem.session.UserSession;
import librarysystem.view.ViewLibraryView;

public class ViewLibraryController {
    private ViewLibraryView view;
    private BookDAO bookDAO;
    private OrderDAO orderDAO;
    private Stage stage;
    private List<Book> cartItems;

    public ViewLibraryController(Stage stage) {
        this.stage = stage;
        this.view = new ViewLibraryView();
        this.bookDAO = new BookDAO();
        this.orderDAO = new OrderDAO();
        this.cartItems = new ArrayList<>();

        if (UserSession.isLoggedIn()) {
            view.setUserLabel("Signed in as " + UserSession.getUsername());
        } else {
            view.setUserLabel("Signed in user");
        }

        loadBooks();
        refreshCart();

        view.getBtnLogout().setOnAction(e -> {
            cartItems.clear();
            UserSession.clear();
            MainController mainController = new MainController(stage);
            stage.setScene(mainController.getView().getScene());
        });

        view.getBtnCheckout().setOnAction(e -> checkoutCart());
        view.getBtnViewOrders().setOnAction(e -> {
            OrderHistoryController orderHistoryController = new OrderHistoryController(stage);
            stage.setScene(orderHistoryController.getView().getScene());
        });
    }

    private void loadBooks() {
        List<Book> books = bookDAO.getAllBooks();
        view.renderBooks(books, this::addToCart);
    }

    private void addToCart(Book book) {
        for (Book cartBook : cartItems) {
            if (cartBook.getId() == book.getId()) {
                view.showAlert("This book is already in your cart.", Alert.AlertType.WARNING, "Cart");
                return;
            }
        }

        cartItems.add(book);
        refreshCart();
    }

    private void removeFromCart(Book book) {
        cartItems.removeIf(cartBook -> cartBook.getId() == book.getId());
        refreshCart();
    }

    private void refreshCart() {
        view.renderCart(cartItems, this::removeFromCart);
    }

    private void checkoutCart() {
        if (!UserSession.isLoggedIn()) {
            view.showAlert("Please log in again before checkout.", Alert.AlertType.WARNING, "Checkout");
            return;
        }

        if (cartItems.isEmpty()) {
            view.showAlert("Your cart is empty.", Alert.AlertType.WARNING, "Checkout");
            return;
        }

        boolean success = orderDAO.checkoutBooks(UserSession.getUserId(), new ArrayList<>(cartItems));
        if (success) {
            cartItems.clear();
            refreshCart();
            loadBooks();
            view.showAlert("Order placed successfully. The ordered books were removed from the library.", Alert.AlertType.INFORMATION, "Checkout");
        } else {
            view.showAlert("Checkout failed. Please try again.", Alert.AlertType.ERROR, "Checkout");
        }
    }

    public ViewLibraryView getView() { return view; }
}