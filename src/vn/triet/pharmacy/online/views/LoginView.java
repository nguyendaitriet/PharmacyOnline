package vn.triet.pharmacy.online.views;

import vn.triet.pharmacy.online.services.IUserService;
import vn.triet.pharmacy.online.services.UserService;

import java.util.Scanner;

public class LoginView {
    private static int userID;
    private final IUserService signUpService = new UserService();
    
    public static void signIn() {
        Scanner input = new Scanner(System.in);
        System.out.println();
        System.out.println();
        System.out.println("----- LOGIN -----");
        System.out.println();
        System.out.print("1. Username: ");
        String username = input.next();
        System.out.print("2. Password: ");
        String password = input.next();

        //Check username and password
        if (username.equals("admin") && password.equals("admin")) {
            System.out.println("------ Sign in successfully as ADMIN!");
            System.out.println();
            AdminView.chooseAdminAction();
            return;
        }
        if (username.equals("abc") && password.equals("abc")) {
            System.out.println("------ Sign in successfully!");
            System.out.println();
            GuestView.chooseServicesForGuest();
            return;
        }

        //Wrong username or password
        showChoicesWhenWrong();
    }

    public static void showChoicesWhenWrong() {
        System.out.println("Wrong username or password!");
        System.out.println("Do you want to try again?");
        do {
            System.out.println("(Enter 'y' to sign in again or enter 'n' to return)");
            System.out.println();
            try {
                String letter = Menu.chooseActionByLetter();
                if (letter.charAt(0) == 'y' && letter.length()==1) {
                    LoginView.signIn();
                    break;
                }
                if (letter.charAt(0) == 'n' && letter.length()==1) {
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
