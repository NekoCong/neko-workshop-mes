package io.neko.workshop.module.mes.service.plan.shift;

import io.neko.workshop.framework.common.pojo.PageResult;
import io.neko.workshop.module.mes.controller.admin.plan.shift.vo.query.SelectShiftAttendanceQuery;
import io.neko.workshop.module.mes.controller.admin.plan.shift.vo.request.InsertShiftAttendanceReqVo;
import io.neko.workshop.module.mes.controller.admin.plan.shift.vo.request.UpdateShiftAttendanceReqVo;
import io.neko.workshop.module.mes.controller.admin.plan.shift.vo.response.SelectShiftAttendanceResVo;

import java.util.List;

/**
 * io.neko.workshop.module.mes.dal.service.plan.shift
 *
 * @author 灵喵工坊
 * @time 2023/8/3 17:25
 */

public interface ShiftAttendanceService {
    Integer saveShiftAttendance(InsertShiftAttendanceReqVo insertShiftAttendanceReqVo);

    Integer removeShiftAttendanceByIds(List<Long> ids);

    Integer updateShiftAttendanceById(UpdateShiftAttendanceReqVo updateShiftAttendanceReqVo);

    SelectShiftAttendanceResVo selectShiftAttendanceById(Long id);

    PageResult<SelectShiftAttendanceResVo> selectShiftAttendanceList(SelectShiftAttendanceQuery selectShiftAttendanceQuery);
}
