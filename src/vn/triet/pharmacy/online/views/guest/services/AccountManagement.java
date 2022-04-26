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

    public static User showAccount() {
        List<User> users = userService.getUsers();
        for (User user : users) {
            if (user.getId() == LoginView.getUserID()) {
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
                return user;
            }
        }
        return null;
    }

    public static void editAccount() {
        User currentUser = showAccount();
        SignUpView newRegister = new SignUpView();
        int number = -1;
        do {
            System.out.println("Choose what information you want to update.");
            System.out.println("NOTE: You cannot update your account ID. Please don't enter '1'!\n");
            System.out.println("---> Enter '0' to cancel updating.");
            System.out.println("---> Enter '9' to confirm that you agree to update your account.\n");
            try {
                number = Menu.chooseActionByNumber();
                switch (number) {
                    case 2:
                        newRegister.enterFullName(currentUser);
                        System.out.println("Updated your full name.\n");
                        break;
                    case 3:
                        newRegister.enterBirthday(currentUser);
                        System.out.println("Updated your date of birth.\n");
                        break;

                    case 4:
                        newRegister.enterPhoneNumber(currentUser);
                        System.out.println("Updated your phone number.\n");
                        break;
                    case 5:
                        newRegister.enterAddress(currentUser);
                        System.out.println("Updated your address.\n");
                        break;
                    case 6:
                        newRegister.enterEmail(currentUser);
                        System.out.println("Updated your email.\n");
                        break;
                    case 7:
                        newRegister.enterUserName(currentUser);
                        System.out.println("Updated your username.\n");
                        break;
                    case 8:
                        newRegister.enterPassword(currentUser);
                        System.out.println("Updated your password.\n");
                        break;
                }
            } catch (Exception ex) {
                Menu.showExceptionAction();
                ex.printStackTrace();
            }
        } while (number != 0 && number != 9);

        if (number == 0) {
            GuestView.chooseServicesForGuest();
        }
        if (number == 9) {
            userService.update(currentUser);
            System.out.println("---> Your account has been updated successfully!\n");
            GuestView.chooseServicesForGuest();
        }
    }

}


