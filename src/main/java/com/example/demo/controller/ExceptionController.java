package com.example.demo.controller;

import com.example.demo.exception.*;
import com.example.demo.helper.model.ResultModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.validation.BindingResult;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;
import java.util.Objects;

/**
 * @ProjectName demo
 * @ClassName ExceptionController
 * @Description TODO
 * @Author Lyn
 * @Date 2020/12/21 21:18
 * @Version 1.0
 * @Function 统一异常处理
 */

@ControllerAdvice
public class ExceptionController {

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    @ResponseBody
    public ResultModel MethodArgumentNotValidExceptionHandler(MethodArgumentNotValidException e) {
        logger.error("发生一个异常：" + e);
        BindingResult result = e.getBindingResult();
        Objects.requireNonNull(result.getFieldError());
        return ResultModel.error(
                201,
                result.getFieldError().getField() + " " +
                        result.getFieldError().getDefaultMessage()
        );
    }

    @ExceptionHandler(value = DataIntegrityViolationException.class)
    @ResponseBody
    public ResultModel DataIntegrityViolationExceptionHandler(DataIntegrityViolationException e) {
        logger.error("发生一个异常：" + e);
        return ResultModel.error(201, "没有session传入参数");
    }

    @ExceptionHandler(value = RangeException.class)
    @ResponseBody
    public ResultModel RangeExceptionHandler(RangeException e) {
        logger.error("发生一个异常：" + e);
        return ResultModel.error(201, e.getMessage());
    }

    @ExceptionHandler(value = NullPointerException.class)
    @ResponseBody
    public ResultModel SessionNullExceptionHandler(NullPointerException e) {
        logger.error("发生一个异常：" + e);
        return ResultModel.error(201, "没有登录成功");
    }

    @ExceptionHandler(value = LoginException.class)
    @ResponseBody
    public ResultModel LoginExceptionHandler(LoginException e) {
        logger.error("发生一个异常：" + e);
        return ResultModel.error(201, e.getMessage());
    }

    @ExceptionHandler(value = EmailException.class)
    @ResponseBody
    public ResultModel EmailExceptionHandler(EmailException e) {
        logger.error("发生一个异常：" + e);
        return ResultModel.error(201, e.getMessage());
    }

    @ExceptionHandler(value = DecryptException.class)
    @ResponseBody
    public ResultModel DecryptExceptionHandler(DecryptException e) {
        logger.error("发生一个异常：" + e);
        return ResultModel.error(201, e.getMessage());
    }

    @ExceptionHandler(value = IOException.class)
    @ResponseBody
    public ResultModel IOExceptionHandler(IOException e) {
        logger.error("发生一个异常：" + e);
        return ResultModel.error(201, e.getMessage());
    }

    @ExceptionHandler(value = RegisterException.class)
    @ResponseBody
    public ResultModel RegisterExceptionHandler(RegisterException e) {
        logger.error("发生一个异常：" + e);
        return ResultModel.error(201, e.getMessage());
    }

    @ExceptionHandler(value = HttpRequestMethodNotSupportedException.class)
    @ResponseBody
    public ResultModel HttpRequestMethodNotSupportedExceptionHandler(HttpRequestMethodNotSupportedException e) {
        logger.error("发生一个异常：" + e);
        return ResultModel.error(201, e.getMessage());
    }

    @ExceptionHandler(value = ArticleException.class)
    @ResponseBody
    public ResultModel ArticleExceptionHandler(ArticleException e) {
        logger.error("发生一个异常：" + e);
        return ResultModel.error(201, e.getMessage());
    }

    @ExceptionHandler(value = NumberFormatException.class)
    @ResponseBody
    public ResultModel NumberFormatExceptionHandler(NumberFormatException e) {
        logger.error("发生一个异常：" + e);
        return ResultModel.error(201, "输入参数不正确：" + e.getMessage());
    }

    @ExceptionHandler(value = ArticleAttitudeException.class)
    @ResponseBody
    public ResultModel ArticleAttitudeExceptionHandler(ArticleAttitudeException e) {
        logger.error("发生一个异常：" + e);
        return ResultModel.error(201, "输入参数不正确：" + e.getMessage());
    }



}
