package com.dist.transaction.config.doubleDatasource;


import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jta.atomikos.AtomikosDataSourceBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.context.annotation.Primary;

import javax.sql.DataSource;
import java.util.Properties;

/**
 * @author yujx
 * @date 2019/1/10 上午10:21
 */
@Configuration
public class DataSourceConfig {

    @Primary
    @Bean(name = "primaryDatasourceProperties")
    @ConfigurationProperties(prefix = "spring.datasource.druid.primary")
    public DatabaseProperties getPrimaryDatasourceProperties() {
        return new DatabaseProperties();
    }

    @Primary
    @DependsOn({"txManager"})
    @Bean(name = "primaryDataSource")
    public DataSource primaryDatasource(@Qualifier("primaryDatasourceProperties") DatabaseProperties primaryDatasourceProperties) {
//        return DataSourceBuilder.create().type(DruidDataSource.class).build();

        AtomikosDataSourceBean atomikosDataSourceBean = new AtomikosDataSourceBean();
        atomikosDataSourceBean.setXaDataSourceClassName(primaryDatasourceProperties.getType());

        Properties properties = new Properties();
        properties.put("url", primaryDatasourceProperties.getUrl());
        properties.put("username", primaryDatasourceProperties.getUsername());
        properties.put("password", primaryDatasourceProperties.getPassword());
//        properties.put("minPoolSize", primaryDatasourceProperties.getMinPoolSize());
//        properties.put("maxPoolSize", primaryDatasourceProperties.getMaxPoolSize());
//        properties.put("borrowConnectionTimeout", primaryDatasourceProperties.getBorrowConnectionTimeout());
        atomikosDataSourceBean.setUniqueResourceName("oneDataSource");
        atomikosDataSourceBean.setXaProperties(properties);

        atomikosDataSourceBean.setMinPoolSize(5);
        atomikosDataSourceBean.setMaxPoolSize(20);
        atomikosDataSourceBean.setBorrowConnectionTimeout(600);

        return atomikosDataSourceBean;
//        ds.setXaProperties(properties);
//        ds.setUniqueResourceName("OracleXADataSource");
//        ds.setXaDataSourceClassName("oracle.jdbc.xa.client.OracleXADataSource");

    }


    @DependsOn({"txManager"})
    @Bean(name = "secondDatasourceProperties")
    @ConfigurationProperties(prefix = "spring.datasource.druid.second")
    public DatabaseProperties getSecondDatasourceProperties() {
        return new DatabaseProperties();
    }

    @Bean(name = "secondDataSource")
    public DataSource secondaryDataSource(@Qualifier("secondDatasourceProperties") DatabaseProperties secondDatasourceProperties) {
        AtomikosDataSourceBean atomikosDataSourceBean = new AtomikosDataSourceBean();

        atomikosDataSourceBean.setXaDataSourceClassName(secondDatasourceProperties.getType());

        Properties properties = new Properties();
        properties.put("url", secondDatasourceProperties.getUrl());
        properties.put("username", secondDatasourceProperties.getUsername());
        properties.put("password", secondDatasourceProperties.getPassword());
//        properties.put("minPoolSize", secondDatasourceProperties.getMinPoolSize());
//        properties.put("maxPoolSize", secondDatasourceProperties.getMaxPoolSize());
//        properties.put("borrowConnectionTimeout", secondDatasourceProperties.getBorrowConnectionTimeout());
        atomikosDataSourceBean.setXaProperties(properties);
        atomikosDataSourceBean.setUniqueResourceName("secondDataSource");

        atomikosDataSourceBean.setMinPoolSize(5);
        atomikosDataSourceBean.setMaxPoolSize(20);
        atomikosDataSourceBean.setBorrowConnectionTimeout(600);

        return atomikosDataSourceBean;
    }


/*
    @Primary
    @Bean(name = "primaryDataSource")
    @ConfigurationProperties(prefix = "spring.datasource.druid.primary")
    public DataSource primaryDatasource() {
        return DataSourceBuilder.create().type(com.alibaba.druid.pool.DruidDataSource.class).build();
    }

    @Bean(name = "secondDataSource")
    @ConfigurationProperties(prefix = "spring.datasource.druid.second")
    public DataSource secondaryDataSource() {
        return DataSourceBuilder.create().type(com.alibaba.druid.pool.DruidDataSource.class).build();
    }
*/


}