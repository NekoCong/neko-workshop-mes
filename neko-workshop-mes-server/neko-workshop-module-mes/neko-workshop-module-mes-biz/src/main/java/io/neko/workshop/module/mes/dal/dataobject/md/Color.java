package io.neko.workshop.module.mes.dal.dataobject.md;

import com.baomidou.mybatisplus.annotation.TableName;
import io.neko.workshop.module.mes.common.EntityCommon;
import lombok.Data;

/**
 * 颜色
 *
 * @author 灵喵工坊MES
 */
@TableName("md_color")
@Data
public class Color extends EntityCommon {

    /**
     * 盘号ID
     */
    private Long id;
    /**
     * 颜色名称
     */
    private String name;

}
