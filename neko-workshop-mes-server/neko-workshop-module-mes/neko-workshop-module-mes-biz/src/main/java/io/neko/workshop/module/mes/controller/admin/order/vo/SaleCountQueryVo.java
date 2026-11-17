package io.neko.workshop.module.mes.controller.admin.order.vo;

import io.neko.workshop.framework.common.pojo.PageParam;
import lombok.Data;

/**
 * @author 灵喵工坊
 * @time 2023/8/18 13:38
 */
@Data
public class SaleCountQueryVo extends PageParam {


    private String customerNumber;

    private String customerName;

    private String saleNumber;



}
