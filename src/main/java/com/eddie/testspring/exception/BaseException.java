package com.eddie.testspring.exception;

import com.eddie.testspring.constant.Status;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author Eddie.Liao
 * @description: TODO
 * @date 2021/11/15 3:57 下午
 */

@Data
@EqualsAndHashCode
public class BaseException extends  RuntimeException{
    private Integer code;
    private String message;

    public BaseException(Status status){
        super(status.getMessage());
        this.code = status.getCode();
        this.message = status.getMessage();

    }

    public BaseException(Integer code, String message){
        super(message);
        this.code = code;
        this.message = message;
    }
}
