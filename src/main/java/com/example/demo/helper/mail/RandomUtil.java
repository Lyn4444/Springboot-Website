package com.example.demo.helper.mail;

/**
 * @ProjectName demo
 * @ClassName RandomUtil
 * @Description TODO
 * @Author Lyn
 * @Date 2020/12/9 0:27
 * @Version 1.0
 * @Function
 */

public class RandomUtil {

    /**
     * 生成6位验证码
     *
     * @return {@link String}
     */
    public synchronized static String getCode() {
        StringBuffer code = new StringBuffer();
        int num;
        for (int i = 0; i < 6; i++) {
            num = (int) (Math.random() * 10);
            code.append(String.valueOf(num));
        }
        return code.toString();
    }
}
