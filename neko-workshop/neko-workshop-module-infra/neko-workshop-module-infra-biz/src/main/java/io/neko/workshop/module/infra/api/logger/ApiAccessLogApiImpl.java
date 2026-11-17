package io.neko.workshop.module.infra.api.logger;

import io.neko.workshop.module.infra.api.logger.dto.ApiAccessLogCreateReqDTO;
import io.neko.workshop.module.infra.service.logger.ApiAccessLogService;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import lombok.RequiredArgsConstructor;

/**
 * API 访问日志的 API 实现类
 *
 * @author 灵喵工坊
 */
@Service
@Validated
@RequiredArgsConstructor
public class ApiAccessLogApiImpl implements ApiAccessLogApi {

    private final ApiAccessLogService apiAccessLogService;

    @Override
    public void createApiAccessLog(ApiAccessLogCreateReqDTO createDTO) {
        apiAccessLogService.createApiAccessLog(createDTO);
    }

}
