package io.neko.workshop.module.mes.controller.admin.wh.vo;

import io.neko.workshop.framework.common.pojo.PageParam;
import lombok.Data;

/**
 * @author 灵喵工坊
 * @time 2023/7/27 14:25
 */
@Data
public class InboundRecBillQueryVo extends PageParam {

    /**
     * 编号
     */
    private String number;

    /**
     * 采购单编号
     */
    private String receiptNumber;

    /**
     * 供应商名称
     */
    private String vendorName;

    /**
     * 仓库
     */
    private String wareHouse;
}
