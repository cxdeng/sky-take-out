package com.sky.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

@Configuration
@Slf4j
public class RedisConfiguration {

    @Bean
    public RedisTemplate<String, Object> redisTemplate(RedisConnectionFactory connectionFactory){
        log.info("开始创建redis模板对象");
        // Create the object of RedisTemplate
        RedisTemplate<String, Object> redisTemplate = new RedisTemplate<>();

        // Set up connection factory
        redisTemplate.setConnectionFactory(connectionFactory);

        // Set up the json serializer
//        GenericJackson2JsonRedisSerializer jackson2JsonRedisSerializer = new GenericJackson2JsonRedisSerializer();

        // Set the serialization of the key
        redisTemplate.setKeySerializer(RedisSerializer.string());

        // Set the serialization of value
//        redisTemplate.setValueSerializer(jackson2JsonRedisSerializer);
//        redisTemplate.setHashValueSerializer(jackson2JsonRedisSerializer);

        // return
        return redisTemplate;

//        log.info("开始创建redis模板对象");
//        RedisTemplate redisTemplate = new RedisTemplate();
//        //设置redis的连接工厂对象
//        redisTemplate.setConnectionFactory(connectionFactory);
//
//        //设置redis key的序列化器
//        redisTemplate.setKeySerializer(new StringRedisSerializer());
//        return redisTemplate;
    }

}
