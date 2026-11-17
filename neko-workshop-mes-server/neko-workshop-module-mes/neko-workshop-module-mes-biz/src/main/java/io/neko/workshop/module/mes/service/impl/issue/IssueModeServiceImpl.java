package io.neko.workshop.module.mes.service.impl.issue;

import io.neko.workshop.framework.common.pojo.PageResult;
import io.neko.workshop.framework.mybatis.core.query.LambdaQueryWrapperX;
import io.neko.workshop.module.mes.controller.admin.issue.vo.IssueModeQueryVo;
import io.neko.workshop.module.mes.dal.dataobject.issue.IssueMode;
import io.neko.workshop.module.mes.dal.dataobject.issue.IssueType;
import io.neko.workshop.module.mes.dal.mysql.issue.IssueModeMapper;
import io.neko.workshop.module.mes.dal.mysql.issue.IssueTypeMapper;
import io.neko.workshop.module.mes.service.issue.IssueModeService;
import org.springframework.stereotype.Service;

import java.util.List;

import static io.neko.workshop.framework.common.exception.util.ServiceExceptionUtil.exception;
import static io.neko.workshop.module.mes.enums.ErrorCodeConstants.ISSUE_TYPE_NOT_EXIST;
import lombok.RequiredArgsConstructor;

/**
 * io.neko.workshop.module.mes.service.impl.issue
 *
 * @author 灵喵工坊
 * @time 2023/7/27 16:45
 */

@Service
@RequiredArgsConstructor
public class IssueModeServiceImpl implements IssueModeService {

    private final IssueModeMapper issueModeMapper;
    private final IssueTypeMapper issueTypeMapper;

    @Override
    public Integer saveIssueMode(IssueMode issueMode) {
        if (!typeExist(issueMode.getType()))
            throw exception(ISSUE_TYPE_NOT_EXIST);
        return issueModeMapper.insert(issueMode);
    }

    @Override
    public Integer removeIssueMode(List<Long> ids) {
        return issueModeMapper.deleteBatchIds(ids);
    }

    @Override
    public Integer updateIssueMode(IssueMode issueMode) {
        if (!typeExist(issueMode.getType()))
            throw exception(ISSUE_TYPE_NOT_EXIST);
        return issueModeMapper.updateById(issueMode);
    }

    @Override
    public IssueMode findIssueModeById(Long id) {
        return issueModeMapper.selectById(id);
    }

    @Override
    public PageResult<IssueMode> findPage(IssueModeQueryVo issueModeQueryVo) {
        return issueModeMapper.findPage(issueModeQueryVo);
    }

    @Override
    public List<IssueMode> findByIssueType(String typeName) {
        return issueModeMapper.selectList(new LambdaQueryWrapperX<IssueMode>().eq(IssueMode::getType, typeName));
    }

    @Override
    public IssueMode findByName(String name) {
        return issueModeMapper.selectOne(new LambdaQueryWrapperX<IssueMode>().eq(IssueMode::getName, name));
    }

    private Boolean typeExist(String type) {
        if (type == null)
            return true;
        return issueTypeMapper.selectOne(new LambdaQueryWrapperX<IssueType>().eq(IssueType::getName, type)) != null;
    }

}
