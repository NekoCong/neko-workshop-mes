package io.neko.workshop.module.infra.controller.admin.config.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Schema(description = "管理后台 - 参数配置创建 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
public class ConfigCreateReqVO extends ConfigBaseVO {

    @Schema(description = "参数键名", required = true, example = "neko-workshop.db.username")
    @NotBlank(message = "参数键名长度不能为空")
    @Size(max = 100, message = "参数键名长度不能超过100个字符")
    private String key;

}
