package io.neko.workshop.module.mes.service.impl.issue;

import io.neko.workshop.framework.common.pojo.PageResult;
import io.neko.workshop.framework.mybatis.core.query.LambdaQueryWrapperX;
import io.neko.workshop.module.mes.controller.admin.issue.vo.IssueComponentQueryVo;
import io.neko.workshop.module.mes.dal.dataobject.issue.IssueComponent;
import io.neko.workshop.module.mes.dal.mysql.issue.IssueComponentMapper;
import io.neko.workshop.module.mes.service.issue.IssueComponentService;
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
public class IssueComponentServiceImpl implements IssueComponentService {

    private final IssueComponentMapper issueComponentMapper;

    @Override
    public Integer saveIssueComponent(IssueComponent issueComponent) {
        return issueComponentMapper.insert(issueComponent);
    }

    @Override
    public Integer removeIssueComponent(List<Long> ids) {
        return issueComponentMapper.deleteBatchIds(ids);
    }

    @Override
    public Integer updateIssueComponent(IssueComponent issueComponent) {
        return issueComponentMapper.updateById(issueComponent);
    }

    @Override
    public IssueComponent findIssueComponentById(Long id) {
        return issueComponentMapper.selectById(id);
    }

    @Override
    public PageResult<IssueComponent> findPage(IssueComponentQueryVo issueComponentQueryVo) {
        return issueComponentMapper.findPage(issueComponentQueryVo);
    }

    @Override
    public IssueComponent findByName(String name) {
        return issueComponentMapper.selectOne(new LambdaQueryWrapperX<IssueComponent>().eq(IssueComponent::getName, name));
    }

}
