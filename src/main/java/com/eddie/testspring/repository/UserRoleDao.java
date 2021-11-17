package com.eddie.testspring.repository;

import com.eddie.testspring.model.UserRole;
import com.eddie.testspring.model.unionKey.UserRoleKey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * @author Eddie.Liao
 * @description: TODO
 * @date 2021/11/17 1:40 下午
 */
public interface UserRoleDao extends JpaRepository<UserRole, UserRoleKey> , JpaSpecificationExecutor<UserRole> {

}
