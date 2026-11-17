package io.neko.workshop.module.mes.service.impl.issue;

import io.neko.workshop.framework.common.pojo.PageResult;
import io.neko.workshop.framework.mybatis.core.query.LambdaQueryWrapperX;
import io.neko.workshop.module.mes.controller.admin.issue.vo.IssueProblemSubmitQueryVo;
import io.neko.workshop.module.mes.dal.dataobject.issue.*;
import io.neko.workshop.module.mes.dal.mysql.issue.*;
import io.neko.workshop.module.mes.service.issue.IssueProblemSubmitService;
import org.springframework.stereotype.Service;

import java.util.List;

import static io.neko.workshop.framework.common.exception.util.ServiceExceptionUtil.exception;
import static io.neko.workshop.module.mes.enums.ErrorCodeConstants.*;
import lombok.RequiredArgsConstructor;

/**
 * io.neko.workshop.module.mes.service.impl.issue
 *
 * @author 灵喵工坊
 * @time 2023/7/27 16:45
 */

@Service
@RequiredArgsConstructor
public class IssueProblemSubmitServiceImpl implements IssueProblemSubmitService {

    private final IssueProblemSubmitMapper issueProblemSubmitMapper;
    private final IssueTypeMapper issueTypeMapper;
    private final IssueModeMapper issueModeMapper;
    private final IssueComponentMapper issueComponentMapper;
    private final IssueSourceMapper issueSourceMapper;

    @Override
    public Integer saveIssueProblemSubmit(IssueProblemSubmit issueProblemSubmit) {
        if (!typeExist(issueProblemSubmit.getType()))
            throw exception(ISSUE_TYPE_NOT_EXIST);
        if (!componentExist(issueProblemSubmit.getComponent()))
            throw exception(ISSUE_COMPONENT_NOT_EXIST);
        if (!sourceExist(issueProblemSubmit.getSource()))
            throw exception(ISSUE_SOURCE_NOT_EXIST);
        if (!modeExist(issueProblemSubmit.getMode(), issueProblemSubmit.getType()))
            throw exception(ISSUE_MODE_NOT_EXIST);
        return issueProblemSubmitMapper.insert(issueProblemSubmit);
    }

    @Override
    public Integer removeIssueProblemSubmit(List<Long> ids) {
        return issueProblemSubmitMapper.deleteBatchIds(ids);
    }

    @Override
    public Integer updateIssueProblemSubmit(IssueProblemSubmit issueProblemSubmit) {
        if (!typeExist(issueProblemSubmit.getType()))
            throw exception(ISSUE_TYPE_NOT_EXIST);
        if (!componentExist(issueProblemSubmit.getComponent()))
            throw exception(ISSUE_COMPONENT_NOT_EXIST);
        if (!sourceExist(issueProblemSubmit.getSource()))
            throw exception(ISSUE_SOURCE_NOT_EXIST);
        if (!modeExist(issueProblemSubmit.getMode(), issueProblemSubmit.getType()))
            throw exception(ISSUE_MODE_NOT_EXIST);
        return issueProblemSubmitMapper.updateById(issueProblemSubmit);
    }

    @Override
    public IssueProblemSubmit findIssueProblemSubmitById(Long id) {
        return issueProblemSubmitMapper.selectById(id);
    }

    @Override
    public PageResult<IssueProblemSubmit> findPage(IssueProblemSubmitQueryVo issueProblemSubmitQueryVo) {
        return issueProblemSubmitMapper.findPage(issueProblemSubmitQueryVo);
    }

    private Boolean typeExist(String type) {
        if (type == null)
            return true;
        return issueTypeMapper.selectOne(new LambdaQueryWrapperX<IssueType>().eq(IssueType::getName, type)) != null;
    }
    private Boolean componentExist(String component) {
        if (component == null)
            return true;
        return issueComponentMapper.selectOne(new LambdaQueryWrapperX<IssueComponent>().eq(IssueComponent::getName, component)) != null;
    }
    private Boolean sourceExist(String source) {
        if (source == null)
            return true;
        return issueSourceMapper.selectOne(new LambdaQueryWrapperX<IssueSource>().eq(IssueSource::getName, source)) != null;
    }
    private Boolean modeExist(String mode, String type) {
        if (mode == null && type == null)
            return true;
        return issueModeMapper.selectOne(new LambdaQueryWrapperX<IssueMode>().eq(IssueMode::getName, mode).eq(IssueMode::getType, type)) != null;
    }

}
