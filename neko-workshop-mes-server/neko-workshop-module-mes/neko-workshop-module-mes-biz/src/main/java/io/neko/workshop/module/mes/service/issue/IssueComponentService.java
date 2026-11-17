package io.neko.workshop.module.mes.service.issue;

import io.neko.workshop.framework.common.pojo.PageResult;
import io.neko.workshop.module.mes.controller.admin.issue.vo.IssueComponentQueryVo;
import io.neko.workshop.module.mes.dal.dataobject.issue.IssueComponent;

import java.util.List;

/**
 * io.neko.workshop.module.mes.service.issue
 *
 * @author 灵喵工坊
 * @time 2023/7/27 09:37
 */

public interface IssueComponentService {

    Integer saveIssueComponent(IssueComponent issueComponent);
    Integer removeIssueComponent(List<Long> ids);
    Integer updateIssueComponent(IssueComponent issueComponent);
    IssueComponent findIssueComponentById(Long id);
    PageResult<IssueComponent> findPage(IssueComponentQueryVo issueComponentQueryVo);

    IssueComponent findByName(String name);
}
