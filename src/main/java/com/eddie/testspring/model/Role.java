package com.eddie.testspring.model;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author Eddie.Liao
 * @description: TODO
 * @date 2021/11/17 1:42 下午
 */
@Entity
@Data
@Table(name="sec_role")
public class Role {
    @Id
    private  Long id;

    private String name;

    private String description;

    @Column(name ="create_time")
    private Long createTime;

    @Column(name ="update_time")
    private Long updateTime;

}
