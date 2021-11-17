package com.eddie.testspring.repository;

import com.eddie.testspring.model.Permission;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * @author Eddie.Liao
 * @description: TODO
 * @date 2021/11/17 1:56 下午
 */
public interface PermissionDao extends JpaRepository<Permission,Long> , JpaSpecificationExecutor<PermissionDao> {
    @Query(value = "select distinct " +
            "sec_permission.* " +
            "from sec_permission,sec_role,sec_role_permission " +
            "where sec_role.id=sec_role_permission.role_id " +
            "and sec_permission.id= sec_role_permission.permission_id " +
            "and sec_role.id " +
            "IN (:ids)"
            ,nativeQuery = true)
    List<Permission> selectByRoleIdList(@Param("ids") List<Long> ids);
}
