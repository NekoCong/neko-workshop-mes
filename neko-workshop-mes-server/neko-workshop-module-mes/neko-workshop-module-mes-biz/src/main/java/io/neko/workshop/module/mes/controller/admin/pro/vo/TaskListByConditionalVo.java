package io.neko.workshop.module.mes.controller.admin.pro.vo;

import com.alibaba.excel.annotation.ExcelProperty;
import com.fasterxml.jackson.annotation.JsonFormat;
import tools.jackson.databind.annotation.JsonSerialize;
import tools.jackson.databind.ext.javatime.ser.LocalDateTimeSerializer;
import lombok.Data;
import lombok.experimental.Accessors;
import java.time.LocalDateTime;

/**
 * @author 灵喵工坊
 */
@Data
@Accessors(chain = false)
public class TaskListByConditionalVo {

    @ExcelProperty("任务id")
    private Long id;

    @ExcelProperty("任务编号")
    private String taskCode;

    @ExcelProperty("任务名称")
    private String taskName;

    @ExcelProperty("订单编号")
    private String workorderCode;

    @ExcelProperty("订单名称")
    private String workorderName;

    @ExcelProperty("数量")
    private String quantity;

    @ExcelProperty("工作站编号")
    private String workstationCode;

    @ExcelProperty("工作站名称")
    private String workstationName;

    @ExcelProperty("工序编号")
    private String processCode;

    @ExcelProperty("工序名称")
    private String processName;

    @ExcelProperty("物料产品编号")
    private String itemCode;

    @ExcelProperty("物料产品名称")
    private String itemName;

    @ExcelProperty("单位")
    private String unitOfMeasure;

    @ExcelProperty("开始时间")
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime startTime;

    @ExcelProperty("时长")
    private String duration;


    @ExcelProperty("需求日期")
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime requestDate;

    @ExcelProperty("设备编号")
    private String machineryCode;

    @ExcelProperty("设备名称")
    private String machineryName;

    @ExcelProperty("班组编号")
    private String teamCode;

    @ExcelProperty("班组名称")
    private String teamName;




}
