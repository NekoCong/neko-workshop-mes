package io.neko.workshop.module.system.api.logger;

import io.neko.workshop.module.system.api.logger.dto.LoginLogCreateReqDTO;
import io.neko.workshop.module.system.service.logger.LoginLogService;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import lombok.RequiredArgsConstructor;

/**
 * 登录日志的 API 实现类
 *
 * @author 灵喵工坊
 */
@Service
@Validated
@RequiredArgsConstructor
public class LoginLogApiImpl implements LoginLogApi {

    private final LoginLogService loginLogService;

    @Override
    public void createLoginLog(LoginLogCreateReqDTO reqDTO) {
        loginLogService.createLoginLog(reqDTO);
    }

}
