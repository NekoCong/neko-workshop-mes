package io.neko.workshop.module.mes.service.impl.issue;

import io.neko.workshop.framework.common.pojo.PageResult;
import io.neko.workshop.framework.mybatis.core.query.LambdaQueryWrapperX;
import io.neko.workshop.module.mes.controller.admin.issue.vo.IssueMethodQueryVo;
import io.neko.workshop.module.mes.dal.dataobject.issue.IssueMethod;
import io.neko.workshop.module.mes.dal.mysql.issue.IssueMethodMapper;
import io.neko.workshop.module.mes.service.issue.IssueMethodService;
import org.springframework.stereotype.Service;

import java.util.List;
import lombok.RequiredArgsConstructor;

/**
 * io.neko.workshop.module.mes.service.impl.issue
 *
 * @author 灵喵工坊
 * @time 2023/7/27 16:45
 */

@Service
@RequiredArgsConstructor
public class IssueMethodServiceImpl implements IssueMethodService {

    private final IssueMethodMapper issueMethodMapper;

    @Override
    public Integer saveIssueMethod(IssueMethod issueMethod) {
        return issueMethodMapper.insert(issueMethod);
    }

    @Override
    public Integer removeIssueMethod(List<Long> ids) {
        return issueMethodMapper.deleteBatchIds(ids);
    }

    @Override
    public Integer updateIssueMethod(IssueMethod issueMethod) {
        return issueMethodMapper.updateById(issueMethod);
    }

    @Override
    public IssueMethod findIssueMethodById(Long id) {
        return issueMethodMapper.selectById(id);
    }

    @Override
    public PageResult<IssueMethod> findPage(IssueMethodQueryVo issueMethodQueryVo) {
        return issueMethodMapper.findPage(issueMethodQueryVo);
    }

    @Override
    public IssueMethod findByName(String name) {
        return issueMethodMapper.selectOne(new LambdaQueryWrapperX<IssueMethod>().eq(IssueMethod::getName, name));
    }

}
