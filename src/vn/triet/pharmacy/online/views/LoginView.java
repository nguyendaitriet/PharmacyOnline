package vn.triet.pharmacy.online.views;

import vn.triet.pharmacy.online.models.Role;
import vn.triet.pharmacy.online.models.User;
import vn.triet.pharmacy.online.services.IUserService;
import vn.triet.pharmacy.online.services.UserService;
import java.util.Scanner;

public class LoginView {

    private static long userID;

    public static long getUserID() {
        return userID;
    }

    private static IUserService userService = new UserService();

    public static void signIn() {
        Scanner input = new Scanner(System.in);
        System.out.println("\n\n----- LOGIN -----\n");
        System.out.print("1. Username: ");
        String username = input.nextLine().trim();
        System.out.print("2. Password: ");
        String password = input.nextLine().trim();
        User user = userService.login(username, password);

        if (user == null) {
            showChoicesWhenWrong();
            return;
        }

        if (user.getRole() == Role.ADMIN) {
            System.out.println("\nSign in successfully as ADMIN!");
            userID = user.getId();
            AdminView.chooseAdminAction();
            return;
        }

        System.out.println("\nSuccessful sign-in!");
        userID = user.getId();
        CustomerView.chooseServicesForGuest();

    }

    public static void showChoicesWhenWrong() {
        System.out.println("\nWrong username or password!");
        System.out.println("Do you want to try again?");
        do {
            System.out.println("(Enter 'y' to sign in again or enter 'n' to return)");
            try {
                String letter = Menu.chooseActionByLetter();
                if (letter.charAt(0) == 'y' && letter.length() == 1) {
                    signIn();
                    break;
                }
                if (letter.charAt(0) == 'n' && letter.length() == 1) {
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
