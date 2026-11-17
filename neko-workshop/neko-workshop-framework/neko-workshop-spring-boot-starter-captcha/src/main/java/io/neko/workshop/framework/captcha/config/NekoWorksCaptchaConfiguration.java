package io.neko.workshop.framework.captcha.config;

import cn.hutool.core.util.ClassUtil;
import io.neko.workshop.framework.captcha.core.enums.CaptchaRedisKeyConstants;
import io.neko.workshop.framework.captcha.core.service.RedisCaptchaServiceImpl;
import com.xingyuv.captcha.service.CaptchaCacheService;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.core.StringRedisTemplate;

/**
 * 验证码自动配置
 *
 * <p>aj-captcha 通过 {@link java.util.ServiceLoader} 以无参构造器创建
 * {@link RedisCaptchaServiceImpl}，这条路径完全绕过了 Spring，
 * 因此 SPI 实例拿不到 Spring 注入的 {@link StringRedisTemplate}。</p>
 *
 * <p>这里把容器内的 BeanFactory 交给 {@link RedisCaptchaServiceImpl} 静态持有，
 * 让 SPI 实例也能按需取到 Template，避免验证码校验时的空指针。</p>
 */
@AutoConfiguration
public class NekoWorksCaptchaConfiguration implements BeanFactoryAware {

    static {
        // 手动加载 Lock4jRedisKeyConstants 类，因为它不会被使用到
        // 如果不加载，会导致 Redis 监控，看到它的 Redis Key 枚举
        ClassUtil.loadClass(CaptchaRedisKeyConstants.class.getName());
    }

    @Override
    public void setBeanFactory(BeanFactory beanFactory) {
        // 供 aj-captcha 通过 SPI 创建的实例回查容器
        RedisCaptchaServiceImpl.setBeanFactory(beanFactory);
    }

    @Bean
    public CaptchaCacheService captchaCacheService(StringRedisTemplate stringRedisTemplate) {
        return new RedisCaptchaServiceImpl(stringRedisTemplate);
    }

}
