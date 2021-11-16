package com.eddie.testspring.service.serviceImpl;

import com.eddie.testspring.bean.UserBean;
import com.eddie.testspring.mapper.UserMapper;
import com.eddie.testspring.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
     UserMapper userMapper;

    @Override
    public UserBean loginIn(String name, String password) {
        return userMapper.getInfo(name,password);
    }

//    @Override
//    public UserBean loginIn(String name, String password) {
//        return userMapper.getInfo(name,password);
//    }
//    @Override
//    public UserBean loginIn(String name, String password) {
//
//        return userMapper.getInfo(name,password);
//    }


}
