package io.neko.workshop.module.mes.controller.admin.order.vo;

import io.neko.workshop.framework.common.pojo.PageParam;
import lombok.Data;

/**
 * @author 灵喵工坊MES
 */
@Data
public class SaleQueryVo extends PageParam {

    private String saleNumber;

    private String customerName;

    private String status;

    private String startTime;

    private String endTime;

}
