package com.example.order.security;

import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.connection.lettuce.LettucePoolingClientConfiguration;
import org.springframework.data.redis.core.StringRedisTemplate;

import java.time.Duration;

/**
 * @author Chencj
 * @description
 * @date 2020/2/8
 */
@Configuration
public class RedisConfig {

    @Value("${spring.redis.port}")
    private int port;

    @Value("${spring.redis.host}")
    private String hostname;

    @Primary
    @Bean(name = "redisStandaloneConnectionFactory")
    public RedisConnectionFactory redisStandaloneConnectionFactory() {
        LettucePoolingClientConfiguration lettucePoolingClientConfiguration = LettucePoolingClientConfiguration.builder()
                .commandTimeout(Duration.ofMillis(6000))
                .build();
        final RedisStandaloneConfiguration redisConfig = new RedisStandaloneConfiguration();
        redisConfig.setPort(port);
        redisConfig.setDatabase(0);
        redisConfig.setHostName(hostname);
        redisConfig.setPassword("");
        return new LettuceConnectionFactory(redisConfig, lettucePoolingClientConfiguration);
    }

    @Bean(name = "stringRedisTemplate")
    public StringRedisTemplate stringRedisTemplate(
            @Qualifier(value = "redisStandaloneConnectionFactory") RedisConnectionFactory redisConnectionFactory) throws Exception {
        StringRedisTemplate template = new StringRedisTemplate();
        template.setConnectionFactory(redisConnectionFactory);
        return template;
    }
}
