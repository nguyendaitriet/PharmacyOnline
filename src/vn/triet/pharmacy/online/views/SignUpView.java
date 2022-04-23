package vn.triet.pharmacy.online.views;

import vn.triet.pharmacy.online.models.User;
import vn.triet.pharmacy.online.services.SignUpService;
import vn.triet.pharmacy.online.utils.ValidateUtils;

import java.util.Locale;
import java.util.Scanner;

public class SignUpView {
    private static Scanner input = new Scanner(System.in);
    private static SignUpService signUpService = new SignUpService();

    public static void createAccount() {
        System.out.println();
        System.out.println();
        System.out.println("----- Create your Account -----");
        System.out.println();
        try {
            User newUser = new User();
            enterID(newUser);
            enterFullName(newUser);
            enterBirthday(newUser);
            enterPhoneNumber(newUser);
            enterAddress(newUser);
            enterEmail(newUser);
            enterUserName(newUser);
            enterPassword(newUser);
            setUpRole(newUser);
        } catch (Exception io) {
            System.out.println("Invalid input. Please try again!");
        }
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

    public static void showNextAction() {
        System.out.println("Please choose next action:");
        System.out.println("1. Sign in now.");
        System.out.println("2. Create a new account.");
        System.out.println("0. Exit.");
        System.out.println();
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

    public static void enterID(User newUser) {
        do {
            System.out.println("1. Enter ID. ");
            System.out.print("==> ");
            String id = input.next().trim();
            System.out.println();
            if (!signUpService.isIdExisted(id)) {
                newUser.setId(id);
                break;
            }
            System.out.println("Your entered ID have existed. Please enter another ID!");
            System.out.println();
        } while (true);
    }

    public static void enterFullName(User newUser) {
        do {
            System.out.println("2. Enter Full Name (Example: Will Smith). ");
            System.out.print("==> ");
            String fullName = input.next().trim();
            System.out.println();
            if (ValidateUtils.isNameValid(fullName)) {
                newUser.setFullName(fullName);
                break;
            }
            System.out.println("Invalid name format, please try again!");
        } while (true);
    }

    public static void enterBirthday(User newUser) {
        do {
            System.out.println("3. Enter Date of Birth (Example: 12/04/1963) ");
            System.out.println("(Note: Your date must be before 01/01/2022)");
            System.out.print("==> ");
            String birthday = input.next().trim();
            System.out.println();
            if (ValidateUtils.isDateValid(birthday)) {
                int before01012022 = ValidateUtils.convertDate(birthday).compareTo("20220101");
                if (before01012022 < 0) {
                    newUser.setBirthday(birthday);
                    break;
                }
            }
            System.out.println("Invalid date format or date is after 01/01/2022, please try again!");
            System.out.println();
        } while (true);
    }

    public static void enterPhoneNumber(User newUser) {
        System.out.println("4. Enter Phone Number (Example: 0783465748). ");
        System.out.println("(Note: Your phone number must start with '0' and has from 10 to 11 digits)");
        System.out.print("==> ");
        String phoneNumber = input.next().trim();
        System.out.println();
        if (!ValidateUtils.isPhoneValid(phoneNumber)) {
            System.out.println("Invalid phone number format, please try again!");
            System.out.println();
            enterPhoneNumber(newUser);
        }
        if (signUpService.checkExistedPhone(phoneNumber)) {
            System.out.println("Phone number has existed. Please enter another number!");
            System.out.println();
            enterPhoneNumber(newUser);
        }
        newUser.setPhoneNumber(phoneNumber);
    }

    public static void enterAddress(User newUser) {
        System.out.println("5. Enter Address (Example: 4/18 An Duong Vuong, Hue. ");
        System.out.print("==> ");
        String address = input.next().trim();
        newUser.setFullName(address);
    }


    private static void enterEmail(User newUser) {
        System.out.println("6. Enter Email Address (Example: namnguyen123@gmail.com). ");
        System.out.print("==> ");
        String email = input.next().trim().toLowerCase();
        if (!ValidateUtils.isPhoneValid(email)) {
            System.out.println("Invalid email address format, please try again!");
            System.out.println();
            enterEmail(newUser);
        }
        if (signUpService.checkExistedPhone(email)) {
            System.out.println("Email address has existed. Please enter another email address!");
            System.out.println();
            enterEmail(newUser);
        }
        newUser.setPhoneNumber(email);
    }

    private static void enterUserName(User newUser) {
        System.out.println("7. Enter username.");
        System.out.print("==> ");
        String username = input.next().trim().toLowerCase();
        if (!ValidateUtils.isPhoneValid(email)) {
            System.out.println("Invalid email address format, please try again!");
            System.out.println();
            enterEmail(newUser);
        }
        if (signUpService.checkExistedPhone(email)) {
            System.out.println("Email address has existed. Please enter another email address!");
            System.out.println();
            enterEmail(newUser);
        }
        newUser.setPhoneNumber(email);
    }

    private static void enterPassword(User newUser) {
    }

    private static void setUpRole(User newUser) {
    }
}
