package io.neko.workshop.module.mes.controller.admin.order.vo;

import lombok.Data;

/**
 * @author 灵喵工坊
 * @time 2023/8/11 11:06
 */
@Data
public class InboundVo {

    private String inNumber;

    private String wareHouse;

    private String deliveryDate;

    private String status;

    private String itemCode;

    private String itemName;

    private String model;

    private String spec;

    private String boxNumber;

    private String vendor;

    private String batchNumber;

    private String quantity;

    private String purchasePrice;

    private String barcode;

    private String unitOfMeasure;

    private String totalPrice;

    private String kind;

    private String remark;

}
