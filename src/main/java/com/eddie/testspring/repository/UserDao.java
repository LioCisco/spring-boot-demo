package com.eddie.testspring.repository;

import com.eddie.testspring.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author Eddie.Liao
 * @description: TODO
 * @date 2021/11/15 5:36 下午
 */
@Repository
public interface UserDao extends JpaRepository<User, Long> {
}
