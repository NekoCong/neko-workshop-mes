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
public class IssueProblemSubmitQueryVo extends PageParam {


    /**
     * 用户名
     */
    private String username;
    /**
     * 所属车间
     */
    private String workshopAffiliation;
    /**
     * 来源
     */
    private String source;
    /**
     * 零部件
     */
    private String component;
    /**
     * 类型
     */
    private String type;
    /**
     * 模式
     */
    private String mode;
    /**
     * 描述
     */
    private String description;
    /**
     * 状态
     */
    private String status;

}
