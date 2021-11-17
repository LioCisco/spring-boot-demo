package com.eddie.testspring.model;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author Eddie.Liao
 * @description: TODO
 * @date 2021/11/17 1:46 下午
 */
@Entity
@Data
@Table(name = "sec_user")
public class User {
    @Id
    private Long id;

    private String username;

    private String password;

    private String nickname;

    private String phone;

    private String email;

    private Long birthday;

    private Integer sex;

    private Integer status;

    @Column(name = "create_time")
    private Long createTime;

    @Column(name = "update_time")
    private Long updateTime;
}
