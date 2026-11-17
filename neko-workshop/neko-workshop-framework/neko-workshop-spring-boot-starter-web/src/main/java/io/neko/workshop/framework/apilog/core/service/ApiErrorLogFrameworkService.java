package io.neko.workshop.framework.apilog.core.service;

/**
 * API 错误日志 Framework Service 接口
 *
 * @author 灵喵工坊
 */
public interface ApiErrorLogFrameworkService {

    /**
     * 创建 API 错误日志
     *
     * @param apiErrorLog API 错误日志
     */
    void createApiErrorLog(ApiErrorLog apiErrorLog);

}
