package vn.triet.pharmacy.online.views.admin.management;

import vn.triet.pharmacy.online.views.AdminView;

import vn.triet.pharmacy.online.views.Menu;

public class UserInformation {

    private static void showActionForm() {
        System.out.println(" ---------- Users' Information -----------");
        System.out.println("|                                         |");
        System.out.println("|        1. Show all users.               |");
        System.out.println("|        2. Block user.                   |");
        System.out.println("|        0. Return.                       |");
        System.out.println("|                                         |");
        System.out.println(" -----------------------------------------");
    }

    public static void chooseActionInUsersInfo() {
        do {
            showActionForm();
            try {
                char character = Menu.chooseAction();
                if (character == '1') {

                    break;
                }
                if (character == '2') {

                    break;
                }
                if (character == '0') {
                    AdminView.chooseAdminAction();
                    break;
                }
                Menu.alert();
            } catch (Exception io) {
                Menu.alert();
            }
        } while (true);
    }
}
