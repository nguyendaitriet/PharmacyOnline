package vn.triet.pharmacy.online.views;

import vn.triet.pharmacy.online.views.guest.services.AccountManagement;

public class GuestView {

    public static void showServicesForm() {
        System.out.println("\n*---------------------------------------------------*");
        System.out.println("|                                                   |");
        System.out.println("|   What would you like to do?                      |");
        System.out.println("|                                                   |");
        System.out.println("|   1. Buy medicine depend on your symptom(s).      |");
        System.out.println("|   2. Buy medicine with medical prescription.      |");
        System.out.println("|   3. Check all order.                             |");
        System.out.println("|   4. Update account.                              |");
        System.out.println("|   0. Go back to homepage.                         |");
        System.out.println("|                                                   |");
        System.out.println("*---------------------------------------------------*\n");
    }

    public static void chooseServicesForGuest() {
        do {
            showServicesForm();
            try {
                int number = Menu.chooseActionByNumber();

                if (number == 1) {

                    break;
                }
                if (number == 2) {

                    break;
                }
                if (number == 3) {

                    break;
                }
                if (number == 4) {
                    AccountManagement.confirmUpdating();
                    break;
                }
                if (number == 0) {
                    Menu.chooseInEntrance();
                    break;
                }
                Menu.alert();
            } catch (Exception ex) {
                ex.printStackTrace();
                Menu.alert();
            }
        } while (true);
    }
}
