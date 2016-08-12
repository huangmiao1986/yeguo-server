package com.yeguo.server.util;

import java.util.Random;

public class RandomNumUtil {
    /* 随机生成4位数字验证码 */
    public static String getValidateNumber() {
        Random random = new Random();
        int number = 0;
        String str = "";
        for (int i = 0; i < 4; i++) {
            number = Math.abs(random.nextInt()) % 10;
            String ctmp = (String.valueOf(number));
            str += ctmp;
        }
        return str;
    }
}
