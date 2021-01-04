package com.example.demo.exception;

/**
 * @ProjectName demo
 * @ClassName EmailException
 * @Description TODO
 * @Author Lyn
 * @Date 2020/12/22 1:10
 * @Version 1.0
 * @Function 自定义异常类(关于自动发邮件)
 */

public class EmailException extends Exception{

    public EmailException() {
        super();
    }

    public EmailException(String message) {
        super(message);
    }
}
