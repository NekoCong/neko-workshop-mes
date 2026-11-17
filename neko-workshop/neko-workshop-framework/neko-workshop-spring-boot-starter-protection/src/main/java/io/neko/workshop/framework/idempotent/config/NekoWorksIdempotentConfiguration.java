package io.neko.workshop.framework.idempotent.config;

import io.neko.workshop.framework.idempotent.core.aop.IdempotentAspect;
import io.neko.workshop.framework.idempotent.core.keyresolver.impl.DefaultIdempotentKeyResolver;
import io.neko.workshop.framework.idempotent.core.keyresolver.impl.ExpressionIdempotentKeyResolver;
import io.neko.workshop.framework.idempotent.core.keyresolver.IdempotentKeyResolver;
import io.neko.workshop.framework.idempotent.core.redis.IdempotentRedisDAO;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import io.neko.workshop.framework.redis.config.NekoWorksRedisAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.core.StringRedisTemplate;

import java.util.List;

@AutoConfiguration(after = NekoWorksRedisAutoConfiguration.class)
public class NekoWorksIdempotentConfiguration {

    @Bean
    public IdempotentAspect idempotentAspect(List<IdempotentKeyResolver> keyResolvers, IdempotentRedisDAO idempotentRedisDAO) {
        return new IdempotentAspect(keyResolvers, idempotentRedisDAO);
    }

    @Bean
    public IdempotentRedisDAO idempotentRedisDAO(StringRedisTemplate stringRedisTemplate) {
        return new IdempotentRedisDAO(stringRedisTemplate);
    }

    // ========== 各种 IdempotentKeyResolver Bean ==========

    @Bean
    public DefaultIdempotentKeyResolver defaultIdempotentKeyResolver() {
        return new DefaultIdempotentKeyResolver();
    }

    @Bean
    public ExpressionIdempotentKeyResolver expressionIdempotentKeyResolver() {
        return new ExpressionIdempotentKeyResolver();
    }

}
