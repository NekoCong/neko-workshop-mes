package io.neko.workshop.framework.file.config;

import io.neko.workshop.framework.file.core.client.FileClientFactory;
import io.neko.workshop.framework.file.core.client.FileClientFactoryImpl;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.context.annotation.Bean;

/**
 * 文件配置类
 *
 * @author 灵喵工坊
 */
@AutoConfiguration
public class NekoWorksFileAutoConfiguration {

    @Bean
    public FileClientFactory fileClientFactory() {
        return new FileClientFactoryImpl();
    }

}
