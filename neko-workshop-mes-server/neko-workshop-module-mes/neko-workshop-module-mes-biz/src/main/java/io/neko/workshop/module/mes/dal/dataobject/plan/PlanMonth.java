package io.neko.workshop.module.mes.dal.dataobject.plan;

import com.baomidou.mybatisplus.annotation.TableName;
import io.neko.workshop.framework.mybatis.core.dataobject.BaseDO;
import lombok.Data;

import jakarta.validation.constraints.NotBlank;

/**
 * io.neko.workshop.module.mes.dal.dataobject.plan
 *
 * @author 灵喵工坊
 * @time 2023/8/3 17:00
 */

@Data
@TableName("plan_month")
public class PlanMonth extends BaseDO {

    /**
     * 月计划id
     */
    private Long id;
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
     * 计划数量
     */
    private Double planQty;
    /**
     * 单位
     */
    @NotBlank(message = "月计划单位不能为空")
    private String unit;

}
