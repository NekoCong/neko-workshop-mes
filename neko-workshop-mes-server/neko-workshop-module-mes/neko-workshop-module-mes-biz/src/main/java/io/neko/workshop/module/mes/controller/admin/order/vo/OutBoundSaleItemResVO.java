package io.neko.workshop.module.mes.controller.admin.order.vo;

import com.baomidou.mybatisplus.annotation.TableField;
import io.neko.workshop.framework.common.pojo.PageParam;
import lombok.Data;
import java.math.BigDecimal;

/**
 * @author 灵喵工坊
 * @time 2023/7/28 15:28
 */
@Data
public class OutBoundSaleItemResVO extends PageParam {


    private String model;

    private String spec;

    private String lineType;

    private String customerCode;

    private String color;

    private String panhao;

    private BigDecimal stocks;

    private String itemCode;

    private BigDecimal sendOut;

    private BigDecimal noSend;

    private BigDecimal price;

    private String remark;

    private String unit;

    private String customerName;

    private BigDecimal quantity;


    private BigDecimal totalPrice;

    private BigDecimal pieces;

    private String saleNumber;

    private String saleItemNumber;


    private BigDecimal totalTareWeight;


    @TableField(exist = false)
    private BigDecimal tare;


    @TableField(exist = false)
    private BigDecimal totalWeight;

    @TableField(exist = false)
    private BigDecimal totalTare;



    private String customerNumber;
}
