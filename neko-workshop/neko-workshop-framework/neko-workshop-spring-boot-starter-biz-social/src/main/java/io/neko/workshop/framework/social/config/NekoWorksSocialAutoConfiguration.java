package io.neko.workshop.framework.social.config;

import io.neko.workshop.framework.social.core.NekoWorksAuthRequestFactory;
import com.xkcoding.http.HttpUtil;
import com.xkcoding.http.support.hutool.HutoolImpl;
import com.xkcoding.justauth.autoconfigure.CacheProperties;
import com.xkcoding.justauth.autoconfigure.JustAuthProperties;
import com.xkcoding.justauth.support.cache.RedisStateCache;
import lombok.extern.slf4j.Slf4j;
import me.zhyd.oauth.cache.AuthDefaultStateCache;
import me.zhyd.oauth.cache.AuthStateCache;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.data.redis.core.RedisTemplate;

/**
 * 社交自动装配类
 *
 * <p>注意：这里刻意不 @Import(JustAuthAutoConfiguration.class)。
 * justauth-spring-boot-starter 1.4.0 的内部配置类
 * {@code JustAuthStateCacheConfiguration$Redis} 依赖旧版
 * {@code org.springframework.boot.autoconfigure.data.redis.RedisAutoConfiguration}
 * （Spring Boot 4 已移除）与单个 StringRedisTemplate（Redisson 会额外注册一个，
 * 导致 "required a single bean, but 2 were found"），因此由本类自行装配
 * {@link AuthStateCache} 与 {@link NekoWorksAuthRequestFactory}。
 *
 * @author timfruit
 * @date 2021-10-30
 */
@Slf4j
@AutoConfiguration
@EnableConfigurationProperties(JustAuthProperties.class)
public class NekoWorksSocialAutoConfiguration {

    /**
     * JustAuth 的 state 缓存。
     *
     * <p>缓存实现由 {@code justauth.cache.type} 决定：{@code REDIS} 使用 Redis，其余使用内存。
     */
    @Bean
    @ConditionalOnMissingBean(AuthStateCache.class)
    public AuthStateCache authStateCache(JustAuthProperties properties,
                                         ObjectProvider<RedisTemplate<String, String>> redisTemplateProvider) {
        CacheProperties cacheProperties = properties.getCache();
        CacheProperties.CacheType type = cacheProperties != null && cacheProperties.getType() != null
                ? cacheProperties.getType() : CacheProperties.CacheType.DEFAULT;
        if (type == CacheProperties.CacheType.REDIS) {
            RedisTemplate<String, String> redisTemplate = redisTemplateProvider.getIfAvailable();
            if (redisTemplate != null) {
                log.info("[authStateCache][使用 Redis 缓存 JustAuth 的 state 数据]");
                return new RedisStateCache(redisTemplate, cacheProperties);
            }
            log.warn("[authStateCache][justauth.cache.type=REDIS 但未找到 RedisTemplate，降级为内存缓存]");
        }
        log.info("[authStateCache][使用内存缓存 JustAuth 的 state 数据]");
        return AuthDefaultStateCache.INSTANCE;
    }

    @Bean
    @Primary
    @ConditionalOnProperty(prefix = "justauth", value = "enabled", havingValue = "true", matchIfMissing = true)
    public NekoWorksAuthRequestFactory nekoWorksAuthRequestFactory(JustAuthProperties properties, AuthStateCache authStateCache) {
        // 需要修改 HttpUtil 使用的实现，避免类报错
        HttpUtil.setHttp(new HutoolImpl());
        // 创建 NekoWorksAuthRequestFactory
        return new NekoWorksAuthRequestFactory(properties, authStateCache);
    }

}
