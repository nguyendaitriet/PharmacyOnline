package vn.triet.pharmacy.online.views.customer.services;

import vn.triet.pharmacy.online.models.*;
import vn.triet.pharmacy.online.services.*;
import vn.triet.pharmacy.online.utils.CSVUtils;
import vn.triet.pharmacy.online.utils.ValidateUtils;
import vn.triet.pharmacy.online.views.CustomerView;
import vn.triet.pharmacy.online.views.LoginView;
import vn.triet.pharmacy.online.views.Menu;
import vn.triet.pharmacy.online.views.admin.management.MedicineManagement;
import java.util.*;

public class MedicineBuy {
    private static IMedicineService medicineService = new MedicineService();
    private static IUserService userService = new UserService();
    private static IOrderService orderService = new OrderService();
    private static IOderItemService orderItemService = new OrderItemService();
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
                    CustomerView.chooseServicesForGuest();
                    break;
                }
            } catch (Exception ex) {
                ex.printStackTrace();
                Menu.alert();
            }

            if (newOrder.getName() == null || newOrder.getPhoneNumber() == null || newOrder.getAddress() == null) {
                System.out.println("\nNo information added, please try again!\n");
                continue;
            }

            List<Drug> drugs = medicineService.getDrugs();
            ArrayList<OrderItem> orderItemList = showHowToGetDrug(newOrder, drugs);
            if (orderItemList.size() == 0) {
                System.out.println("\n----- There is no drugs in your bill. You can try again!");
                continue;
            }

            showBill(newOrder, orderItemList);

            if (confirmBuying()) {
                System.out.println("\n----- Successful Payment. Thank you! -----\n");
                System.out.println("At the next step, enter '0' to exit or others to buy new drugs.");
                orderService.add(newOrder);
                orderItemService.addMoreOrderItems(orderItemList);
                CSVUtils.writeData("Data/drugs.csv", drugs);
            }
        } while (true);
    }

    public static void modifyOrderItemList(ArrayList<OrderItem> orderItemList) {
        orderItemList.sort(Comparator.comparing(OrderItem::getDrugName).thenComparingDouble(OrderItem::getDrugContent));
        for (int i = 0; i < orderItemList.size() - 1; ) {
            OrderItem orderItem1 = orderItemList.get(i);
            OrderItem orderItem2 = orderItemList.get(i + 1);
            if (orderItem1.equals(orderItem2)) {
                orderItem1.setQuantity(orderItem1.getQuantity() + orderItem2.getQuantity());
                orderItemList.remove(i + 1);
                continue;
            }
            i++;
        }
    }

    public static boolean confirmBuying() {
        do {
            System.out.println("\n---> Please confirm that you want to pay the bill!");
            System.out.println("(Enter 'y' to agree or 'n' to exit)");
            String letter = Menu.chooseActionByLetter();
            if (letter.equals("y")) return true;
            if (letter.equals("n")) return false;
            Menu.alert();
        } while (true);
    }

    public static void showBill(Order newOrder, List<OrderItem> orderItemList) {
        System.out.println("\nMEDICAL BILL --------------------------------------------------------------------------");
        System.out.printf("\n%-20s %-20s %-15s %-13s %-15s\n\n", "Drug Name", "Drug Content (mg)", "Price (VND)", "Quantity", "Total (VND)");
        for (OrderItem orderItem : orderItemList) {
            System.out.printf("%-20s %-20s %-15s %-13s %-15s\n", orderItem.getDrugName(), orderItem.getDrugContent(), ValidateUtils.priceWithDecimal(orderItem.getPricePerPill()),
                    orderItem.getQuantity(), ValidateUtils.priceWithDecimal(orderItem.getPricePerPill() * orderItem.getQuantity()));
        }
        System.out.printf("\n%-52s %s %s\n", "", "TOTAL PRICE (VND):", ValidateUtils.priceWithDecimal(newOrder.getTotalPrice()));
        System.out.println("Customer information:");
        System.out.println("     * Name: " + newOrder.getName());
        System.out.println("     * Phone Number: " + newOrder.getPhoneNumber());
        System.out.println("     * Address: " + newOrder.getAddress());
        System.out.println("Creation Date: " + ValidateUtils.convertMilliToDate(newOrder.getCreationTime()));
        System.out.println("\n---------------------------------------------------------------------------------------\n");
    }

    public static void showDrugFromGetting(Order newOrder, List<OrderItem> orderItemList) {
        System.out.printf("\n%-20s %-20s %-15s %-13s %-15s\n\n", "Drug Name", "Drug Content (mg)", "Price (VND)", "Quantity", "Total (VND)");
        double totalPrice = 0;
        for (OrderItem orderItem : orderItemList) {
            orderItem.setOrderID(newOrder.getId());
            orderItem.setCreationTime(newOrder.getCreationTime());
            double total = orderItem.getQuantity() * orderItem.getPricePerPill();
            System.out.printf("%-20s %-20s %-15s %-13s %-15s\n", orderItem.getDrugName(), orderItem.getDrugContent(),
                    ValidateUtils.priceWithDecimal(orderItem.getPricePerPill()), orderItem.getQuantity(), ValidateUtils.priceWithDecimal(total));
            totalPrice += total;
        }
        newOrder.setTotalPrice(totalPrice);
        System.out.printf("\n%-52s %s %s\n", "", "TOTAL PRICE (VND):", ValidateUtils.priceWithDecimal(totalPrice));
    }

    public static void showDrugsGot(Order newOrder, List<OrderItem> orderItemList) {
        System.out.print("\n---------------------------------------------------------------------------------------");
        showDrugFromGetting(newOrder, orderItemList);
        System.out.println("---------------------------------------------------------------------------------------\n");
    }

    public static void setNewOrderWithDefaultInfo(Order newOder) {
        System.out.println("\n----- Your information has been added -----");
        User currentUser = userService.getUserById(LoginView.getUserID());
        newOder.setId(System.currentTimeMillis());
        newOder.setUserId(currentUser.getId());
        newOder.setName(currentUser.getFullName());
        newOder.setPhoneNumber(currentUser.getPhoneNumber());
        newOder.setAddress(currentUser.getAddress());
        newOder.setCreationTime(System.currentTimeMillis());
    }

    public static void setNewOrderWithOtherInfo(Order newOder) {
        System.out.println("\n----- Please fill in all information below -----");
        System.out.println("\n(Enter 'ex5' at any step to cancel)");
        String name = enterFullName();
        if (name == null) return;
        String phone = enterPhoneNumber();
        if (phone == null) return;
        String address = enterAddress();
        if (address == null) return;

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
            System.out.println("2. Enter PhoneNumber.");
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
        if (Exit.E5.equalsIgnoreCase(string)) {
            System.out.println("\n-----> Your operation has been canceled!");
            return true;
        }
        return false;
    }

    public static ArrayList<OrderItem> showHowToGetDrug(Order newOrder, List<Drug> drugs) {
        ArrayList<OrderItem> orderItemList = new ArrayList<>();
        do {
            System.out.println("\n(II) How do you want to get drug?\n");
            System.out.println("1. By drug ID.");
            System.out.println("2. By search drug name.");
            System.out.println("0. Exit and show the bill.");
            try {
                int number = Menu.chooseActionByNumber();
                if (number == 1) {
                    orderItemList = getDrugsBoughtByID(newOrder, drugs);
                }
                if (number == 2) {
                    orderItemList = searchDrugByName(newOrder, drugs);
                }
                if (number == 0) {
                    break;
                }
            } catch (Exception ex) {
                Menu.alert();
            }

            if (orderItemList.size() > 0) {
                System.out.println("\n--- Added all drugs to your bill successfully ---");
                System.out.println("*** At the next step, enter '0' to see all drugs you have got in your bill.");
                System.out.println("*** Enter '1' or '2' to reject all drugs you have got and restart getting drug.");
            }

        } while (true);
        return orderItemList;
    }

    public static ArrayList<OrderItem> searchDrugByName(Order newOrder, List<Drug> drugs) {
        ArrayList<OrderItem> newOrderItemList = new ArrayList<>();
        do {
            System.out.println("\nEnter name of drug you want to buy (Enter '0' to stop getting drug). ");
            System.out.print("---> ");
            String drugNameSearch = input.nextLine().trim().toLowerCase();
            if (drugNameSearch.equals("0")) return newOrderItemList;
            List<Drug> drugListSearch = new ArrayList<>();
            for (Drug drug : drugs) {
                if (drug.getDrugName().toLowerCase().contains(drugNameSearch)) {
                    drugListSearch.add(drug);
                }
            }
            if (drugListSearch.size() > 0) {
                newOrderItemList.addAll(getDrugsBoughtByID(newOrder, drugListSearch));
                changeQuantityAfterGetting(drugs, drugListSearch);
                continue;
            }
            System.out.printf("\nSorry, '%s' can't be found. Please try again!\n", drugNameSearch);
        } while (true);
    }

    public static void changeQuantityAfterGetting(List<Drug> drugs, List<Drug> drugListSearch) {
        for (Drug drugSearch : drugListSearch) {
            for (Drug drug : drugs) {
                if (drugSearch.getId() == drug.getId()) {
                    drug.setQuantity(drugSearch.getQuantity());
                    break;
                }
            }
        }
    }

    private static ArrayList<OrderItem> getDrugsBoughtByID(Order newOrder, List<Drug> drugs) {
        ArrayList<OrderItem> drugsBought = new ArrayList<>();
        boolean stopBuying;
        do {
            MedicineManagement.showAllDrugs(drugs);

            Drug availableDrug = getAvailableDrug();

            if (availableDrug == null) return drugsBought;

            int quantityBuy = getQuantityBuy(drugs, availableDrug);

            drugsBought.add(getNewOrderDrug(availableDrug, quantityBuy));
            System.out.printf("\n---> '%s %.1f mg' - %d has been added to your bill.\n", availableDrug.getDrugName(), availableDrug.getDrugContent(), quantityBuy);
            modifyOrderItemList(drugsBought);

            showDrugsGot(newOrder, drugsBought);
            stopBuying = confirmContinuing();
        } while (!stopBuying);
        return drugsBought;
    }

    private static Drug getAvailableDrug() {
        Drug availableDrug;
        do {
            try {
                System.out.println("\nEnter drug ID you want to buy (Enter '0' to stop getting drug).");
                System.out.print("---> ");
                int drugBoughtID = Integer.parseInt(input.nextLine());
                if (drugBoughtID == 0) return null;
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
        return availableDrug;
    }

    private static int getQuantityBuy(List<Drug> drugs, Drug availableDrug) {
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
        return quantityBuy;
    }

    private static boolean confirmContinuing() {
        do {
            System.out.println("\nDo you want to get drug again?");
            System.out.println("(Enter 'y' to continue, enter 'n' to stop)");
            String letter = Menu.chooseActionByLetter();
            if (letter.equals("y")) return false;
            if (letter.equals("n")) return true;
            Menu.alert();
        } while (true);
    }

    public static OrderItem getNewOrderDrug(Drug availableDrug, int quantityBuy) {
        OrderItem newOrderDrug = new OrderItem();
        newOrderDrug.setId(System.currentTimeMillis());
        newOrderDrug.setDrugID(availableDrug.getId());
        newOrderDrug.setDrugName(availableDrug.getDrugName());
        newOrderDrug.setDrugContent(availableDrug.getDrugContent());
        newOrderDrug.setPricePerPill(availableDrug.getPricePerPill());
        newOrderDrug.setQuantity(quantityBuy);
        return newOrderDrug;
    }
}
