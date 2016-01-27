package com.thetonrifles.recyclerviewsample;

import java.util.Random;

public class Utils {

    public static int buildRandomInt(int limit) {
        Random r = new Random();
        return r.nextInt(limit-1) + 1;
    }

    public static String buildRandomString(int n) {
        char[] chars = "abcdefghijklmnopqrstuvwxyz".toCharArray();
        StringBuilder sb = new StringBuilder();
        Random random = new Random();
        for (int i = 0; i < n; i++) {
            char c = chars[random.nextInt(chars.length)];
            sb.append(c);
        }
        return sb.toString();
    }

    public static String buildRandomName(int n) {
        String name = buildRandomString(n);
        return name.substring(0,1).toUpperCase() + name.substring(1);
    }

}
