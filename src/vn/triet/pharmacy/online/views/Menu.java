package vn.triet.pharmacy.online.views;

import vn.triet.pharmacy.online.models.Method;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Menu {
    public static void entrance() {
        System.out.println(" ____________________________________________ ");
        System.out.println("|     << Welcome to Pharmacy Online >>       |");
        System.out.println("|                                            |");
        System.out.println("|    1. Sign in to use our services.         |");
        System.out.println("|    2. Sign up if you haven't an account.   |");
        System.out.println("|    (Note: Our medicine for adults only)    |");
        System.out.println("|____________________________________________|");
    }


    public static char chooseAction() {
        System.out.print("Your choice is: ");
        Scanner character = new Scanner(System.in);
        return character.next().charAt(0);
    }

    public static void alert() {
        System.out.println("Invalid choice. Please try again!");
    }

//    public static void showChoices(char input, String ClassName, String methodName) {
//        do {
//            entrance();
//            try {
//                char character = chooseAction();
//                if (character == input) {
//                    ClassName.getMethod(methodName);
//                    break;
//                }
//            } catch  (Exception io) {
//                alert();
//            }
//            break;
//        } while (true);
//    }

    public static void chooseInEntrance() {
        do {
            entrance();
            try {
                char character = chooseAction();
                if (character == '1') {
                    LoginView.signIn();
                    break;
                }
                if (character == '2') {
                    SignUpView.createAccount();
                    break;
                }
                alert();
            } catch (Exception io) {
                alert();
            }
        } while (true);
    }


}
