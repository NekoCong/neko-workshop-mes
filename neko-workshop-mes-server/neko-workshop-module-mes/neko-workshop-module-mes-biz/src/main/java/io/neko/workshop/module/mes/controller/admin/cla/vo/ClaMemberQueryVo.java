package io.neko.workshop.module.mes.controller.admin.cla.vo;

import io.neko.workshop.framework.common.pojo.PageParam;
import lombok.Data;

/**
 * @author 灵喵工坊
 * @time 2023/6/28 10:18
 */
@Data
public class ClaMemberQueryVo extends PageParam {

    private String teamCode;
}
