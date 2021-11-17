package com.eddie.testspring.model.unionKey;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.EmbeddedId;
import java.io.Serializable;

/**
 * @author Eddie.Liao
 * @description: TODO
 * @date 2021/11/17 1:52 下午
 */
@Data
@Embeddable
public class UserRoleKey implements Serializable {
    @Column(name = "role_id")
    private Long roleId;

    @Column(name = "user_id")
    private Long userId;
}
