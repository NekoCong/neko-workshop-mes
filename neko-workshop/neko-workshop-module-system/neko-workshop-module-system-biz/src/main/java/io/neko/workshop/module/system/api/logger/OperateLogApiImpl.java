package io.neko.workshop.module.system.api.logger;

import io.neko.workshop.module.system.api.logger.dto.OperateLogCreateReqDTO;
import io.neko.workshop.module.system.service.logger.OperateLogService;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import lombok.RequiredArgsConstructor;

/**
 * 操作日志 API 实现类
 *
 * @author 灵喵工坊
 */
@Service
@Validated
@RequiredArgsConstructor
public class OperateLogApiImpl implements OperateLogApi {

    private final OperateLogService operateLogService;

    @Override
    public void createOperateLog(OperateLogCreateReqDTO createReqDTO) {
        operateLogService.createOperateLog(createReqDTO);
    }

}
