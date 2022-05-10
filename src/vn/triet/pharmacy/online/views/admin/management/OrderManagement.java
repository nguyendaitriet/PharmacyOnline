package vn.triet.pharmacy.online.views.admin.management;

import vn.triet.pharmacy.online.models.Order;
import vn.triet.pharmacy.online.models.Role;
import vn.triet.pharmacy.online.services.IOrderService;
import vn.triet.pharmacy.online.services.OrderService;
import vn.triet.pharmacy.online.views.AdminView;
import vn.triet.pharmacy.online.views.Menu;
import vn.triet.pharmacy.online.views.customer.services.PurchaseHistory;
import java.util.List;

public class OrderManagement {
    private static IOrderService orderService = new OrderService();

    private static void showActionForm() {
        System.out.println("\n1. Sort by creation date.");
        System.out.println("2. Sort by total price.");
        System.out.println("3. Search in order list.");
        System.out.println("4. Show details by order ID.");
        System.out.println("0. Exit.");
    }

    public static void chooseAction() {
        List<Order> userOrdersList = orderService.getOrders();
        do {
            PurchaseHistory.showOrderList(Role.ADMIN, userOrdersList);
            showActionForm();
            try {
                int number = Menu.chooseActionByNumber();
                if (number == 1) {
                    sortByCreationDateACSE(userOrdersList);
                    continue;
                }
                if (number == 2) {
                    sortByTotalPriceACSE(userOrdersList);
                    continue;
                }
                if (number == 3) {
                    PurchaseHistory.searchOrder(Role.ADMIN, userOrdersList);
                    continue;
                }
                if (number == 4) {
                    PurchaseHistory.showOrderDetails(userOrdersList);
                    continue;
                }
                if (number == 0) {
                    AdminView.chooseAdminAction();
                    break;
                }
            } catch (Exception ex) {
                Menu.alert();
            }
        } while (true);
    }

    private static void sortByCreationDateACSE(List<Order> userOrdersList) {
        userOrdersList.sort((e1, e2) -> Long.compare(e1.getCreationTime() - e2.getCreationTime(), 0));
        showResultAndOperation(userOrdersList);
    }

    private static void sortByTotalPriceACSE(List<Order> userOrdersList) {
        userOrdersList.sort((e1, e2) -> Double.compare(e1.getTotalPrice() - e2.getTotalPrice(), 0));
        showResultAndOperation(userOrdersList);
    }

    private static void showResultAndOperation(List<Order> userOrdersList) {
        PurchaseHistory.showOrderList(Role.ADMIN, userOrdersList);
        PurchaseHistory.showOrderDetails(userOrdersList);
    }

}
