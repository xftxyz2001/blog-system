package com.xftxyz.blogsystem.controller.utils;

import java.util.Random;
import java.util.regex.Pattern;

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

    private static final String[] ILLEGAL_WORDS = { "你妈", "傻逼", "煞笔", "沙比", "骚逼", "烧杯", "尼玛", "sb", "tmd" };

    private static final Pattern PATTERN = Pattern.compile("\\b(" + String.join("|", ILLEGAL_WORDS) + ")\\b",
            Pattern.CASE_INSENSITIVE);

    public static boolean containSensitiveWords(String name) {
        return PATTERN.matcher(name).find();
    }

    public static String maskSensitiveWords(String text) {
        for (String word : ILLEGAL_WORDS) {
            String regex = "(?i)\\b" + Pattern.quote(word) + "\\b"; // 忽略大小写，匹配单词边界
            text = text.replaceAll(regex, "**");
        }
        return text;
    }

}
