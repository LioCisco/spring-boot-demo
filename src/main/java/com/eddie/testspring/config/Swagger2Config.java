package com.eddie.testspring.config;

import com.sun.deploy.ui.AppInfo;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @author Eddie.Liao
 * @description: TODO
 * @date 2021/11/16 5:14 下午
 */
@Configuration
@EnableSwagger2
public class Swagger2Config {

    public Docket createRestApi(){
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.eddie.testspring.controller"))
                .paths(PathSelectors.any()).build();
    }

    public ApiInfo apiInfo(){
        return new ApiInfoBuilder()
                .title("spring-boot-demo")
                .description("这是一个简单的 Swagger API 演示")
                .contact(new Contact("Eddie.liao","xxx","eddie.liao@163.com"))
                .version("1.0.0-SNAPSHOT")
                .build();
    }
}
