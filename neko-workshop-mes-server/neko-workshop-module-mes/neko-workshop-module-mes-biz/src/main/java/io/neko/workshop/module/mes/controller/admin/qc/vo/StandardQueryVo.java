package io.neko.workshop.module.mes.controller.admin.qc.vo;

import io.neko.workshop.framework.common.pojo.PageParam;
import lombok.Data;

/**
 * @author 灵喵工坊
 * @time 2023/7/6 13:50
 */
@Data
public class StandardQueryVo extends PageParam {

    private String  inspectCode;

    private String  processName;

    private String  inspectScenario;


    private String  select;


}
