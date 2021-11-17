package com.eddie.testspring.model;

import com.eddie.testspring.model.unionKey.RolePermissionKey;
import lombok.Data;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * @author Eddie.Liao
 * @description: TODO
 * @date 2021/11/17 1:48 下午
 */
@Data
@Entity
@Table(name = "sec_role_permission")
public class RolePermission {

    @EmbeddedId
    private RolePermissionKey id;
}
