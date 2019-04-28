package com.study.druid;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

/**
 * @Description:
 * @Author mengfanzhu
 * @Date 3/21/18 20:23
 * @Version 1.0
 */
@Configuration
@MapperScan(basePackages = "com.study.mapper", sqlSessionTemplateRef  = "businessSqlSessionTemplate")
public class DBBusinessConfig {

    @Bean(name = "businessSqlSessionFactory")
    public SqlSessionFactory managerSqlSessionFactory(DynamicDataSource dataSource) throws Exception {
        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
        bean.setDataSource(dataSource);
        bean.setTypeAliasesPackage("com.study.model");
        bean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath:mapper/*Mapper.xml"));
        return bean.getObject();
    }

    @Bean(name = "businessTransactionManager")
    public DataSourceTransactionManager managerTransactionManager( DynamicDataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }

    @Bean(name = "businessSqlSessionTemplate")
    public SqlSessionTemplate managerSqlSessionTemplate(@Qualifier("businessSqlSessionFactory") SqlSessionFactory sqlSessionFactory) throws Exception {
        return new SqlSessionTemplate(sqlSessionFactory);
    }

}
