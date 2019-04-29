package com.study.druid;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

/**
 * @Description:
 * @Author mengfanzhu
 * @Date 2019/4/28 13:47
 * @Version 1.0
 */
public class DynamicDataSource extends AbstractRoutingDataSource {
    private static final ThreadLocal<DatabaseType> contextHolder = new ThreadLocal<DatabaseType>();

    @Override
    protected Object determineCurrentLookupKey() {
        return contextHolder.get();
    }

    public enum DatabaseType {
        Master, Slave
    }

    public static void master() {
        contextHolder.set(DatabaseType.Master);
    }


    public static void slave() {
        contextHolder.set(DatabaseType.Slave);
    }

    public static void setDatabaseType(DatabaseType type) {
        contextHolder.set(type);
    }

    public static DatabaseType getType() {
        return contextHolder.get();
    }

    public static void cleanAll(){
        contextHolder.remove();
    }
}