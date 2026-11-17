package io.neko.workshop.framework.jackson.config;

import io.neko.workshop.framework.jackson.core.databind.NumberSerializer;
import io.neko.workshop.framework.jackson.core.databind.LocalDateTimeDeserializer;
import io.neko.workshop.framework.jackson.core.databind.LocalDateTimeSerializer;
import io.neko.workshop.framework.common.util.json.JsonUtils;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.jackson.autoconfigure.JsonMapperBuilderCustomizer;
import org.springframework.context.annotation.Bean;
import tools.jackson.databind.ObjectMapper;
import tools.jackson.databind.module.SimpleModule;

import java.time.LocalDateTime;

/**
 * Jackson 自动配置。
 * <p>
 * 注意：Spring Boot 4 默认使用 Jackson 3（tools.jackson），因此这里基于
 * {@link JsonMapperBuilderCustomizer} 在 JsonMapper 构建阶段注册自定义模块。
 */
@AutoConfiguration
public class NekoWorksJacksonAutoConfiguration {

    /**
     * 注册序列化规则：
     * 1. Long 超过 2^53-1 时序列化为字符串，避免前端 JavaScript 精度丢失
     * 2. LocalDateTime 序列化为毫秒时间戳
     */
    @Bean
    public JsonMapperBuilderCustomizer nekoWorksJsonMapperBuilderCustomizer() {
        return builder -> builder.addModule(nekoWorksSimpleModule());
    }

    private SimpleModule nekoWorksSimpleModule() {
        SimpleModule simpleModule = new SimpleModule("nekoWorksModule");
        simpleModule
                .addSerializer(Long.class, NumberSerializer.INSTANCE)
                .addSerializer(Long.TYPE, NumberSerializer.INSTANCE)
                .addSerializer(LocalDateTime.class, LocalDateTimeSerializer.INSTANCE)
                .addDeserializer(LocalDateTime.class, LocalDateTimeDeserializer.INSTANCE);
        return simpleModule;
    }

    /**
     * 将 Spring 创建的 ObjectMapper 交给 {@link JsonUtils} 使用，保证工具类与 Web 序列化规则一致
     */
    @Bean
    public BeanPostProcessor nekoWorksJsonUtilsInitializer() {
        return new BeanPostProcessor() {
            @Override
            public Object postProcessAfterInitialization(Object bean, String beanName) {
                if (bean instanceof ObjectMapper objectMapper) {
                    JsonUtils.init(objectMapper);
                }
                return bean;
            }
        };
    }

}
