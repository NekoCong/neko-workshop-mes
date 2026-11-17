package io.neko.workshop.module.member.framework.web.config;

import io.neko.workshop.framework.swagger.config.NekoWorksSwaggerAutoConfiguration;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * member 模块的 web 组件的 Configuration
 *
 * @author 灵喵工坊
 */
@Configuration(proxyBeanMethods = false)
public class MemberWebConfiguration {

    /**
     * member 模块的 API 分组
     */
    @Bean
    public GroupedOpenApi memberGroupedOpenApi() {
        return NekoWorksSwaggerAutoConfiguration.buildGroupedOpenApi("member");
    }

}
