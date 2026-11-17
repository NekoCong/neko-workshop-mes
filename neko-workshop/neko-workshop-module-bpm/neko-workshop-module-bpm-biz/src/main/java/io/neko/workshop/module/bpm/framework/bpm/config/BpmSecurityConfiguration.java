package io.neko.workshop.module.bpm.framework.bpm.config;

import io.neko.workshop.framework.security.config.AuthorizeRequestsCustomizer;
import io.neko.workshop.framework.web.config.WebProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AuthorizeHttpRequestsConfigurer;

/**
 * @author kemengkai
 * @create 2022-05-07 08:15
 */
@Configuration("bpmSecurityConfiguration")
public class BpmSecurityConfiguration {

    @Bean("bpmAuthorizeRequestsCustomizer")
    public AuthorizeRequestsCustomizer authorizeRequestsCustomizer(WebProperties webProperties) {
        return new AuthorizeRequestsCustomizer(webProperties) {

            @Override
            public void customize(AuthorizeHttpRequestsConfigurer<HttpSecurity>.AuthorizationManagerRequestMatcherRegistry registry) {
                // 任务回退接口
                registry.requestMatchers(buildAdminApi("/bpm/task/back")).permitAll();
            }

        };
    }
}
