package com.eddie.testspring.common;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

/**
 * @author Eddie.Liao
 * @description: TODO
 * @date 2021/11/17 11:32 上午
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PageResult<T> implements Serializable {

    public static final Long seriaVersionUID = 3420391142991247367L;

    private List<T> rows;

    private Long total;

    public static <T> PageResult of(List<T> rows, Long total){
        return new PageResult<>(rows, total);
    }
}
