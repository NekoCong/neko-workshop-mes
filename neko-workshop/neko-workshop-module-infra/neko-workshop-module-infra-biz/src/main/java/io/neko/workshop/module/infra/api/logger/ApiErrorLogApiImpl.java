package io.neko.workshop.module.infra.api.logger;

import io.neko.workshop.module.infra.api.logger.dto.ApiErrorLogCreateReqDTO;
import io.neko.workshop.module.infra.service.logger.ApiErrorLogService;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import lombok.RequiredArgsConstructor;

/**
 * API 访问日志的 API 接口
 *
 * @author 灵喵工坊
 */
@Service
@Validated
@RequiredArgsConstructor
public class ApiErrorLogApiImpl implements ApiErrorLogApi {

    private final ApiErrorLogService apiErrorLogService;

    @Override
    public void createApiErrorLog(ApiErrorLogCreateReqDTO createDTO) {
        apiErrorLogService.createApiErrorLog(createDTO);
    }

}
