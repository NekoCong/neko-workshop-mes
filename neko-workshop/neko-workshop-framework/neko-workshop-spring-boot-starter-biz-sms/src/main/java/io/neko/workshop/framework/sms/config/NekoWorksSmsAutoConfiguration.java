package io.neko.workshop.framework.sms.config;

import io.neko.workshop.framework.sms.core.client.SmsClientFactory;
import io.neko.workshop.framework.sms.core.client.impl.SmsClientFactoryImpl;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.context.annotation.Bean;

/**
 * 短信配置类
 *
 * @author 灵喵工坊
 */
@AutoConfiguration
public class NekoWorksSmsAutoConfiguration {

    @Bean
    public SmsClientFactory smsClientFactory() {
        return new SmsClientFactoryImpl();
    }

}
