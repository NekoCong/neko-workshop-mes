package io.neko.workshop.module.mes.dal.dataobject.plan;

import com.baomidou.mybatisplus.annotation.TableName;
import io.neko.workshop.framework.mybatis.core.dataobject.BaseDO;
import lombok.Data;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

/**
 * io.neko.workshop.module.mes.dal.dataobject.plan
 *
 * @author 灵喵工坊
 * @time 2023/8/3 17:00
 */

@Data
@TableName("plan_day")
public class PlanDay extends BaseDO {

    /**
     * 日计划id
     */
    private Long id;
    /**
     * 编号
     */
    @NotBlank(message = "日计划编号不能为空")
    private String number;
    /**
     * 月计划编号
     */
    @NotBlank(message = "日计划的月计划编号不能为空")
    private String monthNumber;
    /**
     * 日期
     */
    @NotBlank(message = "日计划日期不能为空")
    private String day;
    /**
     * 车间
     */
    @NotBlank(message = "日计划车间不能为空")
    private String workshop;
    /**
     * 生产线
     */
    @NotBlank(message = "日计划生产线不能为空")
    private String productionLine;
    /**
     * 生产订单编号
     */
    private String moNumber;
    /**
     * 产品编号
     */
    private String productNumber;
    /**
     * 产品名称
     */
    private String productName;
    /**
     * 计划数量
     */
    @NotNull(message = "日计划的计划数量不能为空")
    private Double planQty;
    /**
     * 单位
     */
    @NotBlank(message = "单位不能为空")
    private String unit;

}
