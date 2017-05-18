package com.toquery.cleverweb.config;

import org.springframework.boot.autoconfigure.data.redis.RedisProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;

import javax.annotation.Resource;

/**
 * 这个配置项修复hostName为null的bug，不知道是不是spring框架上
 * @author toquery
 * @version 1
 */
@Configuration
public class RedisConfig {

    @Resource
    private RedisProperties redisProperties;

    @Bean
    public JedisConnectionFactory redisConnectionFactory() {
        JedisConnectionFactory redisConnectionFactory = new JedisConnectionFactory();
        // todo redis配置修改
        redisConnectionFactory.setHostName(redisProperties.getHost());
        redisConnectionFactory.setPort(redisProperties.getPort());
        redisConnectionFactory.setDatabase(redisProperties.getDatabase());
        redisConnectionFactory.setPassword(redisProperties.getPassword());
        return redisConnectionFactory;
    }
}
