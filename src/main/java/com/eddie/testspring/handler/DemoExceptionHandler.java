package com.eddie.testspring.handler;

import cn.hutool.poi.excel.ExcelPicUtil;
import com.eddie.testspring.exception.JsonException;
import com.eddie.testspring.exception.PageException;
import com.eddie.testspring.model.ApiResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author Eddie.Liao
 * @description: TODO
 * @date 2021/11/15 4:20 下午
 */
@Slf4j
public class DemoExceptionHandler {
    public static final String DEFAULT_ERROR_VIEW = "error";

    @ExceptionHandler(value = JsonException.class)
    @ResponseBody
    public ApiResponse jsonErrorHandler(JsonException exception){
        log.error("【JsonException】: {}",exception.getMessage());
        return ApiResponse.ofException(exception);
    }

    @ExceptionHandler(value = PageException.class)
    public ModelAndView pageExceptionHandler(PageException exception){
        log.error("【DemoPageException】:{}",exception.getMessage());
        ModelAndView  view = new ModelAndView();
        view.addObject("message", exception.getMessage());
        view.setViewName(DEFAULT_ERROR_VIEW);
        return view;
    }
}
