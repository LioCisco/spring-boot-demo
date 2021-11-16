package com.eddie.testspring.mapper;

import com.eddie.testspring.bean.UserBean;
import com.eddie.testspring.entity.User;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.MySqlMapper;

import java.util.List;

@Component
public interface UserMapper extends Mapper<User>, MySqlMapper<User> {
    UserBean getInfo(String name , String password);

    @Select("SELECT * FROM orm_user")
    List<User> selectAllUser();

    @Select("SELECT * FROM orm_user where id = #{id}")
    User selectUserById(@Param("id") Long id);

    int saveUser(@Param("user") User user);

    int deleteById(@Param("id") Long id);
}
