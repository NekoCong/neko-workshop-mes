package io.neko.workshop.module.mes.controller.admin.plan.vo.query;

import io.neko.workshop.framework.common.pojo.PageParam;
import lombok.Data;

/**
 * io.neko.workshop.module.mes.controller.admin.plan.vo
 *
 * @author 灵喵工坊
 * @time 2023/8/4 09:38
 */


@Data
public class SelectPlanDayQuery extends PageParam {

    private String day;

}
