package com.eddie.testspring.exception;

import com.eddie.testspring.constant.Status;
import lombok.Getter;

/**
 * @author Eddie.Liao
 * @description: TODO
 * @date 2021/11/15 4:05 下午
 */

@Getter
public class JsonException extends  BaseException{
    public JsonException(Status status){
        super(status);
    }

    public JsonException(Integer code,String message){
        super(code,message);
    }
}
