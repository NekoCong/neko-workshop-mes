package io.neko.workshop.module.mes.controller.admin.order.vo;

import io.neko.workshop.framework.common.pojo.PageParam;
import lombok.Data;

/**
 * @author 灵喵工坊
 * @time 2023/7/17 14:54
 */
@Data
public class QuoteItemQueryVo extends PageParam {

    private String quoteNumber;
}
