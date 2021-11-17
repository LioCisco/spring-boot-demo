package com.eddie.testspring.repository;

import com.eddie.testspring.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Optional;

/**
 * @author Eddie.Liao
 * @description: TODO
 * @date 2021/11/15 5:36 下午
 */
@Repository
public interface UserDao extends JpaRepository<User, Long> , JpaSpecificationExecutor<User> {

    Optional<User> findByUsernameOrEmailOrPhone(String username, String email, String phone);

    List<User> findByUsernameIn(List<String> usernameList);
}
