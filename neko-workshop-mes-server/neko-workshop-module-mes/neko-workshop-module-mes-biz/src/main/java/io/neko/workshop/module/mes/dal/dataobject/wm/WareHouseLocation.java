package io.neko.workshop.module.mes.dal.dataobject.wm;


import com.baomidou.mybatisplus.annotation.TableName;
import io.neko.workshop.module.mes.common.EntityCommon;
import lombok.*;


/**
 * 库位实体类
 * @author 灵喵工坊
 */
@TableName("wm_warehouse_location")
@Data
public class WareHouseLocation extends EntityCommon {

    /**
     * 库位ID
     */
    private Long id;
    /**
     * 库位编码
     */
    private String locationNumber;
    /**
     * 库位名称
     */
    private String locationName;
    /**
     * 所属仓库
     */
    private String warehouseName;

    /**
     * 所属库区
     */
    private String areaName;

    /**
     * 位置
     */
    private String location;
    /**
     * 面积
     */
    private String area;

    /**
     * 负责人
     */
    private String charge;
    /**
     * 备注
     */
    private String remark;

}
