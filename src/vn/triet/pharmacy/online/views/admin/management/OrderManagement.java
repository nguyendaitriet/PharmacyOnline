package vn.triet.pharmacy.online.views.admin.management;

import vn.triet.pharmacy.online.models.Order;
import vn.triet.pharmacy.online.models.Role;
import vn.triet.pharmacy.online.services.IOrderService;
import vn.triet.pharmacy.online.services.OrderService;
import vn.triet.pharmacy.online.views.AdminView;
import vn.triet.pharmacy.online.views.GuestView;
import vn.triet.pharmacy.online.views.LoginView;
import vn.triet.pharmacy.online.views.Menu;
import vn.triet.pharmacy.online.views.guest.services.PurchaseHistory;

import java.util.List;

public class OrderManagement {
    private static IOrderService orderService = new OrderService();

    public static void chooseAction() {
        List<Order> userOrdersList = orderService.getOrders();
//        List<Order> userOrdersList = orderService.getUserOrdersList(346749);
        do {
            PurchaseHistory.showOrderList(Role.ADMIN, userOrdersList);
            showActionForm();
            try {
                int number = Menu.chooseActionByNumber();
                if (number == 1) {
//                    searchOrder(userOrdersList);
                }
                if (number == 2) {
//                    showOrderDetails(userOrdersList);
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

    public static void main(String[] args) {
        chooseAction();
    }
    private static void showActionForm() {
    }

}
