package vn.triet.pharmacy.online.views;

import vn.triet.pharmacy.online.models.Role;
import vn.triet.pharmacy.online.models.User;
import vn.triet.pharmacy.online.services.IUserService;
import vn.triet.pharmacy.online.services.UserService;
import vn.triet.pharmacy.online.utils.ValidateUtils;
import vn.triet.pharmacy.online.views.admin.management.UserInformation;
import java.util.Scanner;

public class SignUpView {

    private final IUserService signUpService;

    public SignUpView() {
        signUpService = new UserService();
    }

    private final static String ADMIN_CODE = "admin190996";

    private static final Scanner input = new Scanner(System.in);

    public void createAccount() {
        System.out.println("\n\n----- Create your Account -----\n");
        System.out.println("-----> NOTE: Enter '" + Exit.E1 + "' if you want to cancel signing up at any step.\n");
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
        }
    }

    public void showConfirmForm() {
        System.out.println("\nPlease confirm that you want to sign up with above information!");
        System.out.println("1. Agree to sign up.");
        System.out.println("2. Return homepage.");
        System.out.println("0. Exit.\n");
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
        long id;
        do {
            id = (long) Math.floor(Math.random() * (max - min + 1) + min);
        } while (signUpService.isIdExisted(id));
        newUser.setId(id);
        System.out.println("\n1. Your Account ID: " + id);
    }

    public boolean cancelEntering(String string) {

        switch (string.toLowerCase()) {
            case Exit.E1:
                System.out.println("\n-----> Your register has been canceled!");
                Menu.chooseInEntrance();
                return true;
            case Exit.E2:
                System.out.println("\n-----> Your updating has been canceled!");
                CustomerView.chooseServicesForGuest();
                return true;
            case Exit.E3:
                System.out.println("\n-----> Your updating has been canceled!");
                UserInformation.chooseActionInUsersInfo();
                return true;
        }
        return false;
    }

    public boolean enterFullName(User newUser) {
        do {
            System.out.println("\n2. Enter Full Name (Example: Will Smith). ");
            System.out.print("==> ");
            String fullName = input.nextLine().trim();
            System.out.println();
            if (fullName.equals("")) {
                System.out.println("Your name is required!");
                continue;
            }
            if (ValidateUtils.isNameValid(fullName)) {
                newUser.setFullName(fullName);
                return false;
            }
            if (cancelEntering(fullName)) return true;
            System.out.println("Invalid name format, please try again!\n");
        } while (true);
    }

    public boolean enterBirthday(User newUser) {
        do {
            System.out.println("\n3. Enter Date of Birth (Example: 12/04/1963) ");
            System.out.println("NOTE: Make sure you're OVER 18 years old (Your date must be before 01/01/2005).");
            System.out.print("==> ");
            String birthday = input.nextLine().trim();
            System.out.println();
            if (cancelEntering(birthday)) return true;
            if (birthday.equals("")) {
                System.out.println("Your date of birth is required!");
                continue;
            }
            if (!ValidateUtils.isDateValid(birthday)) {
                System.out.println("Invalid date, please try again!\n");
                continue;
            }
            int before01012022 = ValidateUtils.convertDate(birthday).compareTo("20220101");
            if (before01012022 > 0) {
                System.out.println("Sorry '" + birthday + "' is after 01/01/2005, please try again!\n");
                continue;
            }
            newUser.setBirthday(birthday);
            return false;
        } while (true);
    }

    public boolean enterPhoneNumber(User newUser) {
        do {
            System.out.println("\n4. Enter Phone Number (Example: 0783465748). ");
            System.out.println("(Note: First digit must be '0', second digit is form '1' to '9' and length is from 10 to 11 digits)");
            System.out.print("==> ");
            String phoneNumber = input.nextLine().trim();
            System.out.println();
            if (phoneNumber.equals("")) {
                System.out.println("Your phone number is required!");
                continue;
            }
            if (cancelEntering(phoneNumber)) return true;
            if (!ValidateUtils.isPhoneValid(phoneNumber)) {
                System.out.println("Invalid phone number format, please try again!\n");
                continue;
            }
            if (signUpService.checkExistedPhone(phoneNumber)) {
                System.out.println("Phone number has existed. Please enter another number!\n");
                continue;
            }
            newUser.setPhoneNumber(phoneNumber);
            return false;
        } while (true);
    }

    public boolean enterAddress(User newUser) {
        System.out.println("\n5. Enter Address (Example: 4/18 An Duong Vuong, Hue).");
        System.out.print("==> ");
        String address = input.nextLine().trim();
        if (cancelEntering(address)) return true;
        newUser.setAddress(address);
        return false;
    }

    public boolean enterEmail(User newUser) {
        do {
            System.out.println("\n6. Enter Email Address (Example: namnguyen123@gmail.com). ");
            System.out.print("==> ");
            String email = input.nextLine().trim().toLowerCase();
            if (email.equals("")) {
                System.out.println("Your email is required!");
                continue;
            }
            if (cancelEntering(email)) return true;
            if (!ValidateUtils.isEmailValid(email)) {
                System.out.println("Invalid email address format, please try again!\n");
                continue;
            }
            if (signUpService.checkExistedEmail(email)) {
                System.out.println("Email address has existed. Please enter another email address!\n");
                continue;
            }
            newUser.setEmail(email);
            return false;
        } while (true);
    }

    public boolean enterUserName(User newUser) {
        do {
            System.out.println("\n7. Enter username.");
            System.out.println("NOTE: ");
            System.out.println("  * Your username must start with A LETTER");
            System.out.println("  * All other characters can be alphabets, numbers or an underscore '_'");
            System.out.println("  * Length constraint is from 8 to 20 characters.");
            System.out.print("==> ");
            String username = input.nextLine().trim().toLowerCase();
            System.out.println();
            if (username.equals("")) {
                System.out.println("Your username is required!\n");
                continue;
            }
            if (cancelEntering(username)) return true;
            if (!ValidateUtils.isUsernameValid(username)) {
                System.out.println("Invalid username format, please try again!\n");
                continue;
            }
            if (signUpService.checkExistedUserName(username)) {
                System.out.println("Username has existed. Please enter another username!\n");
                continue;
            }
            newUser.setUserName(username);
            return false;
        } while (true);
    }

    public void enterPassword(User newUser) {
        do {
            System.out.println("\n8. Enter password (Example: myname!0907)");
            System.out.println("(NOTE: Minimum eight characters, at least one letter, one number and one special character).");
            System.out.print("==> ");
            String password = input.nextLine().trim();
            System.out.println();
            if (password.equals("")) {
                System.out.println("Your password is required!");
                continue;
            }
            if (ValidateUtils.isPasswordValid(password)) {
                newUser.setPassword(password);
                break;
            }
            if (cancelEntering(password)) break;
            System.out.println("Invalid password format, please try again!\n");
        } while (true);
    }

    private void setUpRole(User newUser) {
        System.out.println("\n9. Set up ROLE:\n");
        System.out.println("---> Are you an administrator?");
        System.out.println("(Enter 'y' or 'n')\n");
        boolean check;
        do {
            String letter = Menu.chooseActionByLetter();
            if (letter.charAt(0) == 'y') {
                check = checkAdminCode();
                break;
            }
            if (letter.charAt(0) == 'n') {
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
