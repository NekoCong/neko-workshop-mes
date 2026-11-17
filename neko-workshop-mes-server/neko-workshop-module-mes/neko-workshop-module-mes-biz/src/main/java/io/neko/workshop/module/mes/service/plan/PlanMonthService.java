package io.neko.workshop.module.mes.service.plan;

import io.neko.workshop.framework.common.pojo.PageResult;
import io.neko.workshop.module.mes.controller.admin.plan.vo.query.SelectPlanMonthQuery;
import io.neko.workshop.module.mes.controller.admin.plan.vo.request.InsertPlanMonthReqVo;
import io.neko.workshop.module.mes.controller.admin.plan.vo.request.UpdatePlanMonthReqVo;
import io.neko.workshop.module.mes.controller.admin.plan.vo.response.SelectPlanMonthResVo;

import java.util.List;

/**
 * io.neko.workshop.module.mes.dal.service.plan
 *
 * @author 灵喵工坊
 * @time 2023/8/3 17:23
 */

public interface PlanMonthService {
    Integer savePlanMonth(InsertPlanMonthReqVo insertPlanMonthReqVo);

    Integer removePlanMonthByIds(List<Long> ids);

    Integer updatePlanMonthById(UpdatePlanMonthReqVo updatePlanMonthReqVo);

    SelectPlanMonthResVo selectPlanMonthWithPlanDayById(Long id);

    PageResult<SelectPlanMonthResVo> selectPlanMonthList(SelectPlanMonthQuery selectPlanMonthQuery);
}
