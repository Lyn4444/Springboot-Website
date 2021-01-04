package com.example.demo.exception;

/**
 * @ProjectName demo
 * @ClassName DecryptException
 * @Description TODO
 * @Author Lyn
 * @Date 2020/12/22 1:26
 * @Version 1.0
 * @Function 自定义异常类(关于传输传输解密)
 */

public class DecryptException extends Exception{

    public DecryptException() {
        super();
    }

    public DecryptException(String message) {
        super(message);
    }
}
