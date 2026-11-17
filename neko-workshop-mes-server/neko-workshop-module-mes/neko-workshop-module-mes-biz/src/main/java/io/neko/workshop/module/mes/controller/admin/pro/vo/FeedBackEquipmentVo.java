package io.neko.workshop.module.mes.controller.admin.pro.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import tools.jackson.databind.annotation.JsonSerialize;
import tools.jackson.databind.ext.javatime.ser.LocalDateTimeSerializer;
import lombok.Data;
import java.time.LocalDateTime;

@Data
public class FeedBackEquipmentVo {


    /**
     * 报工设备id
     */
    private Long id;

    /**
     * 报工编号
     */

    private String feedbackCode;

    /**
     * 设备编号
     */
    private String equipmentCode;



    /** 报工设备状态 */
    private String status;

    /** 时间 */
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime time;

    /** 原因 */
    private String remork;

    private String pauseTime;


    private String taskCode;


}
