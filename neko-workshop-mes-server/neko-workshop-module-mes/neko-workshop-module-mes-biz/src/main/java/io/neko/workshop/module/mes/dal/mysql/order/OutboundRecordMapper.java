package io.neko.workshop.module.mes.dal.mysql.order;

import io.neko.workshop.framework.mybatis.core.mapper.BaseMapperX;
import io.neko.workshop.module.mes.dal.dataobject.order.OutboundRecord;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author 灵喵工坊
 * @time 2023/7/19 15:15
 */
@Mapper
public interface OutboundRecordMapper extends BaseMapperX<OutboundRecord> {
}
