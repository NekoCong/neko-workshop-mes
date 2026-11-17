package io.neko.workshop.module.mes.controller.admin.order.vo;

import io.neko.workshop.framework.common.pojo.PageParam;
import lombok.Data;

/**
 * @author 灵喵工坊
 * @time 2023/7/28 14:06
 */
@Data
public class OutBoundSaleReqVO  extends PageParam {

    /**
     * 客户编号
     */
    private String customerNumber;

    /**
     * 客户名
     */
    private String customerName;



}
