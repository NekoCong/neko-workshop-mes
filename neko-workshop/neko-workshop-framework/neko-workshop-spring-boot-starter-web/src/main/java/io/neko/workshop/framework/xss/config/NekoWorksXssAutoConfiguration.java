package io.neko.workshop.framework.xss.config;

import io.neko.workshop.framework.common.enums.WebFilterOrderEnum;
import io.neko.workshop.framework.xss.core.clean.JsoupXssCleaner;
import io.neko.workshop.framework.xss.core.clean.XssCleaner;
import io.neko.workshop.framework.xss.core.filter.XssFilter;
import io.neko.workshop.framework.xss.core.json.XssStringJsonDeserializer;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.jackson.autoconfigure.JsonMapperBuilderCustomizer;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.util.PathMatcher;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import tools.jackson.databind.module.SimpleModule;

import static io.neko.workshop.framework.web.config.NekoWorksWebAutoConfiguration.createFilterBean;

@AutoConfiguration
@EnableConfigurationProperties(XssProperties.class)
public class NekoWorksXssAutoConfiguration implements WebMvcConfigurer {

    /**
     * Xss 清理者
     *
     * @return XssCleaner
     */
    @Bean
    @ConditionalOnMissingBean(XssCleaner.class)
    public XssCleaner xssCleaner() {
        return new JsoupXssCleaner();
    }

    /**
     * 注册 Jackson 的反序列化器，用于处理 json 类型参数的 xss 过滤
     *
     * @return JsonMapperBuilderCustomizer
     */
    @Bean
    @ConditionalOnMissingBean(name = "xssJacksonCustomizer")
    @ConditionalOnProperty(value = "neko-workshop.xss.enable", havingValue = "true")
    public JsonMapperBuilderCustomizer xssJacksonCustomizer(XssCleaner xssCleaner) {
        // 在反序列化时进行 xss 过滤，可以替换使用 XssStringJsonSerializer，在序列化时进行处理
        return builder -> {
            SimpleModule module = new SimpleModule("nekoWorksXssModule");
            module.addDeserializer(String.class, new XssStringJsonDeserializer(xssCleaner));
            builder.addModule(module);
        };
    }

    /**
     * 创建 XssFilter Bean，解决 Xss 安全问题
     */
    @Bean
    @ConditionalOnBean(XssCleaner.class)
    public FilterRegistrationBean<XssFilter> xssFilter(XssProperties properties, PathMatcher pathMatcher, XssCleaner xssCleaner) {
        return createFilterBean(new XssFilter(properties, pathMatcher, xssCleaner), WebFilterOrderEnum.XSS_FILTER);
    }

}
