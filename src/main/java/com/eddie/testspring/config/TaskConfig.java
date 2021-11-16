package com.eddie.testspring.config;

import org.apache.commons.lang3.concurrent.BasicThreadFactory;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.task.TaskExecutor;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.SchedulingConfigurer;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;

import java.util.concurrent.Executor;
import java.util.concurrent.ScheduledThreadPoolExecutor;

/**
 * @author Eddie.Liao
 * @description: TODO
 * @date 2021/11/16 4:34 下午
 */
@Configuration
@EnableScheduling
@ComponentScan(basePackages = {"com.eddie.testspring.job"})
public class TaskConfig implements SchedulingConfigurer {

    @Override
    public void configureTasks(ScheduledTaskRegistrar taskRegistrar) {
        taskRegistrar.setScheduler(taskExecutor());
    }

    public Executor taskExecutor(){
        return new ScheduledThreadPoolExecutor(20
                ,new BasicThreadFactory.Builder().namingPattern("Job-Thread-%d").build());
    }
}
