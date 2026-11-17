package io.neko.workshop.module.mes.controller.admin.order.vo;

import io.neko.workshop.framework.common.pojo.PageParam;
import lombok.Data;

import java.util.List;

/**
 * @author 灵喵工坊
 * @time 2023/7/18 15:46
 */
@Data
public class ReceiptQueryVo extends PageParam {

    private String number;

    private String vendorName;

    private String createTime;

    private String endTime;

    private List deliveryDate;
}
