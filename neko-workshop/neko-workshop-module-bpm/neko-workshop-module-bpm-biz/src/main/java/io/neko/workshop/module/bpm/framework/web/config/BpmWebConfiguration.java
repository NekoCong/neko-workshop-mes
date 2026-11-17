package io.neko.workshop.module.bpm.framework.web.config;

import io.neko.workshop.framework.swagger.config.NekoWorksSwaggerAutoConfiguration;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * bpm 模块的 web 组件的 Configuration
 *
 * @author 灵喵工坊
 */
@Configuration(proxyBeanMethods = false)
public class BpmWebConfiguration {

    /**
     * bpm 模块的 API 分组
     */
    @Bean
    public GroupedOpenApi bpmGroupedOpenApi() {
        return NekoWorksSwaggerAutoConfiguration.buildGroupedOpenApi("bpm");
    }

}
