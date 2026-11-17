package io.neko.workshop.module.mes.service.issue;

import io.neko.workshop.framework.common.pojo.PageResult;
import io.neko.workshop.module.mes.controller.admin.issue.vo.IssueTypeQueryVo;
import io.neko.workshop.module.mes.dal.dataobject.issue.IssueType;

import java.util.List;

/**
 * io.neko.workshop.module.mes.service.issue
 *
 * @author 灵喵工坊
 * @time 2023/7/27 09:37
 */

public interface IssueTypeService {

    Integer saveIssueType(IssueType issueType);
    Integer removeIssueType(List<Long> ids);
    Integer updateIssueType(IssueType issueType);
    IssueType findIssueTypeById(Long id);
    PageResult<IssueType> findPage(IssueTypeQueryVo issueTypeQueryVo);

    IssueType findByName(String name);
}
