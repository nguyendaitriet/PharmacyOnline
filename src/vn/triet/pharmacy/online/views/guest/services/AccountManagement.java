package vn.triet.pharmacy.online.views.guest.services;

import vn.triet.pharmacy.online.models.User;
import vn.triet.pharmacy.online.services.IUserService;
import vn.triet.pharmacy.online.services.UserService;
import vn.triet.pharmacy.online.views.GuestView;
import vn.triet.pharmacy.online.views.LoginView;
import vn.triet.pharmacy.online.views.Menu;
import vn.triet.pharmacy.online.views.SignUpView;

import java.util.List;

public class AccountManagement {
    private static IUserService userService = new UserService();

    public static User getCurrentUser() {
        List<User> users = userService.getUsers();
        for (User user : users) {
            if (user.getId() == LoginView.getUserID()) return user;
        }
        return null;
    }

    public static void showCurrentAccount(User user) {
        System.out.println("\n--------------Your Account---------------\n");
        System.out.printf("%-17s %-12d\n", "1. ID:", user.getId());
        System.out.printf("%-17s %-12s\n", "2. Full Name:", user.getFullName());
        System.out.printf("%-17s %-12s\n", "3. Date Of Birth:", user.getBirthday());
        System.out.printf("%-17s %-12s\n", "4. Phone Number:", user.getPhoneNumber());
        System.out.printf("%-17s %-12s\n", "5. Address:", user.getAddress());
        System.out.printf("%-17s %-12s\n", "6. Email:", user.getEmail());
        System.out.printf("%-17s %-12s\n", "7. Username:", user.getUserName());
        System.out.printf("%-17s %-12s\n", "8. Password:", user.getPassword());
        System.out.println("\n-----------------------------------------\n");
    }

    public static int updateAccount() {
        User currentUser = getCurrentUser();
        SignUpView newRegister = new SignUpView();
        boolean is = true;
        int number = -1;
        do {
            showCurrentAccount(currentUser);
            showChangeStatus(number);
            System.out.println("Choose what information you want to update.");
            System.out.println("NOTE: You CANNOT update your account ID. Please don't enter '1'!\n");
            System.out.println("---> Enter '0' to cancel updating.");
            System.out.println("---> Enter '9' to CONFIRM that you agree to update your account with below information.\n");
            try {
                number = Menu.chooseActionByNumber();
                switch (number) {
                    case 2:
                        newRegister.enterFullName(currentUser);
                        is = false;
                        break;
                    case 3:
                        newRegister.enterBirthday(currentUser);
                        is = false;
                        break;
                    case 4:
                        newRegister.enterPhoneNumber(currentUser);
                        is = false;
                        break;
                    case 5:
                        newRegister.enterAddress(currentUser);
                        is = false;
                        break;
                    case 6:
                        newRegister.enterEmail(currentUser);
                        is = false;
                        break;
                    case 7:
                        newRegister.enterUserName(currentUser);
                        is = false;
                        break;
                    case 8:
                        newRegister.enterPassword(currentUser);
                        is = false;
                        break;
                    case 9:
                        userService.update(currentUser);
                        is = true;
                        break;
                    case 0:
                        is = true;
                        break;
                    default:
                        Menu.alert();
                        is = false;
                }
            } catch (Exception ex) {
                ex.printStackTrace();
                Menu.showExceptionAction();
            }
        } while (!is);
        return number;
    }

    public static void confirmUpdating() {
        int number = updateAccount();
        if (number == 0) {
            GuestView.chooseServicesForGuest();
            return;
        }
        System.out.println("\n===> Your account has been UPDATED successfully!\n");
        GuestView.chooseServicesForGuest();
    }

    public static void showChangeStatus(int number) {
        switch (number) {
            case 2 -> System.out.println("--- Your full name has been changed.\n");
            case 3 -> System.out.println("--- Your date of birth has been changed.\n");
            case 4 -> System.out.println("--- Your phone number has been changed.\n");
            case 5 -> System.out.println("--- Your address been changed.\n");
            case 6 -> System.out.println("--- Your email been changed.\n");
            case 7 -> System.out.println("--- Your username has been changed.\n");
            case 8 -> System.out.println("--- Your password has been changed.\n");
        }
    }
}

