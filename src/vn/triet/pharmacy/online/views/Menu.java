package vn.triet.pharmacy.online.views;




import java.util.Scanner;

public class Menu {
    private static void showEntrance() {
        System.out.println(" ____________________________________________ ");
        System.out.println("|     << Welcome to Pharmacy Online >>       |");
        System.out.println("|                                            |");
        System.out.println("|    1. Sign in to use our services.         |");
        System.out.println("|    2. Sign up for a new account.           |");
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

    public static void chooseInEntrance() {
        do {
            showEntrance();
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
