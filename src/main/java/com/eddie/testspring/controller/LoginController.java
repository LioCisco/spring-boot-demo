package com.eddie.testspring.controller;

import com.eddie.testspring.annotation.RateLimiter;
import com.eddie.testspring.bean.UserBean;
import com.eddie.testspring.service.UserService;
import io.swagger.annotations.ApiModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class LoginController {
    @Autowired
    UserService userService;


    @RateLimiter(value = 2, key = "testKey")
    @RequestMapping("/show")
    public String show(){

        return "login";
    }

    @RateLimiter(value = 2, key = "testKey1")
    @RequestMapping(value = "/login",method = RequestMethod.POST)
    public String login(String name,String password)
    {
        UserBean userBean = userService.loginIn(name,password);
        if(userBean != null ){
            return "success";

        }else{
            return "fail";
        }
    }
}
