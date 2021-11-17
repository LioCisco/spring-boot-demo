package com.eddie.testspring.model;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author Eddie.Liao
 * @description: TODO
 * @date 2021/11/17 1:44 下午
 */
@Entity
@Data
@Table(name= "sec_permission")
public class Permission {
    @Id
    private Long id;

    private String name;

    private String url;

    private Integer type;

    private String permission;

    private String method;

    private Integer sort;

    @Column(name = "parent_id")
    private Long parentId;
}
