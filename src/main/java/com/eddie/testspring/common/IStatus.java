package com.eddie.testspring.common;

/**
 * @author Eddie.Liao
 * @description: TODO
 * @date 2021/11/17 4:26 下午
 */
public interface IStatus {
    /**
     * 状态码
     *
     * @return 状态码
     */
    Integer getCode();

    /**
     * 返回信息
     *
     * @return 返回信息
     */
    String getMessage();
}
