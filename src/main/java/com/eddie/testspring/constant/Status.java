package com.eddie.testspring.constant;

import lombok.Getter;

/**
 * @author Eddie.Liao
 * @description: TODO
 * @date 2021/11/15 3:59 下午
 */

@Getter
public enum Status {

    OK(200,"操作成功"),

    UNKNOWN_ERROR(500,"服务器出错");

    private Integer code;

    private String message;

    Status(Integer code,String message){
        this.code = code;
        this.message = message;
    }
}
