package com.study;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * @Description: main_run
 * @Author mengfanzhu
 * @Date 2019/4/28 15:24
 * @Version 1.0
 */
@Slf4j
@SpringBootApplication
@EnableScheduling
public class ApplicationRun extends WebMvcConfigurerAdapter {

    public static void main(String[] args) {
        SpringApplication.run(ApplicationRun.class, args);
    }
}
