package vn.triet.pharmacy.online.views;

import vn.triet.pharmacy.online.views.admin.management.MedicineManagement;
import vn.triet.pharmacy.online.views.admin.management.UserInformation;

public class AdminView {

    public static void showActionsForm() {
        System.out.println("\n**************************************");
        System.out.println("|                                    |");
        System.out.println("|      ADMIN MANAGEMENT              |");
        System.out.println("|                                    |");
        System.out.println("|      1. Users' information.        |");
        System.out.println("|      2. Medicine management.       |");
        System.out.println("|      3. Order management.          |");
        System.out.println("|      0. Go back to homepage.       |");
        System.out.println("|                                    |");
        System.out.println("**************************************\n");
    }

    public static void chooseAdminAction() {
        do {
            showActionsForm();
            try {
                int number = Menu.chooseActionByNumber();

                if (number == 1) {
                    UserInformation.chooseActionInUsersInfo();
                    break;
                }
                if (number == 2) {
                    MedicineManagement.chooseActionInMedicineManagement();
                    break;
                }
                if (number == 3) {

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
