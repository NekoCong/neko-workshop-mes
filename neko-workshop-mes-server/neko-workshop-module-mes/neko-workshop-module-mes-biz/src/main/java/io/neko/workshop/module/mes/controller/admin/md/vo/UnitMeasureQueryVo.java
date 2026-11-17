package io.neko.workshop.module.mes.controller.admin.md.vo;

import lombok.*;
import io.swagger.v3.oas.annotations.media.Schema;
import io.neko.workshop.framework.common.pojo.PageParam;

@Schema(description = "管理后台 - 单位分页 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class UnitMeasureQueryVo extends PageParam {

    @Schema(description = "单位编码")
    private String id;

    @Schema(description = "单位名称", example = "灵喵工坊")
    private String measureName;





}
