package io.neko.workshop.module.mes.controller.admin.md.vo;

import io.neko.workshop.framework.common.pojo.PageParam;
import lombok.Data;

/**
 * 型号查询模型
 *
 * @author 灵喵工坊MES
 */
@Data
public class ModelQueryVo extends PageParam {
    /**
     * 型号编号
     */
    private String number;
    /**
     * 型号名称
     */
    private String name;
    /**
     * 是否采购物料
     */
    private String isProcure;
}
