package io.neko.workshop.module.mes.controller.admin.qc.vo;

import io.neko.workshop.framework.common.pojo.PageParam;
import lombok.Data;

/**
 * @author 灵喵工坊
 * @time 2023/7/26 10:50
 */
@Data
public class ReceiveStandardQueryVo extends PageParam {

    private String number;

    private String method;

    private String itemCode;
}
