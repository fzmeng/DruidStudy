package com.study.druid;

import com.alibaba.druid.pool.DruidDataSource;
import org.springframework.boot.bind.RelaxedPropertyResolver;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

/**
 * @Description:
 * @Author mengfanzhu
 * @Date 2019/4/28 13:48
 * @Version 1.0
 */
@Configuration
@EnableTransactionManagement
public class DataBaseConfiguration  implements EnvironmentAware {

    private RelaxedPropertyResolver propertyResolverMaster;
    private RelaxedPropertyResolver propertyResolverSlave;

    public DataBaseConfiguration(){
        System.out.println("####################  DataBaseConfiguration");
    }

    @Override
    public void setEnvironment(Environment env) {
        this.propertyResolverMaster = new RelaxedPropertyResolver(env, "datasource.business.");
        this.propertyResolverSlave = new RelaxedPropertyResolver(env, "datasource.business_slave.");
    }

    public DataSource master() {
        System.out.println("注入 Master druid！！！");
        DruidDataSource datasource = new DruidDataSource();
        datasource.setUrl(propertyResolverMaster.getProperty("url"));
        datasource.setDriverClassName(propertyResolverMaster.getProperty("driver-class-name"));
        datasource.setUsername(propertyResolverMaster.getProperty("username"));
        datasource.setPassword(propertyResolverMaster.getProperty("password"));
        return datasource;
    }

    public DataSource slave() {
        System.out.println("Slave druid！！！");
        DruidDataSource datasource = new DruidDataSource();
        datasource.setUrl(propertyResolverSlave.getProperty("url"));
        datasource.setDriverClassName(propertyResolverSlave.getProperty("driver-class-name"));
        datasource.setUsername(propertyResolverSlave.getProperty("username"));
        datasource.setPassword(propertyResolverSlave.getProperty("password"));
        return datasource;
    }

    @Bean
    public DynamicDataSource dynamicDataSource() {
        DataSource master = master();
        DataSource slave = slave();
        Map<Object, Object> targetDataSources = new HashMap<Object, Object>();
        targetDataSources.put(DynamicDataSource.DatabaseType.Master, master);
        targetDataSources.put(DynamicDataSource.DatabaseType.Slave, slave);

        DynamicDataSource dataSource = new DynamicDataSource();
        dataSource.setTargetDataSources(targetDataSources);// 该方法是AbstractRoutingDataSource的方法
        dataSource.setDefaultTargetDataSource(master);
        return dataSource;
    }


}
