package vn.triet.pharmacy.online.utils;

import java.util.Random;

public class Captcha {
    public static String createCaptcha() {
        char[] data = {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k',
                'm', 'n', 'p', 'q', '1', '2', '3', '4', '5', '6',
                '7', '8', '9', 'r', 's', 't', 'u', 'v', 'w',
                'x', 'y', 'z', 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I',
                'J', 'K', 'L', 'M', 'N', 'P', 'Q', 'R', 'S', 'T', 'U',
                'V', 'W', 'X', 'Y', 'Z', '1', '2', '3', '4', '5', '6',
                '7', '8', '9'};
        char[] index = new char[6];
        Random r = new Random();
        int i;
        for (i = 0; i < (index.length); i++) {
            int ran = r.nextInt(data.length);
            index[i] = data[ran];
        }
        return new String(index);
    }
}
