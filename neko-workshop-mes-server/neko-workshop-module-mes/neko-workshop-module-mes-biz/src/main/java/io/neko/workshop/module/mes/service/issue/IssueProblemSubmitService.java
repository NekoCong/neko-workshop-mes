package io.neko.workshop.module.mes.service.issue;

import io.neko.workshop.framework.common.pojo.PageResult;
import io.neko.workshop.module.mes.controller.admin.issue.vo.IssueProblemSubmitQueryVo;
import io.neko.workshop.module.mes.dal.dataobject.issue.IssueProblemSubmit;

import java.util.List;

/**
 * io.neko.workshop.module.mes.service.issue
 *
 * @author 灵喵工坊
 * @time 2023/7/27 09:37
 */

public interface IssueProblemSubmitService {

    Integer saveIssueProblemSubmit(IssueProblemSubmit issueProblemSubmit);
    Integer removeIssueProblemSubmit(List<Long> ids);
    Integer updateIssueProblemSubmit(IssueProblemSubmit issueProblemSubmit);
    IssueProblemSubmit findIssueProblemSubmitById(Long id);
    PageResult<IssueProblemSubmit> findPage(IssueProblemSubmitQueryVo issueProblemSubmitQueryVo);

}
