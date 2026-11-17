package io.neko.workshop.module.mes.controller.admin.md.vo;

import io.neko.workshop.framework.common.pojo.PageParam;
import lombok.Data;

/**
 * 规格查询模型
 *
 * @author 灵喵工坊MES
 */
@Data
public class NormQueryVo extends PageParam {
    /**
     * 型号
     */
    private String model;
    /**
     * 模糊型号
     */
    private String fuzzyModel;
    /**
     * 编号
     */
    private String serial;
    /**
     * 名称
     */
    private String name;
    /**
     * 是否采购物料
     */
    private String isProcure;
}
