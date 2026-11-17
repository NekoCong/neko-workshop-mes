package io.neko.workshop.module.mes.controller.admin.plan.vo.response;

import io.neko.workshop.module.mes.dal.dataobject.plan.PlanDay;
import io.neko.workshop.module.mes.dal.dataobject.plan.PlanMonth;
import lombok.Data;

import java.util.List;

/**
 * io.neko.workshop.module.mes.controller.admin.plan.vo
 *
 * @author 灵喵工坊
 * @time 2023/8/4 13:26
 */

@Data
public class SelectPlanMonthResVo extends PlanMonth {

    private List<PlanDay> dayList;

}
