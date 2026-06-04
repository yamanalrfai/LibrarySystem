package librarysystem.controller;

import java.util.List;
import javafx.stage.Stage;
import librarysystem.dao.OrderDAO;
import librarysystem.model.OrderRecord;
import librarysystem.session.UserSession;
import librarysystem.view.OrderHistoryView;

public class OrderHistoryController {
    private OrderHistoryView view;
    private OrderDAO orderDAO;
    private Stage stage;

    public OrderHistoryController(Stage stage) {
        this.stage = stage;
        this.view = new OrderHistoryView();
        this.orderDAO = new OrderDAO();

        loadOrders();

        view.getBtnBack().setOnAction(e -> {
            ViewLibraryController libraryController = new ViewLibraryController(stage);
            stage.setScene(libraryController.getView().getScene());
        });

        view.getBtnLogout().setOnAction(e -> {
            UserSession.clear();
            MainController mainController = new MainController(stage);
            stage.setScene(mainController.getView().getScene());
        });
    }

    private void loadOrders() {
        if (!UserSession.isLoggedIn()) {
            view.renderOrders(List.of());
            return;
        }

        List<OrderRecord> orders = orderDAO.getOrdersForUser(UserSession.getUserId());
        view.renderOrders(orders);
    }

    public OrderHistoryView getView() { return view; }
}