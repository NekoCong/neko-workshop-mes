package io.neko.workshop.framework.apilog.core.service;

/**
 * API 访问日志 Framework Service 接口
 *
 * @author 灵喵工坊
 */
public interface ApiAccessLogFrameworkService {

    /**
     * 创建 API 访问日志
     *
     * @param apiAccessLog API 访问日志
     */
    void createApiAccessLog(ApiAccessLog apiAccessLog);

}
