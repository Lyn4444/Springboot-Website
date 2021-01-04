package com.example.demo.exception;

/**
 * @ProjectName demo
 * @ClassName RangeException
 * @Description TODO
 * @Author Lyn
 * @Date 2020/12/22 0:31
 * @Version 1.0
 * @Function 自定义异常类(关于订阅)
 */

public class RangeException extends Exception {

    public RangeException() {
        super();
    }

    public RangeException(String message) {
        super(message);
    }
}
