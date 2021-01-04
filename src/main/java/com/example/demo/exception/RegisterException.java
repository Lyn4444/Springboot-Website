package com.example.demo.exception;

/**
 * @ProjectName demo
 * @ClassName RegisterException
 * @Description TODO
 * @Author Lyn
 * @Date 2020/12/22 1:30
 * @Version 1.0
 * @Function 自定义异常类(关于注册)
 */

public class RegisterException extends Exception{

    public RegisterException() {
        super();
    }

    public RegisterException(String message) {
        super(message);
    }
}
