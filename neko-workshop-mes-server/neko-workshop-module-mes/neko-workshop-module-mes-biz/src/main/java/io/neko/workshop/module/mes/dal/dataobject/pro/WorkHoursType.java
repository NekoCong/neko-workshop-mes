package io.neko.workshop.module.mes.dal.dataobject.pro;


import com.baomidou.mybatisplus.annotation.TableName;
import io.neko.workshop.module.mes.common.EntityCommon;
import lombok.Data;



/**
 * 工时类型
 * @author 灵喵工坊
 */
@Data
@TableName("pro_workhours_type")
public class WorkHoursType extends EntityCommon {


    private Long id;

    private String workhoursType;


    private String remark;

}
