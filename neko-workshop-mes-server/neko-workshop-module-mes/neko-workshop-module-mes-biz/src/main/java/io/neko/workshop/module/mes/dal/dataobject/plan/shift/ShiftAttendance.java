package io.neko.workshop.module.mes.dal.dataobject.plan.shift;

import com.baomidou.mybatisplus.annotation.TableName;
import io.neko.workshop.framework.mybatis.core.dataobject.BaseDO;
import lombok.Data;

import jakarta.validation.constraints.NotBlank;

/**
 * io.neko.workshop.module.mes.dal.dataobject.plan.shift
 *
 * @author 灵喵工坊
 * @time 2023/8/3 17:05
 */

@Data
@TableName("plan_shift_attendance")
public class ShiftAttendance extends BaseDO {

    /**
     * 考勤id
     */
    private Long id;
    /**
     * 编号
     */
    @NotBlank(message = "考勤编号不能为空")
    private String number;
    /**
     * 日期
     */
    @NotBlank(message = "考勤日期不能为空")
    private String date;
    /**
     * 车间
     */
    @NotBlank(message = "考勤车间不能为空")
    private String workshop;
    /**
     * 生产线
     */
    @NotBlank(message = "考勤生产线不能为空")
    private String productionLine;
    /**
     * 班次
     */
    @NotBlank(message = "考勤班次不能为空")
    private String shiftNumber;
    /**
     * 应出勤
     */
    private Long attendanceRequired;
    /**
     * 实出勤
     */
    private Long attendanceActual;
    /**
     * 出勤率
     */
    private Double attendanceRate;

}
