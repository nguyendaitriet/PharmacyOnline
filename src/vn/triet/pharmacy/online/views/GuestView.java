package vn.triet.pharmacy.online.views;

public class GuestView {
    public static void showServiceForm() {
        System.out.println("*---------------------------------------------------*");
        System.out.println("|   What would you like to do?                      |");
        System.out.println("|                                                   |");
        System.out.println("|   1. Buy medicine depend on your symptom(s)       |");
        System.out.println("|   2. Buy medicine with medical prescription       |");
        System.out.println("|   3. Edit your account.                           |");
        System.out.println("|   0. Exit                                         |");
        System.out.println("*---------------------------------------------------*");
        System.out.println();
    }

    public static void showServicesForGuest() {
        do {
            showServiceForm();
            try {
                char character = Menu.chooseAction();
                if (character == '1') {

                    break;
                }
                if (character == '2') {

                    break;
                }
                if (character == '3') {

                    break;
                }
                if (character == '0') {
                    System.exit(0);
                    break;
                }
                Menu.alert();
            } catch (Exception io) {
                Menu.alert();
            }
        } while (true);
    }
}
