package vn.triet.pharmacy.online.views;




import java.util.Scanner;

public class Menu {

    public static void showEntrance() {
        System.out.println(" ____________________________________________ ");
        System.out.println("|     << Welcome to Pharmacy Online >>       |");
        System.out.println("|                                            |");
        System.out.println("|    1. Sign in to use our services.         |");
        System.out.println("|    2. Sign up for a new account.           |");
        System.out.println("|    (Note: Our medicine for adults only)    |");
        System.out.println("|____________________________________________|");
    }

    public static int chooseAction() {
        Scanner input = new Scanner(System.in);
        System.out.print("Your choice is: ");
        return input.nextInt();
    }

    public static void alert() {
        System.out.println("Invalid input. Please try again!");
    }

    public static void chooseInEntrance() {
        do {
            showEntrance();
            try {
                int number = chooseAction();
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
