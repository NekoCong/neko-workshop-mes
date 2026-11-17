package io.neko.workshop.module.infra.api.logger;

import io.neko.workshop.module.infra.api.logger.dto.ApiErrorLogCreateReqDTO;

import jakarta.validation.Valid;

/**
 * API 错误日志的 API 接口
 *
 * @author 灵喵工坊
 */
public interface ApiErrorLogApi {

    /**
     * 创建 API 错误日志
     *
     * @param createDTO 创建信息
     */
    void createApiErrorLog(@Valid ApiErrorLogCreateReqDTO createDTO);

}
