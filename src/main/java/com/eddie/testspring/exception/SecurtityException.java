package com.eddie.testspring.exception;

import com.eddie.testspring.constant.Status;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author Eddie.Liao
 * @description: TODO
 * @date 2021/11/17 5:04 下午
 */
@EqualsAndHashCode(callSuper = true)
public class SecurtityException extends BaseException{
    public SecurtityException(Status status){
        super(status);
    }

//    public SecurityException(Status status, Object data) {
//        super(status, data);
//    }
//
//    public SecurityException(Integer code, String message) {
//        super(code, message);
//    }
//
//    public SecurityException(Integer code, String message, Object data) {
//        super(code, message, data);
//    }
}
