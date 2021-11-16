package com.eddie.testspring.controller;

import com.eddie.testspring.entity.User;
import com.eddie.testspring.mapper.UserMapper;
import com.eddie.testspring.model.ApiResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.criterion.Example;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.xml.ws.RequestWrapper;
import java.io.Serializable;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.IntStream;

/**
 * @author Eddie.Liao
 * @description: TODO
 * @date 2021/11/16 10:09 上午
 */
@Controller
@Slf4j
public class UserController {

    @Autowired
    private  StringRedisTemplate stringRedisTemplate;

    @Autowired
    private RedisTemplate<String, Serializable> redisCacheTemplate;

    @Autowired
    private UserMapper userMapper;

    @RequestMapping(value = "selectAllUser" )
    @ResponseBody
    public String selectAllUser(){
        List<User> userList = userMapper.selectAllUser();
        log.info("userList {}" ,userList);
        return  "selectAllUser";

    }

    @RequestMapping(value = "selectUserByPage")
    public void queryUsersByPageAndSort(){
        int currentPage = 1;
        int pageSize = 1;
        String orderBy = "id desc";
        int count = userMapper.selectCount(null);
        PageHelper.startPage(currentPage,pageSize,orderBy);
        List<User> users = userMapper.selectAll();
        PageInfo<User> userPageInfo = new PageInfo<>(users);
        log.info("userListPage {}",userPageInfo);
    }
    @RequestMapping(value = "redisGet")
    public void redisGet(){
        ExecutorService executorService = Executors.newFixedThreadPool(1);
        IntStream.range(0,1000)
                .forEach(i -> executorService.execute(
                        () -> stringRedisTemplate.opsForValue().increment("count",1))
                );

        stringRedisTemplate.opsForValue().set("k1","v1");
        String k1 = stringRedisTemplate.opsForValue().get("k1");
        log.info("[k1] = {}",k1);

//        String key = "xkcoding:user:1";
//
//        redisCacheTemplate.opsForValue().set(key,new User());
//
//        User user = (User) redisCacheTemplate.opsForValue().get(key);
//        log.info("[user] = {}",user);

    }

}
