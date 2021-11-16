package com.eddie.testspring.exception;

import com.eddie.testspring.constant.Status;
import lombok.Getter;

/**
 * @author Eddie.Liao
 * @description: TODO
 * @date 2021/11/15 4:06 下午
 */
@Getter
public class PageException extends BaseException{
    public PageException(Status status){
        super(status);
    }

    public PageException(Integer code,String message){
        super(code,message);
    }

}
