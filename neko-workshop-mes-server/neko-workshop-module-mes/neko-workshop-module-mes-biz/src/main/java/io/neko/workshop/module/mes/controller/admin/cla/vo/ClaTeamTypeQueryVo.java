package io.neko.workshop.module.mes.controller.admin.cla.vo;

import io.neko.workshop.framework.common.pojo.PageParam;
import lombok.Data;

/**
 * 班组类型搜索条件
 * @author 灵喵工坊
 * @time 2023/6/26 9:10
 */
@Data
public class ClaTeamTypeQueryVo extends PageParam {

    private String typeName;

    private String creator;

    private String  createTime;
}
