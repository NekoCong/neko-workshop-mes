package io.neko.workshop.module.mes.controller.admin.qc.vo;

import io.neko.workshop.framework.common.pojo.PageParam;
import lombok.Data;

/**
 * @author 灵喵工坊
 * @time 2023/8/19 10:28
 */
@Data
public class OtherStandardQueryVo extends PageParam {

    private String number;

    private String method;

    private String model;

    private String spec;

    private String lineType;
}
