package com.example.demo.exception;

/**
 * @ProjectName demo
 * @ClassName ArticleException
 * @Description TODO
 * @Author Lyn
 * @Date 2020/12/22 1:40
 * @Version 1.0
 * @Function 自定义异常类(关于新闻内容)
 */

public class ArticleException extends Exception {

    public ArticleException() {
        super();
    }

    public ArticleException(String message) {
        super(message);
    }
}
