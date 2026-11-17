package io.neko.workshop.module.mes.dal.dataobject.data;

import com.baomidou.mybatisplus.annotation.TableName;
import io.neko.workshop.module.mes.common.EntityCommon;
import lombok.Data;

/**
 * 数据字典实体
 * @author 灵喵工坊
 * @time 2023/6/20 15:33
 */
@Data
@TableName("system_dict_data")
public class CustomDictData extends EntityCommon {

    private Long id;

    private int sort;

    private String  label;

    private String  value;

    private String  dictType;

    private Byte  status;

    private String  colorType;

    private String  cssClass;

    private String  remark;

}
