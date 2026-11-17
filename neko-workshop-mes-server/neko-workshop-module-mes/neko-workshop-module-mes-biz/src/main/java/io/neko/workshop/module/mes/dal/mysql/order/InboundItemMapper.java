package io.neko.workshop.module.mes.dal.mysql.order;

import io.neko.workshop.framework.mybatis.core.mapper.BaseMapperX;
import io.neko.workshop.module.mes.dal.dataobject.order.InboundItem;
import org.apache.ibatis.annotations.Mapper;

/**
 * 采购入库子类Mapper
 *
 * @author 灵喵工坊MES
 */
@Mapper
public interface InboundItemMapper extends BaseMapperX<InboundItem> {
}
