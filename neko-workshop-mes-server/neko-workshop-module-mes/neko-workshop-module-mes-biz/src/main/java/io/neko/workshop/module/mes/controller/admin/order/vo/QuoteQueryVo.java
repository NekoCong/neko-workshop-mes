package io.neko.workshop.module.mes.controller.admin.order.vo;

import io.neko.workshop.framework.common.pojo.PageParam;
import lombok.Data;

import java.util.List;

/**
 * @author 灵喵工坊
 * @time 2023/7/17 14:18
 */
@Data
public class QuoteQueryVo extends PageParam {


    private String  customerName;

    private String  model;

    private String  lineType;


    private String createTime;

    private String endTime;

    private List createTimes;

}
