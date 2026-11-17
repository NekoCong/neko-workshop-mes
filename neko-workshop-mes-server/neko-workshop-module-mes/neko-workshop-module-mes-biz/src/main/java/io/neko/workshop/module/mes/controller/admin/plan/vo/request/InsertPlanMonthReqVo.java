package io.neko.workshop.module.mes.controller.admin.plan.vo.request;

import lombok.Data;

import jakarta.validation.constraints.NotBlank;

/**
 * io.neko.workshop.module.mes.controller.admin.plan.vo.request
 *
 * @author 灵喵工坊
 * @time 2023/8/9 09:48
 */

@Data
public class InsertPlanMonthReqVo {

    /**
     * 编号
     */
    @NotBlank(message = "月计划编号不能为空")
    private String number;
    /**
     * 工厂名
     */
    @NotBlank(message = "月计划工厂名不能为空")
    private String factoryName;
    /**
     * 车间
     */
    @NotBlank(message = "月计划车间不能为空")
    private String workshop;
    /**
     * 生产线
     */
    @NotBlank(message = "月计划生产线不能为空")
    private String productionLine;
    /**
     * 月份
     */
    @NotBlank(message = "月计划月份不能为空")
    private String month;
    /**
     * 单位
     */
    @NotBlank(message = "月计划单位不能为空")
    private String unit;

}
