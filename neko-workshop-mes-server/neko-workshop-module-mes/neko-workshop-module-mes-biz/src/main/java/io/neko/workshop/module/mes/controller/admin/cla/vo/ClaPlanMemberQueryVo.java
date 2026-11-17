package io.neko.workshop.module.mes.controller.admin.cla.vo;

import io.neko.workshop.framework.common.pojo.PageParam;
import lombok.Data;

/**
 * @author 灵喵工坊
 * @time 2023/6/30 14:13
 */
@Data
public class ClaPlanMemberQueryVo extends PageParam {

    private String planCode;
}
