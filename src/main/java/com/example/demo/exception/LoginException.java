package com.example.demo.exception;

/**
 * @ProjectName demo
 * @ClassName LoginException
 * @Description TODO
 * @Author Lyn
 * @Date 2020/12/22 0:52
 * @Version 1.0
 * @Function 自定义异常类(关于登录)
 */

public class LoginException extends Exception{

    public LoginException() {
        super();
    }

    public LoginException(String message) {
        super(message);
    }
}
