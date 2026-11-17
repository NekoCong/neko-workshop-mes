package io.neko.workshop.module.system.service.logger;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.StrUtil;
import io.neko.workshop.framework.common.pojo.PageResult;
import io.neko.workshop.framework.common.util.string.StrUtils;
import io.neko.workshop.module.system.api.logger.dto.OperateLogCreateReqDTO;
import io.neko.workshop.module.system.controller.admin.logger.vo.operatelog.OperateLogExportReqVO;
import io.neko.workshop.module.system.controller.admin.logger.vo.operatelog.OperateLogPageReqVO;
import io.neko.workshop.module.system.convert.logger.OperateLogConvert;
import io.neko.workshop.module.system.dal.dataobject.logger.OperateLogDO;
import io.neko.workshop.module.system.dal.dataobject.user.AdminUserDO;
import io.neko.workshop.module.system.dal.mysql.logger.OperateLogMapper;
import io.neko.workshop.module.system.service.user.AdminUserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

import static io.neko.workshop.framework.common.util.collection.CollectionUtils.convertSet;
import static io.neko.workshop.module.system.dal.dataobject.logger.OperateLogDO.JAVA_METHOD_ARGS_MAX_LENGTH;
import static io.neko.workshop.module.system.dal.dataobject.logger.OperateLogDO.RESULT_MAX_LENGTH;

@Service
@Validated
@Slf4j
@RequiredArgsConstructor
public class OperateLogServiceImpl implements OperateLogService {

    private final OperateLogMapper operateLogMapper;

    private final AdminUserService userService;

    @Override
    public void createOperateLog(OperateLogCreateReqDTO createReqDTO) {
        OperateLogDO logDO = OperateLogConvert.INSTANCE.convert(createReqDTO);
        logDO.setJavaMethodArgs(StrUtils.maxLength(logDO.getJavaMethodArgs(), JAVA_METHOD_ARGS_MAX_LENGTH));
        logDO.setResultData(StrUtils.maxLength(logDO.getResultData(), RESULT_MAX_LENGTH));
        operateLogMapper.insert(logDO);
    }

    @Override
    public PageResult<OperateLogDO> getOperateLogPage(OperateLogPageReqVO reqVO) {
        // 处理基于用户昵称的查询
        Collection<Long> userIds = null;
        if (StrUtil.isNotEmpty(reqVO.getUserNickname())) {
            userIds = convertSet(userService.getUserListByNickname(reqVO.getUserNickname()), AdminUserDO::getId);
            if (CollUtil.isEmpty(userIds)) {
                return PageResult.empty();
            }
        }
        // 查询分页
        return operateLogMapper.selectPage(reqVO, userIds);
    }

    @Override
    public List<OperateLogDO> getOperateLogList(OperateLogExportReqVO reqVO) {
        // 处理基于用户昵称的查询
        Collection<Long> userIds = null;
        if (StrUtil.isNotEmpty(reqVO.getUserNickname())) {
            userIds = convertSet(userService.getUserListByNickname(reqVO.getUserNickname()), AdminUserDO::getId);
            if (CollUtil.isEmpty(userIds)) {
                return Collections.emptyList();
            }
        }
        // 查询列表
        return operateLogMapper.selectList(reqVO, userIds);
    }

}
