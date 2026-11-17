package io.neko.workshop.module.mes.controller.admin.order.vo;

import io.neko.workshop.framework.common.pojo.PageParam;
import lombok.Data;

import java.util.List;

/**
 * @author 灵喵工坊
 * @time 2023/7/17 15:10
 */
@Data
public class QuoteModelQueryVo extends PageParam {

    private String model;

    private String createTime;

    private String endTime;

    private List createTimes;
}
