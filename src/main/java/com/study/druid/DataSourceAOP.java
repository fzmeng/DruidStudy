package com.study.druid;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

/**
 * @Description:
 * @Author mengfanzhu
 * @Date 2019/4/28 14:08
 * @Version 1.0
 */

@Aspect
@Component
@Slf4j
public class DataSourceAOP {

    @Before("(@annotation(com.study.druid.Master)" +
            "&& !@annotation(com.study.druid.Slave)" +
            "||  execution(* com.study.service..*.update*(..))" +
            "||  execution(* com.study.service..*.save*(..))" +
            "||  execution(* com.study.service..*.insert*(..))" +
            "||  execution(* com.study.service..*.delete*(..))" +
            ")")
    public void setWriteDataSourceType() {
        DynamicDataSource.master();
        log.info("dataSource切换到：master");
    }

    @Before("(@annotation(com.study.druid.Slave)" +
            "&& !@annotation(com.study.druid.Master)" +
            "||  execution(* com.study.service..*.query*(..))" +
            "||  execution(* com.study.service..*.get*(..))" +
            ")")
    public void setReadDataSourceType() {
        DynamicDataSource.slave();
        log.info("dataSource切换到：slave");
    }

   @After("(" +
           "execution(* com.study.service..*.query*(..))" +
           "|| execution(* com.study.service..*.get*(..))" +
           "|| execution(* com.study.service..*.update*(..))" +
           "|| execution(* com.study.service..*.insert*(..))" +
           "|| execution(* com.study.service..*.save*(..))" +
           "|| execution(* com.study.service..*.delete*(..))" +
           "|| @annotation(com.study.druid.Slave)" +
           "|| @annotation(com.study.druid.Master)" +
           ")")
    public void clean() {
        DynamicDataSource.cleanAll();
        log.info("======dataSource cleanAll======");
    }


}