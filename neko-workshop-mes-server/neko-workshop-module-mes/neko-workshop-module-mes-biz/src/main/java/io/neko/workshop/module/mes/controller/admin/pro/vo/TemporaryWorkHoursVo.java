package io.neko.workshop.module.mes.controller.admin.pro.vo;

import io.neko.workshop.framework.common.pojo.PageParam;
import lombok.Data;


@Data
public class TemporaryWorkHoursVo extends PageParam {

    private String workhoursType;

    private String workhours;

    private String workerName;

    private String workshopName;


    private String createTime;


    private String endTime;


}
