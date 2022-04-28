package vn.triet.pharmacy.online.views.admin.management;

import vn.triet.pharmacy.online.models.Role;
import vn.triet.pharmacy.online.models.User;
import vn.triet.pharmacy.online.services.IUserService;
import vn.triet.pharmacy.online.services.UserService;
import vn.triet.pharmacy.online.views.AdminView;

import vn.triet.pharmacy.online.views.Menu;
import vn.triet.pharmacy.online.views.guest.services.AccountManagement;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Scanner;

public class UserInformation {
    private  static IUserService userService = new UserService();

    private  static List<User> users = userService.getUsers();

    private static void showActionForm() {
        System.out.println("\n ------------- Users' Information --------------");
        System.out.println("|                                               |");
        System.out.println("|        1. Show all users' account.            |");
        System.out.println("|               2. Sort by Name.                |");
        System.out.println("|               3. Sort by Creation Date.       |");
        System.out.println("|               4. Filter by Role Admin.        |");
        System.out.println("|               5. Filter by Role User.         |");
        System.out.println("|        6. Update your Admin account.          |");
        System.out.println("|        7. Block user.                         |");
        System.out.println("|        0. Return.                             |");
        System.out.println("|                                               |");
        System.out.println(" -----------------------------------------------");
    }

    public static void chooseActionInUsersInfo() {
//        IUserService userService = new UserService();
//        List<User> users = userService.getUsers();
        do {
            showActionForm();
            try {
                int number = Menu.chooseActionByNumber();

                if (number == 1) {
                    showUsersList(users, 1);
                    break;
                }
                if (number == 2) {
                    showUsersList(users, 2);
                    break;
                }
                if (number == 3) {
                    showUsersList(users, 3);
                    break;
                }
                if (number == 4) {
                    showUsersList(users, 4);
                    break;
                }
                if (number == 5) {
                    showUsersList(users, 5);
                    break;
                }
                if (number == 6) {
                    updateAdminAccount();
                    break;
                }
                if (number == 7) {

                    break;
                }
                if (number == 0) {
                    AdminView.chooseAdminAction();
                    break;
                }
                Menu.alert();
            } catch (Exception ex) {
                ex.printStackTrace();
                Menu.alert();
            }
        } while (true);
    }

    public static void showUsersList(List<User> users, int action) {
        System.out.println("\nUSERS LIST ----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
        System.out.printf("%-12s %-28s %-20s %-20s %-25s %-25s %-22s %-10s %-10s\n", "ID", "Full Name", "Date of Birth", "Phone Number", "Email", "Address", "Username", "Role", "Creation Date");
        System.out.println("---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
        switch (action) {
            case 1:
                showAllUsers(users);
                break;
            case 2:
                sortByNameASCE(users);
                break;
            case 3:
                sortByCreationDate(users);
                break;
            case 4:
                filterByAdmin(users);
                break;
            case 5:
                filterByUser(users);
                break;
        }
        System.out.println("---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------\n");
        do {
            System.out.print("Enter '0' to return: ");
            try {
                Scanner input = new Scanner(System.in);
                int number = input.nextInt();
                if (number == 0) {
                    chooseActionInUsersInfo();
                    break;
                }
                Menu.alert();
            } catch (Exception ex) {
                Menu.alert();
            }
        } while (true);
    }

    private static void filterByUser(List<User> users) {
        int count = 0;
        for (User user : users) {
            if (user.getRole() == Role.USER) showOneUser(user);
            count++;
        }
        System.out.printf("\n---> Total: %d user%s\n", count, count>1?"s":"");
    }

    private static void filterByAdmin(List<User> users) {
        int count = 0;

        for (User user : users) {
            if (user.getRole() == Role.ADMIN) showOneUser(user);
            count++;
        }
        System.out.printf("\n---> Total: %d administrator%s\n", count, count>1?"s":"");
    }

    private static void sortByCreationDate(List<User> users) {
        users.sort((e1, e2) -> Long.compare(e1.getCreationTime() - e2.getCreationTime(), 0));
        showAllUsers(users);
    }

    private static void sortByNameASCE(List<User> users) {
        users.sort((e1, e2) -> Integer.compare(e1.getFirstName().compareTo(e2.getFirstName()), 0));
        showAllUsers(users);
    }

    public static void showAllUsers(List<User> users) {
        for (User user : users) showOneUser(user);
    }

    public static void showOneUser(User user) {
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy 'at' HH:mm:ss a");
        String creationDateFormat = dateFormat.format(user.getCreationTime());
        System.out.printf("%-12d %-17s%-11s %-20s %-20s %-25s %-25s %-22s %-10s %-10s\n", user.getId(), user.getLastName(), user.getFirstName(), user.getBirthday(), user.getPhoneNumber(), user.getEmail(), user.getAddress(), user.getUserName(), user.getRole(), creationDateFormat);
    }

    public static void updateAdminAccount() {
        int number = AccountManagement.updateAccount();
        if (number == 0) {
            chooseActionInUsersInfo();
            return;
        }
        System.out.println("\n===> Your account has been UPDATED successfully!\n");
        chooseActionInUsersInfo();
    }

}
