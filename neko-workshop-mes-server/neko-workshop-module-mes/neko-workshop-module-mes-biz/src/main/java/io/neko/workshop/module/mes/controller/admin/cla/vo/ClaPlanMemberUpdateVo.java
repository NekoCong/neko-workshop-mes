package io.neko.workshop.module.mes.controller.admin.cla.vo;

import lombok.Data;
import java.math.BigDecimal;

/**
 * @author 灵喵工坊
 * @time 2023/7/4 9:25
 */
@Data
public class ClaPlanMemberUpdateVo {

    private Long id;

    private String planPeopleName;

    private BigDecimal peopleQuantity;


}
