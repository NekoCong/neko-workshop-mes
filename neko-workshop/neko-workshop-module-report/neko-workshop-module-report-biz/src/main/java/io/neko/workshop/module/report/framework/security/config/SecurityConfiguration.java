package io.neko.workshop.module.report.framework.security.config;

import io.neko.workshop.framework.security.config.AuthorizeRequestsCustomizer;
import io.neko.workshop.framework.web.config.WebProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AuthorizeHttpRequestsConfigurer;

/**
 * Report 模块的 Security 配置
 */
@Configuration("reportSecurityConfiguration")
public class SecurityConfiguration {

    @Bean("reportAuthorizeRequestsCustomizer")
    public AuthorizeRequestsCustomizer authorizeRequestsCustomizer(WebProperties webProperties) {
        return new AuthorizeRequestsCustomizer(webProperties) {

            @Override
            public void customize(AuthorizeHttpRequestsConfigurer<HttpSecurity>.AuthorizationManagerRequestMatcherRegistry registry) {
                //积木报表
                registry.requestMatchers("/jmreport/**").permitAll();
            }

        };
    }

}
