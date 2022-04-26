package vn.triet.pharmacy.online.views.admin.management;

import vn.triet.pharmacy.online.views.AdminView;
import vn.triet.pharmacy.online.views.Menu;

import java.util.Scanner;

public class MedicineManagement {

    private static void showActionForm() {
        System.out.println(" -------------- Medicine management ---------------");
        System.out.println("|                                                  |");
        System.out.println("|        1. Show medicine list.                    |");
        System.out.println("|        2. Add new medicine.                      |");
        System.out.println("|        3. Edit information in medicine list.     |");
        System.out.println("|        4. Remove medicine.                       |");
        System.out.println("|        0. Return.                                |");
        System.out.println("|                                                  |");
        System.out.println(" --------------------------------------------------");
    }

    public static void chooseActionInMedicineManagement() {
        do {
            showActionForm();
            try {
                int number = Menu.chooseAction();

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
                    System.out.println("------ Sorry, this action is not available now. Please choose another!");
                    chooseActionInMedicineManagement();
                    break;
                }
                if (number == 0) {
                    AdminView.chooseAdminAction();
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
