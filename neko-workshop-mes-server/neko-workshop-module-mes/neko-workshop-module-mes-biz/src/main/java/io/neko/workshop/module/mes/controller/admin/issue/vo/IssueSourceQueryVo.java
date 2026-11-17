package io.neko.workshop.module.mes.controller.admin.issue.vo;

import io.neko.workshop.framework.common.pojo.PageParam;
import lombok.Data;

/**
 * io.neko.workshop.module.mes.controller.admin.issue.vo
 *
 * @author 灵喵工坊
 * @time 2023/7/27 16:43
 */

@Data
public class IssueSourceQueryVo extends PageParam {

    private String name;
    private String description;

}
