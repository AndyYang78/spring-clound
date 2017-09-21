package com.eryu.common.datasource;

import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import javax.sql.DataSource;

/**
 * 数据库配置
 */
@Configuration
public class DataSourceConfig {

    /**
     * 管理后台库
     */
    @Bean
    @Primary
    @ConfigurationProperties(prefix = "spring.datasource.manager")
    public DataSource managerDataSource() {
        return DataSourceBuilder.create().build();
    }

    /**
     * 用户库
     */
    @Bean
    @ConfigurationProperties(prefix = "spring.datasource.user")
    public DataSource userDataSource() {
        return DataSourceBuilder.create().build();
    }

    /**
     * 内容库
     */
    @Bean
    @ConfigurationProperties(prefix = "spring.datasource.content")
    public DataSource contentDataSource() {
        return DataSourceBuilder.create().build();
    }

    /**
     * 交易库
     */
    @Bean
    @ConfigurationProperties(prefix = "spring.datasource.trade")
    public DataSource tradeDataSource() {
        return DataSourceBuilder.create().build();
    }
}
