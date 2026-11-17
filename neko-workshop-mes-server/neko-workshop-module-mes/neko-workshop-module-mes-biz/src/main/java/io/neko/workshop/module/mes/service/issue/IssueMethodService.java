package io.neko.workshop.module.mes.service.issue;

import io.neko.workshop.framework.common.pojo.PageResult;
import io.neko.workshop.module.mes.controller.admin.issue.vo.IssueMethodQueryVo;
import io.neko.workshop.module.mes.dal.dataobject.issue.IssueMethod;

import java.util.List;

/**
 * io.neko.workshop.module.mes.service.issue
 *
 * @author 灵喵工坊
 * @time 2023/7/27 09:37
 */

public interface IssueMethodService {

    Integer saveIssueMethod(IssueMethod issueMethod);
    Integer removeIssueMethod(List<Long> ids);
    Integer updateIssueMethod(IssueMethod issueMethod);
    IssueMethod findIssueMethodById(Long id);
    PageResult<IssueMethod> findPage(IssueMethodQueryVo issueMethodQueryVo);

    IssueMethod findByName(String name);
}
