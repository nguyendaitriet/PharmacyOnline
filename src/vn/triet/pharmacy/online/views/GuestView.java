package vn.triet.pharmacy.online.views;

import java.util.ArrayList;
import java.util.Scanner;

public class GuestView{
    private static Scanner input = new Scanner(System.in);

    public static void showServicesForm() {
        System.out.println("*---------------------------------------------------*");
        System.out.println("|                                                   |");
        System.out.println("|   What would you like to do?                      |");
        System.out.println("|                                                   |");
        System.out.println("|   1. Buy medicine depend on your symptom(s).      |");
        System.out.println("|   2. Buy medicine with medical prescription.      |");
        System.out.println("|   3. Edit your account.                           |");
        System.out.println("|   0. Go back to homepage.                         |");
        System.out.println("|                                                   |");
        System.out.println("*---------------------------------------------------*");
        System.out.println();
    }

    public static void chooseServicesForGuest() {
        do {
            showServicesForm();
            try {
                int number = Menu.chooseAction();

                if (number == 1) {

                    break;
                }
                if (number == 2) {

                    break;
                }
                if (number == 3) {

                    break;
                }
                if (number ==0) {
                    Menu.chooseInEntrance();
                    break;
                }
                Menu.alert();
            } catch (Exception ex) {
                ex.printStackTrace();
                Menu.alert();
            }
        } while (true);
    }
}
