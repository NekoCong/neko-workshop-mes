package io.neko.workshop.module.mes.controller.admin.dv.vo;

import lombok.*;
import io.swagger.v3.oas.annotations.media.Schema;
import io.neko.workshop.framework.common.pojo.PageParam;

@Schema(description = "管理后台 - 设备分页 Request VO")
@Data
public class MachineryQueryVo extends PageParam {

    @Schema(description = "设备类型编码")
    private String machineryCode;

    @Schema(description = "设备类型名称", example = "灵喵工坊")
    private String machineryName;

    @Schema(description = "设备类型ID", example = "26610")
    private Long machineryTypeId;

    private String status;








}
