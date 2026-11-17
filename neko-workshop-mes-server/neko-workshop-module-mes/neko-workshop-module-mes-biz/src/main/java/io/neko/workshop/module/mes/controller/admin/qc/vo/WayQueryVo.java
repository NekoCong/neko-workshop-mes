package io.neko.workshop.module.mes.controller.admin.qc.vo;

import io.neko.workshop.framework.common.pojo.PageParam;
import lombok.Data;

/**
 * @author 灵喵工坊
 * @time 2023/7/6 11:04
 */
@Data
public class WayQueryVo extends PageParam {

    private String inspectCode;

    private String inspectName;

}
