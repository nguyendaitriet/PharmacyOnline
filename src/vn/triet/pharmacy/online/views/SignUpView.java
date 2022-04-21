package vn.triet.pharmacy.online.views;

public class SignUpView {
    public static void createAccount() {
        System.out.println();
        System.out.println();
        System.out.println("----- Create your Account -----");

        confirmSignUp();
    }

    public static void showConfirmForm() {
        System.out.println("Please confirm that you want to sign up with above information!");
        System.out.println("1. Agree to sign up.");
        System.out.println("2. Return homepage.");
        System.out.println("0. Exit.");
        System.out.println();
    }

    public static void confirmSignUp() {
        do {
            showConfirmForm();
            try {
                char character = Menu.chooseAction();
                if (character == '1') {
                    showSuccessfulMessage();
                    break;
                }
                if (character == '2') {
                    Menu.chooseInEntrance();
                    break;
                }
                if (character == '0') {
                    System.exit(0);
                    break;
                }
                Menu.alert();
            } catch (Exception io) {
                Menu.alert();
            }
        } while (true);
    }

    private static void showNextAction() {
        System.out.println("Please choose next action:");
        System.out.println("1. Sign in now.");
        System.out.println("2. Create a new account.");
        System.out.println("0. Exit.");
        System.out.println()
        ;
    }

    public static void showSuccessfulMessage() {
        System.out.println("Sign up successfully!");
        System.out.println();
        do {
            showNextAction();
            try {
                char character = Menu.chooseAction();
                if (character == '1') {
                    LoginView.signIn();
                    break;
                }
                if (character == '2') {
                    SignUpView.createAccount();
                    break;
                }
                if (character == '0') {
                    System.exit(0);
                    break;
                }
                Menu.alert();
            } catch (Exception io) {
                Menu.alert();
            }
        } while (true);
    }
}
