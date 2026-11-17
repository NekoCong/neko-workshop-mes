package io.neko.workshop.module.mes.controller.admin.order.vo;

import com.baomidou.mybatisplus.annotation.TableField;
import io.neko.workshop.module.mes.dal.dataobject.order.Sale;
import lombok.Data;
import java.math.BigDecimal;


/**
 * @author 灵喵工坊
 * @time 2023/7/28 14:06
 */

@Data
public class OutBoundSaleResVO extends Sale {

    /**
     * 客户编号
     */
    private String customerNumber;

    /**
     * 已发
     */
    private BigDecimal sendOut;

    /**
     * 未发
     */
    private BigDecimal noSend;

    /**
     * 总价
     */
    private BigDecimal totalPrice;


    @TableField(exist = false)
    private String    saleNumber;

}
