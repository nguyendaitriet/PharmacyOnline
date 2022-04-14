package vn.triet.pharmacy.online.views;

public class SignUpView {
    public static void createAccount() {
        System.out.println();
        System.out.println();
        System.out.println("----- Create your Account -----");

        showSuccessfulMessage();
    }
    public static void confirmSignUp() {

    }
    public static void showSuccessfulMessage() {
        System.out.println("Sign up successfully!");
        System.out.println();
        System.out.println("Please choose next action:");
        System.out.println("1. Sign in now.");
        System.out.println("2. Create a new account.");
        System.out.println();
        do {
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
                Menu.alert();
            } catch (Exception io) {
                Menu.alert();
            }
        } while (true);
    }
}
