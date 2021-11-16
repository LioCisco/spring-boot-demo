package com.eddie.testspring.controller;

import cn.hutool.json.JSONArray;
import com.eddie.testspring.entity.Department;
import com.eddie.testspring.model.ApiResponse;
import com.eddie.testspring.repository.DepartmentDao;
import com.eddie.testspring.repository.UserDao;
import com.fasterxml.jackson.databind.jsonFormatVisitors.JsonArrayFormatVisitor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Collection;
import java.util.List;

/**
 * @author Eddie.Liao
 * @description: TODO
 * @date 2021/11/15 8:18 下午
 */
@Slf4j
@Controller
public class DepartmentController {

    @Autowired
    private DepartmentDao departmentDao;

    @Autowired
    private UserDao userDao;


    @GetMapping("/save")
    public ApiResponse save(){
        Collection<Department> departmentList = departmentDao.findDepartmentsByLevels(0);
        if(departmentList.size() == 0 ){
            Department testSave1 = Department.builder().name("testSave1").orderNo(0).levels(0).superior(null).build();
            Department testSave1_1 = Department.builder().name("testSave1_1").orderNo(0).levels(1).superior(testSave1).build();
            Department testSave1_1_1 = Department.builder().name("testSave1_1_1").orderNo(0).levels(2).superior(testSave1_1).build();
            Department testSave1_2 = Department.builder().name("testSave1_2").orderNo(0).levels(1).superior(testSave1).build();

            departmentList.add(testSave1);
            departmentList.add(testSave1_1);
            departmentList.add(testSave1_1_1);
            departmentList.add(testSave1_2);
            departmentDao.saveAll(departmentList);


            Collection<Department> deptall = departmentDao.findAll();
            return  new ApiResponse(200,"添加成功",deptall);

        }
        return null;
    }

}
