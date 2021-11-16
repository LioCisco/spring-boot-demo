package com.eddie.testspring.entity;

import com.eddie.testspring.entity.base.AbstractAuditModel;
import lombok.*;

import javax.persistence.*;
import java.util.Collection;

/**
 * @author Eddie.Liao
 * @description: TODO
 * @date 2021/11/15 5:40 下午
 */
@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "orm_department")
@ToString(callSuper = true)
public class Department extends AbstractAuditModel {

    @Column(name = "name",columnDefinition = "varchar(255) not null")
    private String name;

    @ManyToOne(cascade = {CascadeType.REFRESH},optional = true)
    @JoinColumn(name = "superior",referencedColumnName = "id")
    private Department superior;

    @Column(name = "levels",columnDefinition = "int not null default 0")
    private Integer levels;

    @Column(name = "order_no",columnDefinition = "int not null default 0")
    private Integer orderNo;

    @OneToMany(cascade = {CascadeType.REFRESH,CascadeType.REMOVE},fetch = FetchType.EAGER,mappedBy="superior")
    private Collection<Department> children;

    @ManyToMany(mappedBy = "departmentList")
    private Collection<User> userList;


}
