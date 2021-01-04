package com.example.demo.helper.mail;

import java.io.IOException;

/**
 * @ProjectName demo
 * @ClassName Mail
 * @Description TODO
 * @Author Lyn
 * @Date 2020/12/9 20:22
 * @Version 1.0
 * @Function
 */

public class MailUtil {

    private String ServerIP = "smtp.qq.com";
    private String ServerPort = "25";
    private String SendAddr = "3077002706@qq.com";
    private String authCode = "ylvaflbdsoqsdcdi";
    private TCPMailClient tcpMailClient;

    /**
     * 发送邮件
     *
     * @param RcieveAddr rcieve addr
     * @param MailTitle  邮件标题
     * @param Content    内容
     */
    public void sendMail(String RcieveAddr, String MailTitle, String Content) throws IOException {
        tcpMailClient = new TCPMailClient(ServerIP, ServerPort);
        new Thread(() -> {
            tcpMailClient.send("HELO myfriend");
            tcpMailClient.send("AUTH LOGIN");
            String msg = new sun.misc.BASE64Encoder().encode(SendAddr.getBytes());
            tcpMailClient.send(msg);
            msg = new sun.misc.BASE64Encoder().encode(authCode.getBytes());
            tcpMailClient.send(msg);
            msg = "MAIL FROM:<" + SendAddr + ">";
            tcpMailClient.send(msg);
            msg = "RCPT TO:<" + RcieveAddr + ">";
            tcpMailClient.send(msg);
            msg = "DATA";
            tcpMailClient.send(msg);
            msg = "FROM:" + SendAddr;
            tcpMailClient.send(msg);
            msg = "Subject:" + MailTitle;
            tcpMailClient.send(msg);
            msg = "To:" + SendAddr;
            tcpMailClient.send(msg);
            msg = "\n";
            tcpMailClient.send(msg);
            msg = Content;
            tcpMailClient.send(msg);
            msg = ".";
            tcpMailClient.send(msg);
            msg = "QUIT";
            tcpMailClient.send(msg);
        }).start();
    }

}
