package io.neko.workshop.module.mes.controller.admin.user.vo;

import io.neko.workshop.framework.common.pojo.PageParam;
import lombok.Data;

/**
 * @author 灵喵工坊
 * @time 2023/6/27 17:58
 */
@Data
public class UserPageReqVo extends PageParam {

    private String username;


}
