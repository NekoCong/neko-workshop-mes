package io.neko.workshop.framework.redis.config;

import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializer;

/**
 * Redis 配置类
 *
 * 排序在 Spring Boot 自带的 {@code DataRedisAutoConfiguration} 之前，保证本项目基于 JSON 序列化的
 * {@code RedisTemplate} 优先注册；后续 Boot 的自动配置会因为 {@code @ConditionalOnMissingBean} 而跳过。
 */
@AutoConfiguration(beforeName = "org.springframework.boot.data.redis.autoconfigure.DataRedisAutoConfiguration")
public class NekoWorksRedisAutoConfiguration {

    /**
     * 创建 RedisTemplate Bean，使用 JSON 序列化方式
     *
     * 注意：Spring Boot 4 的 {@code DataRedisAutoConfiguration} 也会注册名为 {@code redisTemplate} 的 Bean，
     * 且它自身带 {@code @ConditionalOnMissingBean(name = "redisTemplate")}。这里必须同样声明
     * {@link ConditionalOnMissingBean}，否则两个配置类同时导入时会抛出
     * {@code BeanDefinitionOverrideException: Invalid bean definition with name 'redisTemplate'}。
     */
    @Bean
    @ConditionalOnMissingBean(name = "redisTemplate")
    public RedisTemplate<String, Object> redisTemplate(RedisConnectionFactory factory) {
        // 创建 RedisTemplate 对象
        RedisTemplate<String, Object> template = new RedisTemplate<>();
        // 设置 RedisConnection 工厂。😈 它就是实现多种 Java Redis 客户端接入的秘密工厂。感兴趣的胖友，可以自己去撸下。
        template.setConnectionFactory(factory);
        // 使用 String 序列化方式，序列化 KEY 。
        template.setKeySerializer(RedisSerializer.string());
        template.setHashKeySerializer(RedisSerializer.string());
        // 使用 JSON 序列化方式（库是 Jackson ），序列化 VALUE 。
        template.setValueSerializer(RedisSerializer.json());
        template.setHashValueSerializer(RedisSerializer.json());
        return template;
    }

}
