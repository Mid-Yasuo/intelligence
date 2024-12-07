package com.einstein.common.util;

import java.security.SecureRandom;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * @author 张春杰
 * @version 1.0.0
 * @date 2023/8/28
 */
public class RandomUtils {

    public static final char[] CHARS = "ABCDEFGHIGHLMNOPQRSTUVWXYZ0123456789abcdefghijklmnopqrstuvwxyz".toCharArray();
    public static final char[] NUM_CHARS = "0123456789".toCharArray();
    public static final DateTimeFormatter YYYY_MM_DD = DateTimeFormatter.ofPattern("yyyyMMdd");

    private static String random(char[] chars, int length) {
        SecureRandom random = new SecureRandom();
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < length; i++) {
            char next = chars[random.nextInt(chars.length)];
            result.append(next);
        }
        return result.toString();
    }

    public static String generateTraceId(){
        return dateRandom(20);
    }

    public static String random(int length) {
        return random(CHARS, length);
    }

    public static String numRandom(int length) {
        return random(NUM_CHARS, length);
    }

    public static String dateRandom(int length) {
        return LocalDate.now().format(YYYY_MM_DD) + random(CHARS, length);
    }

    public static int randomNo(int bound) {
        return new SecureRandom().nextInt(bound);
    }
}
