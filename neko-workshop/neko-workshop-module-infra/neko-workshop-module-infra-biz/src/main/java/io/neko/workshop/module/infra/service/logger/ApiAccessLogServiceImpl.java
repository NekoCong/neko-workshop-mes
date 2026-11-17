package io.neko.workshop.module.infra.service.logger;

import io.neko.workshop.framework.common.pojo.PageResult;
import io.neko.workshop.module.infra.api.logger.dto.ApiAccessLogCreateReqDTO;
import io.neko.workshop.module.infra.controller.admin.logger.vo.apiaccesslog.ApiAccessLogExportReqVO;
import io.neko.workshop.module.infra.controller.admin.logger.vo.apiaccesslog.ApiAccessLogPageReqVO;
import io.neko.workshop.module.infra.convert.logger.ApiAccessLogConvert;
import io.neko.workshop.module.infra.dal.dataobject.logger.ApiAccessLogDO;
import io.neko.workshop.module.infra.dal.mysql.logger.ApiAccessLogMapper;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.List;
import lombok.RequiredArgsConstructor;

/**
 * API 访问日志 Service 实现类
 *
 * @author 灵喵工坊
 */
@Service
@Validated
@RequiredArgsConstructor
public class ApiAccessLogServiceImpl implements ApiAccessLogService {

    private final ApiAccessLogMapper apiAccessLogMapper;

    @Override
    public void createApiAccessLog(ApiAccessLogCreateReqDTO createDTO) {
        ApiAccessLogDO apiAccessLog = ApiAccessLogConvert.INSTANCE.convert(createDTO);
        apiAccessLogMapper.insert(apiAccessLog);
    }

    @Override
    public PageResult<ApiAccessLogDO> getApiAccessLogPage(ApiAccessLogPageReqVO pageReqVO) {
        return apiAccessLogMapper.selectPage(pageReqVO);
    }

    @Override
    public List<ApiAccessLogDO> getApiAccessLogList(ApiAccessLogExportReqVO exportReqVO) {
        return apiAccessLogMapper.selectList(exportReqVO);
    }

}
