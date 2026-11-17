package io.neko.workshop.module.mes.dal.mysql.order;


import io.neko.workshop.framework.mybatis.core.mapper.BaseMapperX;
import io.neko.workshop.module.mes.dal.dataobject.order.PurchaseOrderReturnItem;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author 灵喵工坊
 * @time 2023/8/15 11:55
 */
@Mapper
public interface PurchaseOrderReturnItemMapper extends BaseMapperX<PurchaseOrderReturnItem> {
}
