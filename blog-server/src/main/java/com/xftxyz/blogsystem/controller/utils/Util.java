package com.xftxyz.blogsystem.controller.utils;

import java.util.Random;

/**
 * 工具类
 */
public class Util {
    public static final String SESSION_USER = "user";
    public static final String PASSWORD_MASK = "******";
    
    public static String getCode() {
        Random r = new Random();
        int randomNum = r.nextInt(9) + 1;
        int temp = 0;
        for (int i = 0; i < 6; i++) {
            temp = temp * 10 + randomNum;
            randomNum = r.nextInt(9) + 1;
        }
        return String.valueOf(temp);
    }
}
