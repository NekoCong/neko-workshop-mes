package io.neko.workshop.framework.captcha.core.service;

import com.xingyuv.captcha.service.CaptchaCacheService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.data.redis.core.StringRedisTemplate;

import java.util.concurrent.TimeUnit;

/**
 * 基于 Redis 实现验证码的存储
 *
 * <p><b>为什么不能只靠构造器注入：</b>aj-captcha 通过
 * {@link java.util.ServiceLoader} 读取
 * META-INF/services/com.xingyuv.captcha.service.CaptchaCacheService，
 * 用<b>无参构造器</b>创建本类实例。这条路径不经过 Spring 容器，
 * 注入和生命周期回调都不会发生，字段会是 null。</p>
 *
 * <p>因此这里由 {@link io.neko.workshop.framework.captcha.config.NekoWorksCaptchaConfiguration}
 * 在启动时把 BeanFactory 静态登记进来，SPI 实例在首次使用时按需取回
 * {@link StringRedisTemplate}。Spring 直接注册的那个实例则走构造器注入。</p>
 *
 * @author 星语
 */
@NoArgsConstructor
@AllArgsConstructor
public class RedisCaptchaServiceImpl implements CaptchaCacheService {

    /**
     * 由自动配置在启动时写入，供 ServiceLoader 创建的实例回查容器
     */
    private static volatile BeanFactory beanFactory;

    private StringRedisTemplate stringRedisTemplate;

    /**
     * 登记 BeanFactory（由 NekoWorksCaptchaConfiguration 调用）
     */
    public static void setBeanFactory(BeanFactory factory) {
        RedisCaptchaServiceImpl.beanFactory = factory;
    }

    /**
     * 获取 Template：构造器注入优先，否则从容器回查（SPI 创建的场景）
     */
    private StringRedisTemplate redisTemplate() {
        if (stringRedisTemplate == null) {
            BeanFactory factory = beanFactory;
            if (factory == null) {
                throw new IllegalStateException(
                        "StringRedisTemplate 未就绪：验证码缓存需要 Spring 容器，请确认 NekoWorksCaptchaConfiguration 已生效");
            }
            stringRedisTemplate = factory.getBean(StringRedisTemplate.class);
        }
        return stringRedisTemplate;
    }

    @Override
    public String type() {
        return "redis";
    }

    @Override
    public void set(String key, String value, long expiresInSeconds) {
        redisTemplate().opsForValue().set(key, value, expiresInSeconds, TimeUnit.SECONDS);
    }

    @Override
    public boolean exists(String key) {
        return Boolean.TRUE.equals(redisTemplate().hasKey(key));
    }

    @Override
    public void delete(String key) {
        redisTemplate().delete(key);
    }

    @Override
    public String get(String key) {
        return redisTemplate().opsForValue().get(key);
    }

    @Override
    public Long increment(String key, long val) {
        return redisTemplate().opsForValue().increment(key, val);
    }

}
