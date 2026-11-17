package io.neko.workshop.framework.operatelog.core.service;

/**
 * 操作日志 Framework Service 接口
 *
 * @author 灵喵工坊
 */
public interface OperateLogFrameworkService {

    /**
     * 记录操作日志
     *
     * @param operateLog 操作日志请求
     */
    void createOperateLog(OperateLog operateLog);

}
