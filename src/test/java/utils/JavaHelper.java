package utils;

//A helper class with commonly used methods, such as generating random data.

import java.util.Random;

public class JavaHelper {

    // Generate random string
    public static String generateRandomString(int length) {
        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        StringBuilder result = new StringBuilder();
        Random random = new Random();
        for (int i = 0; i < length; i++) {
            result.append(characters.charAt(random.nextInt(characters.length())));
        }
        return result.toString();
    }

    // Generate random number
    public static int generateRandomNumber(int bound) {
        Random random = new Random();
        return random.nextInt(bound);
    }
}
