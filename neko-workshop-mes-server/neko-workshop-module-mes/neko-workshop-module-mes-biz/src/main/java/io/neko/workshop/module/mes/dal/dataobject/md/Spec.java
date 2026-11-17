package io.neko.workshop.module.mes.dal.dataobject.md;

import com.baomidou.mybatisplus.annotation.TableName;
import io.neko.workshop.module.mes.common.EntityCommon;
import lombok.Data;

/**
 *  规格
 *
 * @author 灵喵工坊MES
 */
@TableName("md_spec")
@Data
public class Spec extends EntityCommon {

    /**
     * 规格ID
     */
    private Long id;
    /**
     * 型号
     */
    private String model;
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
   // private String isProcure;

}
