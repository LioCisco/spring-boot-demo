package com.eddie.testspring.service;

import com.eddie.testspring.bean.UserBean;

public interface UserService {
    UserBean loginIn(String name,String password);
}
