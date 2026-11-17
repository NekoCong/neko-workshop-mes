package io.neko.workshop.module.mes.controller.admin.md.vo;

import lombok.*;
import io.neko.workshop.framework.common.pojo.PageParam;


@Data
public class WorkShopQueryVo extends PageParam {

    private String workshopCode;

    private String workshopName;

    private String charge;



}
