package com.eddie.testspring.mapper;

import com.eddie.testspring.bean.UserBean;
import com.eddie.testspring.entity.UserBackend;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;
import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.MySqlMapper;

import java.util.List;

@Component
public interface UserMapper extends Mapper<UserBackend>, MySqlMapper<UserBackend> {
    UserBean getInfo(String name , String password);

    @Select("SELECT * FROM orm_user")
    List<UserBackend> selectAllUser();

    @Select("SELECT * FROM orm_user where id = #{id}")
    UserBackend selectUserById(@Param("id") Long id);

    int saveUser(@Param("user") UserBackend user);

    int deleteById(@Param("id") Long id);
}
