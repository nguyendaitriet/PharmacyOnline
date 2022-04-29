package vn.triet.pharmacy.online.views;

import vn.triet.pharmacy.online.models.Role;
import vn.triet.pharmacy.online.models.User;
import vn.triet.pharmacy.online.services.IUserService;
import vn.triet.pharmacy.online.services.UserService;
import vn.triet.pharmacy.online.utils.ValidateUtils;

import java.util.Scanner;

public class SignUpView {

    private final IUserService signUpService;

    public SignUpView() {
        signUpService = new UserService();
    }

    private final static String ADMIN_CODE = "admin190996";

    private static final Scanner input = new Scanner(System.in);

    public void createAccount() {
        System.out.println();
        System.out.println();
        System.out.println("----- Create your Account -----");
        System.out.println();
        User newUser = new User();
        try {
            setID(newUser);
            enterFullName(newUser);
            enterBirthday(newUser);
            enterPhoneNumber(newUser);
            enterAddress(newUser);
            enterEmail(newUser);
            enterUserName(newUser);
            enterPassword(newUser);
            setUpRole(newUser);
            confirmSignUp(newUser);
        } catch (Exception ex) {
            ex.printStackTrace();
            Menu.alert();
            Menu.showExceptionAction();
        }
    }

    public void showConfirmForm() {
        System.out.println("\nPlease confirm that you want to sign up with above information!");
        System.out.println("1. Agree to sign up.");
        System.out.println("2. Return homepage.");
        System.out.println("0. Exit.");
        System.out.println();
    }

    public void confirmSignUp(User newUser) {
        do {
            showConfirmForm();
            int number = Menu.chooseActionByNumber();
            if (number == 1) {
                newUser.setCreationTime();
                signUpService.add(newUser);
                showSuccessfulMessage();
                break;
            }
            if (number == 2) {
                Menu.chooseInEntrance();
                break;
            }
            if (number == 0) {
                System.exit(0);
                break;
            }
            Menu.alert();
        } while (true);
    }

    public void showNextAction() {
        System.out.println("Please choose next action:");
        System.out.println("1. Sign in now.");
        System.out.println("2. Create another account.");
        System.out.println("0. Exit.\n");
    }

    public void showSuccessfulMessage() {
        System.out.println("\nSign up successfully!\n");
        do {
            showNextAction();
            int number = Menu.chooseActionByNumber();
            if (number == 1) {
                LoginView.signIn();
                break;
            }
            if (number == 2) {
                createAccount();
                break;
            }
            if (number == 0) {
                System.exit(0);
                break;
            }
            Menu.alert();
        } while (true);
    }

    public void setID(User newUser) {
        int min = 100000;
        int max = 999999;
        int id;
        do {
            id = (int) Math.floor(Math.random() * (max - min + 1) + min);
        } while (signUpService.isIdExisted(id));
        newUser.setId(id);
        System.out.println("1. Your Account ID: " + id);
    }

//    public void enterID(User newUser) {
//        do {
//            try {
//                System.out.println("1. Enter ID (NUMBER). ");
//                System.out.print("==> ");
//                Scanner input = new Scanner(System.in);
//                int id = Integer.parseInt(input.nextLine());
//                if (!signUpService.isIdExisted(id)) {
//                    newUser.setId(id);
//                    break;
//                }
//                System.out.println("Your entered ID have existed. Please enter another ID!");
//                System.out.println();
//            } catch (Exception ex) {
//                ex.printStackTrace();
//                Menu.alert();
//                Menu.showExceptionAction();
//            }
//        }
//        while (true);
//    }


    public void enterFullName(User newUser) {
        do {
            System.out.println("2. Enter Full Name (Example: Will Smith). ");
            System.out.print("==> ");
//            Scanner input = new Scanner(System.in);
            String fullName = input.nextLine().trim();
            System.out.println();
            if (ValidateUtils.isNameValid(fullName)) {
                newUser.setFullName(fullName);
                break;
            }
            System.out.println("Invalid name format, please try again!");
            System.out.println();
        } while (true);
    }

    public void enterBirthday(User newUser) {
        do {
            System.out.println("3. Enter Date of Birth (Example: 12/04/1963) ");
            System.out.println("(Note: Your date must be before 01/01/2022)");
            System.out.print("==> ");
//            Scanner input = new Scanner(System.in);
            String birthday = input.nextLine().trim();
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

    public void enterPhoneNumber(User newUser) {
        do {
            System.out.println("4. Enter Phone Number (Example: 0783465748). ");
            System.out.println("(Note: Your phone number must start with '0' and has from 10 to 11 digits)");
            System.out.print("==> ");
//            Scanner input = new Scanner(System.in);
            String phoneNumber = input.nextLine().trim();
            System.out.println();
            if (!ValidateUtils.isPhoneValid(phoneNumber)) {
                System.out.println("Invalid phone number format, please try again!");
                System.out.println();
                continue;
            }
            if (signUpService.checkExistedPhone(phoneNumber)) {
                System.out.println("Phone number has existed. Please enter another number!");
                System.out.println();
                continue;
            }
            newUser.setPhoneNumber(phoneNumber);
            break;
        } while (true);
    }

    public void enterAddress(User newUser) {
        System.out.println("5. Enter Address (Example: 4/18 An Duong Vuong, Hue).");
        System.out.print("==> ");
//        Scanner input = new Scanner(System.in);
        String address = input.nextLine().trim();
        newUser.setAddress(address);
    }

    public void enterEmail(User newUser) {
        do {
            System.out.println("6. Enter Email Address (Example: namnguyen123@gmail.com). ");
            System.out.print("==> ");
//            Scanner input = new Scanner(System.in);
            String email = input.nextLine().trim().toLowerCase();
            if (!ValidateUtils.isEmailValid(email)) {
                System.out.println("Invalid email address format, please try again!");
                System.out.println();
                continue;
            }
            if (signUpService.checkExistedEmail(email)) {
                System.out.println("Email address has existed. Please enter another email address!");
                System.out.println();
                continue;
            }
            newUser.setEmail(email);
            break;
        } while (true);
    }

    public void enterUserName(User newUser) {
        do {
            System.out.println("7. Enter username.");
            System.out.print("==> ");
//            Scanner input = new Scanner(System.in);
            String username = input.nextLine().trim().toLowerCase();
            System.out.println();
            if (!signUpService.checkExistedUserName(username)) {
                newUser.setUserName(username);
                break;
            }
            System.out.println("Username has existed. Please enter another username!");
            System.out.println();
        } while (true);
    }

    public void enterPassword(User newUser) {
        do {
            System.out.println("8. Enter password (Example: myname!0907)");
            System.out.println("(NOTE: Minimum eight characters, at least one letter, one number and one special character).");
            System.out.print("==> ");
//            Scanner input = new Scanner(System.in);
            String password = input.nextLine().trim();
            System.out.println();
            if (ValidateUtils.isPasswordValid(password)) {
                newUser.setPassword(password);
                break;
            }
            System.out.println("Invalid password format, please try again!");
            System.out.println();
        } while (true);
    }

    private void setUpRole(User newUser) {
        System.out.println("9. Set up ROLE:\n");
        System.out.println("---> Are you an administrator?");
        System.out.println("(Enter 'y' or 'n')\n");
        boolean check;
        do {
            String letter = Menu.chooseActionByLetter();
            if (letter.charAt(0) == 'y' && letter.length() == 1) {
                check = checkAdminCode();
                break;
            }
            if (letter.charAt(0) == 'n' && letter.length() == 1) {
                check = false;
                break;
            }
            Menu.alert();
        } while (true);
        if (check) newUser.setRole(Role.ADMIN);
        else newUser.setRole(Role.USER);
    }

    private boolean checkAdminCode() {
        System.out.print("Enter administrator code: ");
//        Scanner input = new Scanner(System.in);
        String code = input.nextLine().trim();
        if (code.equals(ADMIN_CODE)) {
            System.out.println("Set ROLE as administrator successfully!\n");
            return true;
        }
        do {
            System.out.println("Incorrect code, do you want to try again?");
            System.out.println("(Enter 'y' or 'n')\n");
            String letter = Menu.chooseActionByLetter();
            switch (letter.charAt(0)) {
                case 'y':
                    return checkAdminCode();
                case 'n':
                    return false;
                default:
                    Menu.alert();
            }
        } while (true);
    }

}
