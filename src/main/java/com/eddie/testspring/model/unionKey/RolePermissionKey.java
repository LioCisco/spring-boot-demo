package com.eddie.testspring.model.unionKey;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

/**
 * @author Eddie.Liao
 * @description: TODO
 * @date 2021/11/17 1:50 下午
 */
@Data
@Embeddable
public class RolePermissionKey implements Serializable {
    private static final long  serialVersionUID = 6850974328279713855L;

    @Column(name ="role_id")
    private Long roleId;

    @Column(name = "permission_id")
    private Long permissionId;
}
