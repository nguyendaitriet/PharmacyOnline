package vn.triet.pharmacy.online.views;

import java.util.ArrayList;

public class GuestView{
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
                char character = Menu.chooseAction();
                if (character == '1') {

                    break;
                }
                if (character == '2') {

                    break;
                }
                if (character == '3') {

                    break;
                }
                if (character == '0') {
                    Menu.chooseInEntrance();
                    break;
                }
                Menu.alert();
            } catch (Exception io) {
                Menu.alert();
            }
        } while (true);
    }
}
