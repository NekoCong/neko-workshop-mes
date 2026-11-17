package io.neko.workshop.framework.security.config;

import io.neko.workshop.framework.web.config.WebProperties;
import org.springframework.core.Ordered;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AuthorizeHttpRequestsConfigurer;

import org.springframework.beans.factory.annotation.Autowired;

/**
 * 自定义的 URL 的安全配置
 * 目的：每个 Maven Module 可以自定义规则！
 *
 * @author 灵喵工坊
 */
public abstract class AuthorizeRequestsCustomizer
        implements Customizer<AuthorizeHttpRequestsConfigurer<HttpSecurity>.AuthorizationManagerRequestMatcherRegistry>, Ordered {

    private final WebProperties webProperties;

    /**
     * 子类均为匿名内部类（在 @Bean 方法内 new 出来），无法由 Spring 直接装配，
     * 因此保留带 @Autowired 的构造器：由 Spring 创建子类 Bean 时自动注入。
     */
    @Autowired
    public AuthorizeRequestsCustomizer(WebProperties webProperties) {
        this.webProperties = webProperties;
    }

    protected String buildAdminApi(String url) {
        return webProperties.getAdminApi().getPrefix() + url;
    }

    protected String buildAppApi(String url) {
        return webProperties.getAppApi().getPrefix() + url;
    }

    @Override
    public int getOrder() {
        return 0;
    }

}
