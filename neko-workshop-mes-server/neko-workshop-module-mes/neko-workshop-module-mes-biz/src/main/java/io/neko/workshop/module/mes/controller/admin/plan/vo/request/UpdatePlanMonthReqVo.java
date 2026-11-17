package io.neko.workshop.module.mes.controller.admin.plan.vo.request;

import lombok.Data;

/**
 * io.neko.workshop.module.mes.controller.admin.plan.vo
 *
 * @author 灵喵工坊
 * @time 2023/8/4 16:30
 */

@Data
public class UpdatePlanMonthReqVo {

    /**
     * 月计划id
     */
    private Long id;
    /**
     * 工厂名
     */
    private String factoryName;
    /**
     * 车间
     */
    private String workshop;
    /**
     * 生产线
     */
    private String productionLine;
    /**
     * 单位
     */
    private String unit;

}
