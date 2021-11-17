package com.eddie.testspring.repository;

import com.eddie.testspring.model.RolePermission;
import com.eddie.testspring.model.unionKey.RolePermissionKey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * @author Eddie.Liao
 * @description: TODO
 * @date 2021/11/17 1:54 下午
 */
public interface RolePermissionDao extends JpaRepository<RolePermission, RolePermissionKey> , JpaSpecificationExecutor<RolePermission> {

}
