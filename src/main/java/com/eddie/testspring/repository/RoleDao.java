package com.eddie.testspring.repository;

import com.eddie.testspring.model.Role;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * @author Eddie.Liao
 * @description: TODO
 * @date 2021/11/17 1:40 下午
 */
public interface RoleDao extends JpaRepository<Role,Long>, JpaSpecificationExecutor<Role> {

    @Query(value = "select sec_role.* from sec_role,sec_user,sec_user_role " +
            "where sec_user.id =sec_user_role.user_id" +
            "and sec_role.id = sec_user_role.role.id" +
            "and sec_user.id = :userId",nativeQuery = true)
    List<Role> selectByUserId(@Param("userId") Long userID);
}
