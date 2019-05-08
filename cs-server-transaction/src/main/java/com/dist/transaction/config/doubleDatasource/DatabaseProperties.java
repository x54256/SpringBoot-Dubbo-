package com.dist.transaction.config.doubleDatasource;


import lombok.Data;

@Data
public class DatabaseProperties {

    private String url;

    private String username;

    private String password;

    // xa驱动
    private String type;

    private int minPoolSize;

    private int maxPoolSize;

    private int maxLifetime;

    private int borrowConnectionTimeout;

    private int loginTimeout;

    private int maintenanceInterval;

    private int maxIdleTime;

    private String testQuery;

}