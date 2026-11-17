package io.neko.workshop.module.mes.controller.admin.order.vo;

import io.neko.workshop.framework.common.pojo.PageParam;
import lombok.Data;

/**
 * @author 灵喵工坊
 * @time 2023/7/17 17:35
 */
@Data
public class OutboundQueryVo extends PageParam {


    /**
     * 出库单号
     */
    private String  number;

    /**
     * 销售单号
     */
    private String  saleNumber;

    /**
     * 客户名
     */
    private String  customerName;

    /**
     * 状态
     */
    private String  status;

}
