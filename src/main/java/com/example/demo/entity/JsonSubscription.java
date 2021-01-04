package com.example.demo.entity;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

/**
 * @ProjectName demo
 * @ClassName JsonSubscription
 * @Description TODO
 * @Author Lyn
 * @Date 2020/12/21 20:27
 * @Version 1.0
 * @Function
 */

public class JsonSubscription {

    @NotEmpty(message = "type不能为空")
    private String type;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
