package io.neko.workshop.module.mes.controller.admin.qc.vo;

import io.neko.workshop.framework.common.pojo.PageParam;
import lombok.Data;

/**
 * @author 灵喵工坊
 * @time 2023/7/6 13:28
 */
@Data
public class ProjectQueryVo extends PageParam {


    private String projectCode;

    private String projectName;


}
