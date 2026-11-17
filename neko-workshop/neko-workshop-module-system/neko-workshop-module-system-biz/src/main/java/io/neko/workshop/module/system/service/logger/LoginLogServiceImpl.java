package io.neko.workshop.module.system.service.logger;

import io.neko.workshop.framework.common.pojo.PageResult;
import io.neko.workshop.module.system.api.logger.dto.LoginLogCreateReqDTO;
import io.neko.workshop.module.system.controller.admin.logger.vo.loginlog.LoginLogExportReqVO;
import io.neko.workshop.module.system.controller.admin.logger.vo.loginlog.LoginLogPageReqVO;
import io.neko.workshop.module.system.convert.logger.LoginLogConvert;
import io.neko.workshop.module.system.dal.dataobject.logger.LoginLogDO;
import io.neko.workshop.module.system.dal.mysql.logger.LoginLogMapper;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.List;
import lombok.RequiredArgsConstructor;

/**
 * 登录日志 Service 实现
 */
@Service
@Validated
@RequiredArgsConstructor
public class LoginLogServiceImpl implements LoginLogService {

    private final LoginLogMapper loginLogMapper;

    @Override
    public PageResult<LoginLogDO> getLoginLogPage(LoginLogPageReqVO reqVO) {
        return loginLogMapper.selectPage(reqVO);
    }

    @Override
    public List<LoginLogDO> getLoginLogList(LoginLogExportReqVO reqVO) {
        return loginLogMapper.selectList(reqVO);
    }

    @Override
    public void createLoginLog(LoginLogCreateReqDTO reqDTO) {
        LoginLogDO loginLog = LoginLogConvert.INSTANCE.convert(reqDTO);
        loginLogMapper.insert(loginLog);
    }

}
