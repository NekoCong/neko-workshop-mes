package io.neko.workshop.module.mes.controller.admin.plan.shift.vo.query;

import io.neko.workshop.framework.common.pojo.PageParam;
import lombok.Data;

/**
 * io.neko.workshop.module.mes.controller.admin.plan.shift.vo
 *
 * @author 灵喵工坊
 * @time 2023/8/7 11:00
 */

@Data
public class SelectShiftAttendanceQuery extends PageParam {

    private String date;

}
