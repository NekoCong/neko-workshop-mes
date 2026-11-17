package io.neko.workshop.framework.apilog.config;

import io.neko.workshop.framework.apilog.core.filter.ApiAccessLogFilter;
import io.neko.workshop.framework.apilog.core.service.ApiAccessLogFrameworkService;
import io.neko.workshop.framework.apilog.core.service.ApiAccessLogFrameworkServiceImpl;
import io.neko.workshop.framework.apilog.core.service.ApiErrorLogFrameworkService;
import io.neko.workshop.framework.apilog.core.service.ApiErrorLogFrameworkServiceImpl;
import io.neko.workshop.framework.common.enums.WebFilterOrderEnum;
import io.neko.workshop.framework.web.config.WebProperties;
import io.neko.workshop.framework.web.config.NekoWorksWebAutoConfiguration;
import io.neko.workshop.module.infra.api.logger.ApiAccessLogApi;
import io.neko.workshop.module.infra.api.logger.ApiErrorLogApi;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;

import jakarta.servlet.Filter;

@AutoConfiguration(after = NekoWorksWebAutoConfiguration.class)
public class NekoWorksApiLogAutoConfiguration {

    @Bean
    public ApiAccessLogFrameworkService apiAccessLogFrameworkService(ApiAccessLogApi apiAccessLogApi) {
        return new ApiAccessLogFrameworkServiceImpl(apiAccessLogApi);
    }

    @Bean
    public ApiErrorLogFrameworkService apiErrorLogFrameworkService(ApiErrorLogApi apiErrorLogApi) {
        return new ApiErrorLogFrameworkServiceImpl(apiErrorLogApi);
    }

    /**
     * 创建 ApiAccessLogFilter Bean，记录 API 请求日志
     */
    @Bean
    @ConditionalOnProperty(prefix = "neko-workshop.access-log", value = "enable", matchIfMissing = true) // 允许使用 neko-workshop.access-log.enable=false 禁用访问日志
    public FilterRegistrationBean<ApiAccessLogFilter> apiAccessLogFilter(WebProperties webProperties,
                                                                         @Value("${spring.application.name}") String applicationName,
                                                                         ApiAccessLogFrameworkService apiAccessLogFrameworkService) {
        ApiAccessLogFilter filter = new ApiAccessLogFilter(webProperties, applicationName, apiAccessLogFrameworkService);
        return createFilterBean(filter, WebFilterOrderEnum.API_ACCESS_LOG_FILTER);
    }

    private static <T extends Filter> FilterRegistrationBean<T> createFilterBean(T filter, Integer order) {
        FilterRegistrationBean<T> bean = new FilterRegistrationBean<>(filter);
        bean.setOrder(order);
        return bean;
    }

}
