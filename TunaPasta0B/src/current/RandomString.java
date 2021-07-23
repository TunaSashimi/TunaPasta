package current;

import java.util.Random;

/**
 * @author Tunasashimi
 * @date 2018/9/13 16:15
 * @Copyright 2018 TunaSashimi. All rights reserved.
 * @Description
 */
public class RandomString {
    public static void main(String[] args) {
        System.out.println(getRandomString(16));
    }

    public static String getRandomString(int length) {
        String str = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        Random random = new Random();
        StringBuffer sb = new StringBuffer();

        for (int i = 0; i < length; ++i) {
            sb.append(str.charAt(random.nextInt(62)));
        }
        return sb.toString();
    }
}
