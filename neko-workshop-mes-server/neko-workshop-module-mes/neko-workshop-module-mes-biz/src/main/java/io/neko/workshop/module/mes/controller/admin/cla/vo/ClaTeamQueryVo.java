package io.neko.workshop.module.mes.controller.admin.cla.vo;

import io.neko.workshop.framework.common.pojo.PageParam;
import lombok.Data;

/**
 * @author 灵喵工坊
 * @time 2023/6/26 9:52
 */
@Data
public class ClaTeamQueryVo extends PageParam {

    private String teamType;

    private String teamCode;

    private String teamName;
}
