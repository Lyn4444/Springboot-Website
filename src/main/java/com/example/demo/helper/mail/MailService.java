package com.example.demo.helper.mail;

import java.io.IOException;

/**
 * @ProjectName demo
 * @ClassName MailService
 * @Description TODO
 * @Author Lyn
 * @Date 2020/12/9 22:14
 * @Version 1.0
 * @Function
 */

public class MailService {

    /**
     * 发送邮件
     *
     * @param email        电子邮件
     * @param Verification 验证
     */
    public static void sendMail(String email, String Verification) throws IOException {

        String MailTitle = "注册验证码";
        String Content = "你的验证码是：" + Verification + ",请在2分钟内进行验证";
        MailUtil mail = new MailUtil();
        mail.sendMail(email, MailTitle, Content);
    }

}
