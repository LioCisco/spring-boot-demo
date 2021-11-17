package com.eddie.testspring.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Eddie.Liao
 * @description: TODO
 * @date 2021/11/17 5:13 下午
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class JwtResponse {
    /**
     * token 字段
     */
    private String token;
    /**
     * token类型
     */
    private String tokenType = "Bearer";

    public JwtResponse(String token) {
        this.token = token;
    }
}
