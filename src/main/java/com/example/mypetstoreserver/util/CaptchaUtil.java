package com.example.mypetstoreserver.util;

import java.util.Random;

public class CaptchaUtil {
    private static final String CODE_POOL = "0123456789";

    /**
     * 生成四位验证码
     * @return 四位验证码
     */
    public static String getCode() {
        StringBuilder code = new StringBuilder();
        Random random = new Random();
        for (int i = 0; i < 6; i++) {
            code.append(CODE_POOL.charAt(random.nextInt(CODE_POOL.length())));
        }
        return code.toString();
    }

//    public static void main(String[] args) {
//        System.out.println(getCode());
//    }
}
