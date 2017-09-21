package com.eryu.common;

import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import redis.clients.jedis.JedisPoolConfig;

/**
 * Redis 配置
 * Created by yangtao on 2017/6/29.
 */
@Configuration
@ConfigurationProperties(prefix = "spring.redis")
@Setter
public class RedisConfig {

    private String password;
    private int port;
    private String host;
    private int database;

    @Bean
    public JedisConnectionFactory connectionFactory() {
        JedisConnectionFactory factory = new JedisConnectionFactory();
        factory.setHostName(host);
        factory.setPort(port);
        factory.setPassword(password);
        factory.setDatabase(database);
        factory.setTimeout(5000); //设置连接超时时间
        factory.setPoolConfig(poolConfig());
        return factory;
    }

    @Bean
    public JedisPoolConfig poolConfig() {
        JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
        jedisPoolConfig.setMaxIdle(100);
        jedisPoolConfig.setMaxTotal(300);
        jedisPoolConfig.setMaxWaitMillis(8000);
        jedisPoolConfig.setMinIdle(0);
        return jedisPoolConfig;
    }

}
