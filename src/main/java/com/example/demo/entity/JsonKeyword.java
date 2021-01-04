package com.example.demo.entity;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

/**
 * @ProjectName demo
 * @ClassName JsonKeyword
 * @Description TODO
 * @Author Lyn
 * @Date 2020/12/21 20:00
 * @Version 1.0
 * @Function
 */

public class JsonKeyword {

    @NotEmpty(message = "keyWord不能为空")
    private String keyWord;

    public String getKeyWord() {
        return keyWord;
    }

    public void setKeyWord(String keyWord) {
        this.keyWord = keyWord;
    }
}
