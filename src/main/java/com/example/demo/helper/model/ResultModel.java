package com.example.demo.helper.model;


/**
 * @ProjectName demo
 * @ClassName ResultModel
 * @Description TODO
 * @Author Lyn
 * @Date 2020/12/8 15:42
 * @Version 1.0
 * @Function
 */

public class ResultModel<T> {

    private int statecode;

    private String msg;

    private T data;

    public int getStatecode() {
        return statecode;
    }

    public void setStatecode(int statecode) {
        this.statecode = statecode;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    private ResultModel() {

    }

    public static ResultModel error(int statecode, String msg) {
        ResultModel resultModel = new ResultModel();
        resultModel.setStatecode(statecode);
        resultModel.setMsg(msg);
        return resultModel;
    }

    public static ResultModel success() {
        ResultModel resultModel = new ResultModel();
        resultModel.setStatecode(200);
        resultModel.setMsg("success");
        return resultModel;
    }

    public ResultModel data(T data) {
        this.setData(data);
        return this;
    }

}
