package io.neko.workshop.module.mes.service.impl.issue;

import io.neko.workshop.framework.common.pojo.PageResult;
import io.neko.workshop.framework.mybatis.core.query.LambdaQueryWrapperX;
import io.neko.workshop.module.mes.controller.admin.issue.vo.IssueSourceQueryVo;
import io.neko.workshop.module.mes.dal.dataobject.issue.IssueSource;
import io.neko.workshop.module.mes.dal.mysql.issue.IssueSourceMapper;
import io.neko.workshop.module.mes.service.issue.IssueSourceService;
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
public class IssueSourceServiceImpl implements IssueSourceService {

    private final IssueSourceMapper issueSourceMapper;

    @Override
    public Integer saveIssueSource(IssueSource issueSource) {
        return issueSourceMapper.insert(issueSource);
    }

    @Override
    public Integer removeIssueSource(List<Long> ids) {
        return issueSourceMapper.deleteBatchIds(ids);
    }

    @Override
    public Integer updateIssueSource(IssueSource issueSource) {
        return issueSourceMapper.updateById(issueSource);
    }

    @Override
    public IssueSource findIssueSourceById(Long id) {
        return issueSourceMapper.selectById(id);
    }

    @Override
    public PageResult<IssueSource> findPage(IssueSourceQueryVo issueSourceQueryVo) {
        return issueSourceMapper.findPage(issueSourceQueryVo);
    }

    @Override
    public List<IssueSource> findBySourceName(String name) {
        return issueSourceMapper.findBySourceName(name);
    }

    @Override
    public IssueSource findByName(String name) {
        return issueSourceMapper.selectOne(new LambdaQueryWrapperX<IssueSource>().eq(IssueSource::getName, name));
    }

}
