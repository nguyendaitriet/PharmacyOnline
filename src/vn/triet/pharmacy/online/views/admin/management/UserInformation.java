package vn.triet.pharmacy.online.views.admin.management;

import vn.triet.pharmacy.online.models.User;
import vn.triet.pharmacy.online.services.IUserService;
import vn.triet.pharmacy.online.services.UserService;
import vn.triet.pharmacy.online.views.AdminView;

import vn.triet.pharmacy.online.views.GuestView;
import vn.triet.pharmacy.online.views.Menu;
import vn.triet.pharmacy.online.views.guest.services.AccountManagement;

import java.util.List;
import java.util.Scanner;

public class UserInformation {
    private static void showActionForm() {
        System.out.println(" ---------- Users' Information -----------");
        System.out.println("|                                         |");
        System.out.println("|        1. Show all users' account.      |");
        System.out.println("|        2. Update your account.          |");
        System.out.println("|        3. Block user.                   |");
        System.out.println("|        0. Return.                       |");
        System.out.println("|                                         |");
        System.out.println(" -----------------------------------------");
    }

    public static void chooseActionInUsersInfo() {
        do {
            showActionForm();
            try {
                int number = Menu.chooseActionByNumber();

                if (number == 1) {
                    showAllUsers();
                    break;
                }
                if (number == 2) {
                    updateAdminAccount();
                    break;
                }
                if (number == 3) {

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
        System.out.println("\n------------------------------------------------------------------- USERS LIST ----------------------------------------------------------------------------");
        System.out.printf("%-12s %-20s %-20s %-20s %-25s %-25s %-22s %-10s\n", "ID", "Full Name", "Date of Birth", "Phone Number", "Email", "Address", "Username", "Role");
        System.out.println("------------------------------------------------------------------------------------------------------------------------------------------------------------");
        List<User> users = userService.getUsers();
        for (User user : users) {
            System.out.printf("%-12d %-20s %-20s %-20s %-25s %-25s %-22s %-10s\n", user.getId(), user.getFullName(), user.getBirthday(), user.getPhoneNumber(), user.getEmail(), user.getAddress(), user.getUserName(), user.getRole());
        }
        System.out.println("------------------------------------------------------------------------------------------------------------------------------------------------------------\n");
        do {
            System.out.print("Enter '0' to return: ");
            try {
                Scanner input = new Scanner(System.in);
                int number = input.nextInt();
                if (number == 0) {
                    chooseActionInUsersInfo();
                    break;
                }
                Menu.alert();
            } catch (Exception ex) {
                Menu.alert();
            }
        } while (true);
    }

    public static void updateAdminAccount() {
        int number = AccountManagement.updateAccount();
        if (number == 0) {
            chooseActionInUsersInfo();
            return;
        }
        System.out.println("\n===> Your account has been UPDATED successfully!\n");
        chooseActionInUsersInfo();

    }

}
