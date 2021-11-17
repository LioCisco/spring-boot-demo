package com.eddie.testspring.common;

/**
 * @author Eddie.Liao
 * @description: TODO
 * @date 2021/11/17 11:36 上午
 */
public interface Consts {
    Integer ENABLE  = 1;

    Integer DISABLE = 0;

    Integer PAGE  = 1;

    Integer BUTTON = 2;

    String REDIS_JWT_KEY_PREFIX = "security:jwt:";

    String SYMBOL_STAR = "*";

    String SYMBOL_EMAIL = "@";

    Integer DEFAULT_CURRENT_PAGE = 1;

    Integer DEFAULT_PAGE_SIZE = 0 ;

    String ANONYMOUS_NAME = "匿名用户";
}
