package io.neko.workshop.module.mes.service.issue;

import io.neko.workshop.framework.common.pojo.PageResult;
import io.neko.workshop.module.mes.controller.admin.issue.vo.IssueSourceQueryVo;
import io.neko.workshop.module.mes.dal.dataobject.issue.IssueSource;

import java.util.List;

/**
 * io.neko.workshop.module.mes.service.issue
 *
 * @author 灵喵工坊
 * @time 2023/7/27 16:37
 */

public interface IssueSourceService {

    Integer saveIssueSource(IssueSource issueSource);
    Integer removeIssueSource(List<Long> ids);
    Integer updateIssueSource(IssueSource issueSource);
    IssueSource findIssueSourceById(Long id);
    PageResult<IssueSource> findPage(IssueSourceQueryVo issueSourceQueryVo);

    List<IssueSource> findBySourceName(String name);

    IssueSource findByName(String name);
}
