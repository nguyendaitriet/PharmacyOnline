package vn.triet.pharmacy.online.services;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class DateValidator {

    public static boolean isValid(String dateStr) {
        DateTimeFormatter dateFormatter = DateTimeFormatter.BASIC_ISO_DATE;
        try {
            LocalDate.parse(convertDate(dateStr), dateFormatter);
        } catch (DateTimeParseException e) {
            return false;
        }
        return true;
    }

    public static String convertDate(String date) {
        //  23/04/2021 -> 20210423
        String[] array = date.split("/");
        String result = "";
        for (int i = array.length - 1; i >= 0; i--) {
            result += array[i];
        }
        return result;
    }


}
