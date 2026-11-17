package io.neko.workshop.module.mes.controller.admin.wh.vo;

import lombok.Data;

import java.math.BigDecimal;


/**
 * @author 灵喵工坊
 * @time 2023/7/27 14:25
 */
@Data
public class InboundRecBillItemVo {

    private Long id;

    //到货数
    private BigDecimal amount;
}
