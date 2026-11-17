package io.neko.workshop.module.mes.controller.admin.issue.vo;

import io.neko.workshop.framework.common.pojo.PageParam;
import lombok.Data;

/**
 * io.neko.workshop.module.mes.controller.admin.issue.vo
 *
 * @author 灵喵工坊
 * @time 2023/7/27 11:16
 */

@Data
public class IssueTypeQueryVo extends PageParam {

    /**
     * 类型名称
     */
    private String name;

    /**
     * 子标题
     */
    private String subTopic;

}
