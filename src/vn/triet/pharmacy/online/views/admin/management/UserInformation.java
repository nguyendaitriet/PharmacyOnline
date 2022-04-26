package vn.triet.pharmacy.online.views.admin.management;

import vn.triet.pharmacy.online.models.User;
import vn.triet.pharmacy.online.services.IUserService;
import vn.triet.pharmacy.online.services.UserService;
import vn.triet.pharmacy.online.views.AdminView;

import vn.triet.pharmacy.online.views.Menu;

import java.util.List;
import java.util.Scanner;

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
                int number = Menu.chooseAction();

                if (number == 1) {
                    showAllUsers();
                    break;
                }
                if (number == 2) {

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

    public static void showAllUsers() {
        IUserService userService = new UserService();
        System.out.println("-------------------------------------------------------- USERS LIST --------------------------------------------------------");
        System.out.printf("%-12s %-20s %-20s %-25s %-25s %-10s\n", "ID", "Full Name", "Phone Number", "Email", "Address", "Role");
        System.out.println("-----------------------------------------------------------------------------------------------------------------------------");

        List<User> users = userService.getUsers();
        for (User user : users) {
            System.out.printf("%-12d %-20s %-20s %-25s %-25s %-10s\n", user.getId(), user.getFullName(), user.getPhoneNumber(), user.getEmail(), user.getAddress(), user.getRole());
        }
        System.out.println("-----------------------------------------------------------------------------------------------------------------------------");
        System.out.println(" ");
    }
}
