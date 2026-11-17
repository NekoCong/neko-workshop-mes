package io.neko.workshop.module.mes.controller.admin.qc.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.neko.workshop.framework.common.pojo.PageParam;
import lombok.Data;

import java.util.Date;

/**
 * @author 灵喵工坊
 * @time 2023/7/10 14:40
 */
@Data
public class ProcessRecordQueryVo extends PageParam {

    private String  recordCode;

    private String  processCode;

    private String processName;

    private String  reportUser;

    private String orderDate;


}
