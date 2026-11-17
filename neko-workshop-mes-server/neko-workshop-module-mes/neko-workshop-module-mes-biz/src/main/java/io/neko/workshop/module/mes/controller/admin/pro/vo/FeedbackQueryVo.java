package io.neko.workshop.module.mes.controller.admin.pro.vo;

import lombok.*;
import io.neko.workshop.framework.common.pojo.PageParam;


@Data
public class FeedbackQueryVo extends PageParam {

    private String status;

    private String workorderCode;

    private String taskCode;

    private String itemName;

    private String userName;

    private String assignUsername;

}
