package com.eddie.testspring.controller;


import com.eddie.testspring.constant.Status;
import com.eddie.testspring.exception.PageException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
@Slf4j
class HelloController {

    @RequestMapping("/index")
    @ResponseBody
    public String sayHello(){

        try {
            log.error("test jrebel");
            log.debug("test jrebel");
            log.info("test jrebel");
            log.warn("test jrebel");
          //  throw new PageException(Status.UNKNOWN_ERROR);
        } catch (PageException e) {
            e.printStackTrace();
        } finally {
        }
        return "index";
    }
}