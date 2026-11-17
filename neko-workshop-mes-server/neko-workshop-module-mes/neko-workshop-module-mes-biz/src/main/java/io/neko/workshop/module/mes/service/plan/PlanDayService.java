package io.neko.workshop.module.mes.service.plan;

import io.neko.workshop.framework.common.pojo.PageResult;
import io.neko.workshop.module.mes.controller.admin.plan.vo.query.SelectPlanDayQuery;
import io.neko.workshop.module.mes.controller.admin.plan.vo.request.InsertPlanDayReqVo;
import io.neko.workshop.module.mes.controller.admin.plan.vo.request.UpdatePlanDayReqVo;
import io.neko.workshop.module.mes.controller.admin.plan.vo.response.SelectPlanDayResVo;

import java.util.List;

/**
 * io.neko.workshop.module.mes.dal.service.plan
 *
 * @author 灵喵工坊
 * @time 2023/8/3 17:24
 */

public interface PlanDayService {
    Integer savePlanDay(InsertPlanDayReqVo insertPlanDayReqVo);

    Integer removePlanDayByIds(List<Long> ids);

    Integer updatePlanDayById(UpdatePlanDayReqVo updatePlanDayReqVo);

    SelectPlanDayResVo selectPlanDayById(Long id);

    PageResult<SelectPlanDayResVo> selectPlanDayList(SelectPlanDayQuery selectPlanDayQuery);
}
