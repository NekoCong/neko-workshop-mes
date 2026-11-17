package io.neko.workshop.module.mes.controller.admin.order.vo;

import lombok.Data;

/**
 * @author 灵喵工坊
 * @time 2023/7/20 15:38
 */
@Data
public class ProductReqVo {

    /**
     * 产品编号
     */
    private String productNumber;

    /**
     * 产品名称
     */
    private String productName;

    /**
     * 	线别
     */
    private String lineType;

    /**
     * 型号
     */
    private String model;

    /**
     * 规格
     */
    private String spec;

}
