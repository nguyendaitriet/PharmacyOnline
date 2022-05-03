package vn.triet.pharmacy.online.views;


import java.util.Scanner;

public class Menu {

    public static void showEntrance() {
        System.out.println("\n ____________________________________________ ");
        System.out.println("|     << Welcome to Pharmacy Online >>       |");
        System.out.println("|                                            |");
        System.out.println("|    1. Sign in to use our services.         |");
        System.out.println("|    2. Sign up for a new account.           |");
        System.out.println("|    (Note: Our medicine for adults only)    |");
        System.out.println("|____________________________________________|");
    }

    public static int chooseActionByNumber() {
        Scanner input = new Scanner(System.in);
        System.out.print("\nYour choice is: ");
        return input.nextInt();
    }

    public static String chooseActionByLetter() {
        Scanner input = new Scanner(System.in);
        System.out.print("\nYour choice is: ");
        return input.nextLine();
    }

    public static void alert() {
        System.out.println("!!!!! INVALID input. Please try again !!!!!\n");
    }

    public static void chooseInEntrance() {
        do {
            showEntrance();
            try {
                int number = chooseActionByNumber();
                if (number == 1) {
                    LoginView.signIn();
                    break;
                }
                if (number == 2) {
                    SignUpView newRegister = new SignUpView();
                    newRegister.createAccount();
                    break;
                }
                alert();
            } catch (Exception io) {
                io.printStackTrace();
                alert();
            }
        } while (true);
    }

}
