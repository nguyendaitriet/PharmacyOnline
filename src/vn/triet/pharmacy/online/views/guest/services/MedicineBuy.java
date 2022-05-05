package vn.triet.pharmacy.online.views.guest.services;

import vn.triet.pharmacy.online.models.*;
import vn.triet.pharmacy.online.services.IMedicineService;
import vn.triet.pharmacy.online.services.IUserService;
import vn.triet.pharmacy.online.services.MedicineService;
import vn.triet.pharmacy.online.services.UserService;
import vn.triet.pharmacy.online.utils.ValidateUtils;
import vn.triet.pharmacy.online.views.GuestView;
import vn.triet.pharmacy.online.views.LoginView;
import vn.triet.pharmacy.online.views.Menu;
import vn.triet.pharmacy.online.views.admin.management.MedicineManagement;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MedicineBuy {
    private static IMedicineService medicineService = new MedicineService();
    private static IUserService userService = new UserService();

    private static final Scanner input = new Scanner(System.in);

    public static void setInformation() {
        showDrugBuy();
//        Order2 newOrder = new Order2();
//        System.out.println("\n(I) Before buying, we need some required information from you!\n");
//        do {
//            System.out.println("     * Enter '1' to use your default information.");
//            System.out.println("     * Enter '2' to use another information.");
//            System.out.println("     * Enter '0' to return.");
//            try {
//                int number = Menu.chooseActionByNumber();
//                if (number == 1) {
//                    setNewOrderWithDefaultInfo(newOrder);
//                    break;
//                }
//                if (number == 2) {
//                    setNewOrderWithOtherInfo(newOrder);
//                    break;
//                }
//                if (number == 0) {
//                    GuestView.chooseServicesForGuest();
//                    break;
//                }
//            } catch (Exception ex) {
//                Menu.alert();
//            }
//        } while (true);
//        System.out.println(newOrder);
    }

    public static void setNewOrderWithDefaultInfo(Order2 newOder) {
        User currentUser = userService.getUserById(LoginView.getUserID());
        newOder.setId(System.currentTimeMillis());
        newOder.setUserId(currentUser.getId());
        newOder.setName(currentUser.getFullName());
        newOder.setPhoneNumber(currentUser.getPhoneNumber());
        newOder.setAddress(currentUser.getAddress());
        newOder.setCreationTime(System.currentTimeMillis());
    }


    public static void setNewOrderWithOtherInfo(Order2 newOder) {
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

    public static void showDrugBuy() {
        List<Drug> drugs = medicineService.getDrugs();
        List<OrderItem2> orderItemList = new ArrayList<>();
        do {
            System.out.println("\n(II) How do you want to get drug?\n");
            System.out.println("1. By drug ID.");
            System.out.println("2. By search drug name.");
            System.out.println("0. Exit.");
            try {
                int number = Menu.chooseActionByNumber();
                if (number == 1) {
                    showDrugsList(drugs);
                    orderItemList = getDrugsBought(drugs);
                    break;
                }
                if (number == 2) {
                    break;
                }
                if (number == 0) {
                    GuestView.chooseServicesForGuest();
                    break;
                }
            } catch (Exception ex) {
                Menu.alert();
            }
        } while (true);
    }

    private static void showDrugsList(List<Drug> drugs) {
        System.out.println("\nDRUGS LIST -------------------------------------------------------------------------------------------------------------");
        System.out.printf("%-12s %-25s %-23s %-20s %-20s %-20s\n",
                "ID", "Drug Name", "Drug Content (mg)", "Quantity (pill)", "Price per Pill", "Expiration Date");
        System.out.println("------------------------------------------------------------------------------------------------------------------------");
        MedicineManagement.showAllDrugs(drugs);
        System.out.println("------------------------------------------------------------------------------------------------------------------------");
    }

    private static ArrayList<OrderItem2> getDrugsBought(List<Drug> drugs) {
        ArrayList<OrderItem2> drugsBought = new ArrayList<>();
        Drug availableDrug;
        boolean stopBuying = true;
        do {
            do {
                try {
                    System.out.print("\nEnter drug ID you want to buy: ");
                    int drugBoughtID = Integer.parseInt(input.nextLine());
                    availableDrug = medicineService.getDrugById(drugBoughtID);
                    if (availableDrug == null) {
                        System.out.println("Wrong ID, please try again!\n");
                        continue;
                    }
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
                    if (quantityBuy > availableDrug.getQuantity()) {
                        System.out.println("\n---> The quantity in EXCESS! Please enter a number less than '" + availableDrug.getQuantity() + "'.\n");
                        continue;
                    }
                    break;
                } catch (Exception ex) {
                    Menu.alert();
                }
            } while (true);

            drugsBought.add(getNewOrderDrug(availableDrug, quantityBuy));

            System.out.println("\nDo you want to buy another drug?");
            System.out.println("(Enter 'y' to continue, enter 'n' to stop)");
            String letter = Menu.chooseActionByLetter();
            switch (letter.charAt(0)) {
                case 'y' -> stopBuying = false;
                case 'n' -> stopBuying = true;
                default -> Menu.alert();
            }
        } while (!stopBuying);
//        System.out.println(drugsBought);
        return drugsBought;
    }

    public static OrderItem2 getNewOrderDrug(Drug availableDrug, int quantityBuy) {
        OrderItem2 newOrderDrug = new OrderItem2();
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
