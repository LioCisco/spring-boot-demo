package com.eddie.testspring.model;

import com.eddie.testspring.model.unionKey.UserRoleKey;
import lombok.Data;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * @author Eddie.Liao
 * @description: TODO
 * @date 2021/11/17 1:49 下午
 */
@Data
@Entity
@Table(name = "sec_user_role")
public class UserRole {

    @EmbeddedId
    private UserRoleKey id;
}
