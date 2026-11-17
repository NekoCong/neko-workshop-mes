package io.neko.workshop.framework.pay.config;

import io.neko.workshop.framework.pay.core.client.PayClientFactory;
import io.neko.workshop.framework.pay.core.client.impl.PayClientFactoryImpl;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;

/**
 * 支付配置类
 *
 * @author 灵喵工坊
 */
@AutoConfiguration
@EnableConfigurationProperties(PayProperties.class)
public class NekoWorksPayAutoConfiguration {

    @Bean
    public PayClientFactory payClientFactory() {
        return new PayClientFactoryImpl();
    }

}
