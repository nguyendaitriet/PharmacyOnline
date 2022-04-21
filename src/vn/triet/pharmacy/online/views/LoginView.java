package vn.triet.pharmacy.online.views;

import java.util.Scanner;

public class LoginView {

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
                char character = Menu.chooseAction();
                if (character == 'y') {
                    LoginView.signIn();
                    break;
                }
                if (character == 'n') {
                    Menu.chooseInEntrance();
                    break;
                }
                Menu.alert();
            } catch (Exception io) {
                Menu.alert();
            }
        } while (true);
    }
}
