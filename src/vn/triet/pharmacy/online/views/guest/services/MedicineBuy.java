package vn.triet.pharmacy.online.views.guest.services;

import vn.triet.pharmacy.online.models.*;
import vn.triet.pharmacy.online.services.*;
import vn.triet.pharmacy.online.utils.CSVUtils;
import vn.triet.pharmacy.online.utils.ValidateUtils;
import vn.triet.pharmacy.online.views.GuestView;
import vn.triet.pharmacy.online.views.LoginView;
import vn.triet.pharmacy.online.views.Menu;
import vn.triet.pharmacy.online.views.admin.management.MedicineManagement;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

public class MedicineBuy {
    private static IMedicineService medicineService = new MedicineService();
    private static IUserService userService = new UserService();
    private static IOrderService orderService = new OrderService();
    private static IOderItemService oderItemService = new OrderItemService();

    private static final Scanner input = new Scanner(System.in);

    public static void setInformation() {
        do {
            Order newOrder = new Order();
            System.out.println("\n(I) Before buying, we need some required information from you!\n");
            System.out.println("     * Enter '1' to use your default information.");
            System.out.println("     * Enter '2' to use another information.");
            System.out.println("     * Enter '0' to exit.");
            try {
                int number = Menu.chooseActionByNumber();
                if (number == 1) {
                    setNewOrderWithDefaultInfo(newOrder);
                }
                if (number == 2) {
                    setNewOrderWithOtherInfo(newOrder);
                }
                if (number == 0) {
                    GuestView.chooseServicesForGuest();
                    break;
                }
            } catch (Exception ex) {
                ex.printStackTrace();
                Menu.alert();
            }
            List<Drug> drugs = medicineService.getDrugs();
            ArrayList<OrderItem> orderItemList = showHowToGetDrug(drugs);
            orderItemList.sort(Comparator.comparing(OrderItem::getDrugName).thenComparingDouble(OrderItem::getDrugContent));
            double totalPrice = 0;
            for (OrderItem orderItem : orderItemList) {
                orderItem.setOrderID(newOrder.getId());
                totalPrice += orderItem.getPricePerPill() * orderItem.getQuantity();
            }
            newOrder.setTotalPrice(totalPrice);
            showBill(newOrder, orderItemList);

            if (confirmBuying()) {
                System.out.println("\n----- Successful Payment. Thank you! -----\n");
                orderService.add(newOrder);
                oderItemService.addMoreOrderItems(orderItemList);
                CSVUtils.writeData("Data/drugs.csv",drugs);
            }
        } while (true);
//        System.out.println(newOrder);
    }

    public static boolean confirmBuying() {
        System.out.println("\n---> Confirm that you want to pay the bill!");
        System.out.println("(Enter 'y' or 'n')");
        do {
            String letter = Menu.chooseActionByLetter();
            if(letter.equals("y")) return true;
            if(letter.equals("n")) return false;
            Menu.alert();
        } while (true);
    }

    private static void showBill(Order newOrder, List<OrderItem> orderItemList) {
        System.out.println("\nMEDICAL BILL --------------------------------------------------------------------------");
        System.out.printf("\n%-20s %-20s %-15s %-20s", "Drug Name", "Drug Content", "Quantity", "Price per Pill (VND)\n");
        for (OrderItem orderItem : orderItemList) {
            System.out.printf("%-20s %-20s %-15s %-20s\n", orderItem.getDrugName(), orderItem.getDrugContent(), orderItem.getQuantity(), orderItem.getPricePerPill());
        }
        System.out.println("\nCustomer information:");
        System.out.println("     * Name: " + newOrder.getName());
        System.out.println("     * Phone Number: " + newOrder.getPhoneNumber());
        System.out.println("     * Address: " + newOrder.getAddress());
        System.out.println("\n---> TOTAL PRICE (VND): " + newOrder.getTotalPrice());
        System.out.println("---------------------------------------------------------------------------------------\n");

    }

    public static void setNewOrderWithDefaultInfo(Order newOder) {
        User currentUser = userService.getUserById(LoginView.getUserID());
        newOder.setId(System.currentTimeMillis());
        newOder.setUserId(currentUser.getId());
        newOder.setName(currentUser.getFullName());
        newOder.setPhoneNumber(currentUser.getPhoneNumber());
        newOder.setAddress(currentUser.getAddress());
        newOder.setCreationTime(System.currentTimeMillis());

    }

    public static void setNewOrderWithOtherInfo(Order newOder) {
        String name = enterFullName();
        String phone = enterPhoneNumber();
        String address = enterAddress();
        newOder.setId(System.currentTimeMillis());
        newOder.setUserId(LoginView.getUserID());
        newOder.setName(name);
        newOder.setPhoneNumber(phone);
        newOder.setAddress(address);
        newOder.setCreationTime(System.currentTimeMillis());
    }

    private static String enterAddress() {
        System.out.println("3. Enter Address (Example: 4/18 An Duong Vuong, Hue).");
        System.out.print("==> ");
        String address = input.nextLine().trim();
        while (address.equals("")) {
            if (cancelEntering(address)) return null;
            System.out.println("Address is required, please try again!\n");
            System.out.print("==> ");
        }
        return address;
    }

    private static String enterPhoneNumber() {
        String phone;
        System.out.println("2. Enter PhoneNumber.");
        System.out.println("(Note: First digit must be '0', second digit is form '1' to '9' and length is from 10 to 11 digits)");
        System.out.print("==> ");
        while (!ValidateUtils.isPhoneValid(phone = input.nextLine().trim())) {
            if (cancelEntering(phone)) return null;
            System.out.println("Invalid phone number format, please try again!\n");
            System.out.println("(Note: Your phone number must start with '0' and has from 10 to 11 digits)");
            System.out.print("==> ");
        }
        return phone;
    }

    private static String enterFullName() {
        String fullName;
        System.out.println("1. Enter Full Name (Example: Will Smith).");
        System.out.print("==> ");
        while (!ValidateUtils.isNameValid(fullName = input.nextLine().trim())) {
            if (cancelEntering(fullName)) return null;
            System.out.println("Invalid name format, please try again!\n");
            System.out.println("1. Enter Full Name (Example: Will Smith).");
            System.out.print("==> ");
        }
        return fullName;
    }

    private static boolean cancelEntering(String string) {
        if ("exit-05".equalsIgnoreCase(string)) {
            System.out.println("\n-----> Your operation has been canceled!");
            return true;
        }
        return false;
    }

    public static ArrayList<OrderItem> showHowToGetDrug(List<Drug> drugs) {
        ArrayList<OrderItem> orderItemList = new ArrayList<>();
        do {
            System.out.println("\n(II) How do you want to get drug?\n");
            System.out.println("1. By drug ID.");
            System.out.println("2. By search drug name.");
            System.out.println("0. Exit.");
            try {
                int number = Menu.chooseActionByNumber();
                if (number == 1) {
                    orderItemList = getDrugsBought(drugs);
                    break;
                }
                if (number == 2) {

                    break;
                }
            } catch (Exception ex) {
                Menu.alert();
            }
        } while (true);
        return orderItemList;
    }

    private static void showDrugsList(List<Drug> drugs) {
        System.out.println("\nDRUGS LIST -------------------------------------------------------------------------------------------------------------");
        System.out.printf("%-12s %-25s %-23s %-20s %-20s %-20s\n",
                "ID", "Drug Name", "Drug Content (mg)", "Quantity (pill)", "Price per Pill", "Expiration Date");
        System.out.println("------------------------------------------------------------------------------------------------------------------------");
        MedicineManagement.showAllDrugs(drugs);
        System.out.println("------------------------------------------------------------------------------------------------------------------------");
    }

    private static ArrayList<OrderItem> getDrugsBought(List<Drug> drugs) {
//        List<Drug> drugs = medicineService.getDrugs();
        ArrayList<OrderItem> drugsBought = new ArrayList<>();
        Drug availableDrug;
        boolean stopBuying;
        do {
            showDrugsList(drugs);
            do {
                try {
                    System.out.print("\nEnter drug ID you want to buy: ");
                    int drugBoughtID = Integer.parseInt(input.nextLine());
                    availableDrug = medicineService.getDrugById(drugBoughtID);
                    if (availableDrug == null) {
                        System.out.println("Wrong ID, please try again!\n");
                        continue;
                    }
                    System.out.printf("Drug Name: %s %.1f mg\n", availableDrug.getDrugName(), availableDrug.getDrugContent());
                    break;
                } catch (Exception ex) {
                    Menu.alert();
                }
            } while (true);

            int quantityBuy;
            do {
                try {
                    System.out.print("\nEnter the number of pills you want to buy: ");
                    quantityBuy = Integer.parseInt(input.nextLine());
                    if (quantityBuy <= 0) {
                        System.out.println("You mustn't enter negative value or '0', please try again!\n");
                        continue;
                    }
                    int oldQuantity = drugs.get(drugs.indexOf(availableDrug)).getQuantity();
                    if (quantityBuy > oldQuantity) {
                        System.out.println("\n---> The quantity in EXCESS! Please enter a number less than '" + oldQuantity + "'.\n");
                        continue;
                    }
                    int quantityLeft = oldQuantity - quantityBuy;
                    drugs.get(drugs.indexOf(availableDrug)).setQuantity(quantityLeft);
                    break;
                } catch (Exception ex) {
                    Menu.alert();
                }
            } while (true);

            drugsBought.add(getNewOrderDrug(availableDrug, quantityBuy));

            do {
                System.out.println("\nDo you want to buy another drug?");
                System.out.println("(Enter 'y' to continue, enter 'n' to stop)");
                String letter = Menu.chooseActionByLetter();
                if (letter.equals("y")) {
                    stopBuying = false;
                    break;
                }
                if (letter.equals("n")) {
                    stopBuying = true;
                    break;
                }
                Menu.alert();
            } while (true);

        } while (!stopBuying);

        return drugsBought;
    }

    public static OrderItem getNewOrderDrug(Drug availableDrug, int quantityBuy) {
        OrderItem newOrderDrug = new OrderItem();
        newOrderDrug.setId(System.currentTimeMillis());
        newOrderDrug.setDrugID(availableDrug.getId());
        newOrderDrug.setDrugName(availableDrug.getDrugName());
        newOrderDrug.setDrugContent(availableDrug.getDrugContent());
        newOrderDrug.setPricePerPill(availableDrug.getPricePerPill());
        newOrderDrug.setQuantity(quantityBuy);
        newOrderDrug.setCreationTime(System.currentTimeMillis());
        return newOrderDrug;
    }
}
