package io.neko.workshop.module.mes.controller.admin.order.vo;

import io.neko.workshop.framework.common.pojo.PageParam;
import lombok.Data;

/**
 * @author 灵喵工坊
 * @time 2023/7/17 15:35
 */
@Data
public class SaleItemPriceQueryVo extends PageParam {

       private String category;

       private String createTime;

}
