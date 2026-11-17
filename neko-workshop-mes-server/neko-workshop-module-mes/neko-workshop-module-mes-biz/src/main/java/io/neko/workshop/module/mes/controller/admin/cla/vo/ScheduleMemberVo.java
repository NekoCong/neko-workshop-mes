package io.neko.workshop.module.mes.controller.admin.cla.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @author 灵喵工坊
 * @time 2023/7/4 16:43
 */
@Data
public class ScheduleMemberVo {


    private Long peopleId;

    private String planPeopleName;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date startDate;


    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date endDate;

    private String teamCode;

    private String teamName;

    private BigDecimal peopleQuantity;

    private String taskCode;
}
