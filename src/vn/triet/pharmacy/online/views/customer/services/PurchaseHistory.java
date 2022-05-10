package vn.triet.pharmacy.online.views.customer.services;

import vn.triet.pharmacy.online.models.Order;
import vn.triet.pharmacy.online.models.Role;
import vn.triet.pharmacy.online.services.IOderItemService;
import vn.triet.pharmacy.online.services.IOrderService;
import vn.triet.pharmacy.online.services.OrderItemService;
import vn.triet.pharmacy.online.services.OrderService;
import vn.triet.pharmacy.online.utils.ValidateUtils;
import vn.triet.pharmacy.online.views.CustomerView;
import vn.triet.pharmacy.online.views.LoginView;
import vn.triet.pharmacy.online.views.Menu;
import java.util.List;
import java.util.Scanner;

public class PurchaseHistory {
    private static IOrderService orderService = new OrderService();
    private static IOderItemService orderItemService = new OrderItemService();
    private static final Scanner input = new Scanner(System.in);

    public static void showOrderList(Role role, List<Order> orderList) {
        System.out.println("\nOrders List -------------------------------------------------------------------------------------------------------------------------");
        System.out.printf("%-17s %-17s %s    %-20s %-18s %-25s %-15s\n", "Order ID", "Creation Date", role == Role.ADMIN ? "Account ID" : "",
                "Customer Name", "Phone Number", "Address", "Total Price (VND)");
        System.out.println("-------------------------------------------------------------------------------------------------------------------------------------");
        for (Order order : orderList) {
            String creationDate = ValidateUtils.convertMilliToDate(order.getCreationTime());
            System.out.printf("%-17s %-17s %s    %-20s %-18s %-25s %-15s\n", order.getId(), creationDate, role == Role.ADMIN ? order.getUserId() + "    " : "",
                    order.getName(), order.getPhoneNumber(), order.getAddress(), ValidateUtils.priceWithDecimal(order.getTotalPrice()));
        }
        System.out.println("-------------------------------------------------------------------------------------------------------------------------------------");
    }

    private static void showActionForm() {
        System.out.println("\n1. Search in order list.");
        System.out.println("2. Show details by order ID.");
        System.out.println("0. Exit.");
    }

    public static void main(String[] args) {
        chooseAction();
    }

    public static void chooseAction() {
        List<Order> userOrdersList = orderService.getUserOrdersList(LoginView.getUserID());
        do {
            showOrderList(Role.USER, userOrdersList);
            showActionForm();
            try {
                int number = Menu.chooseActionByNumber();
                if (number == 1) {
                    searchOrder(Role.USER, userOrdersList);
                    continue;
                }
                if (number == 2) {
                    showOrderDetails(userOrdersList);
                    continue;
                }

                if (number == 0) {
                    CustomerView.chooseServicesForGuest();
                    break;
                }
            } catch (Exception ex) {
                Menu.alert();
            }
        } while (true);
    }

    public static void showOrderDetails(List<Order> userOrdersList) {
        do {
            try {
                System.out.print("\nEnter order ID you want to check (Enter '0' to exit).\n");
                System.out.print("---> ");
                long orderID = Long.parseLong(input.nextLine());
                if (orderID == 0) return;
                Order order = orderService.getOrderById(orderID, userOrdersList);
                if (order == null) {
                    System.out.println("Wrong order ID, please try again!");
                    continue;
                }
                MedicineBuy.showBill(order, orderItemService.getUserOrderItemList(orderID));
            } catch (Exception ex) {
                Menu.alert();
            }
        } while (true);
    }

    public static void searchOrder(Role role,List<Order> userOrdersList) {
        do {
            System.out.print("\nEnter anything you want to search (Enter '0' to exit).\n");
            System.out.print("---> ");
            String searchContent = input.nextLine().trim().toLowerCase();
            if (searchContent.equals("0")) return;
            List<Order> searchList = orderService.getSearchOrderList(searchContent, userOrdersList);
            if (searchList.size() == 0) {
                System.out.println("Sorry, can't find any order with your given information!");
                continue;
            }
            showOrderList(role, searchList);
            showOrderDetails(searchList);
        } while (true);
    }
}
