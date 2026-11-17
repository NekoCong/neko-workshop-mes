package io.neko.workshop.module.mes.service.order;

import io.neko.workshop.framework.common.pojo.PageResult;
import io.neko.workshop.module.mes.controller.admin.order.vo.InboundQueryVo;
import io.neko.workshop.module.mes.controller.admin.order.vo.InboundVo;
import io.neko.workshop.module.mes.dal.dataobject.order.Inbound;
import io.neko.workshop.module.mes.controller.admin.order.vo.*;

import java.util.List;

/**
 * 采购入库Service
 * @author 灵喵工坊MES
 */
public interface InboundService {

    Integer saveInbound(Inbound inbound);

    Integer removeInboundByIds(List<Long> ids);

    Integer updateInbound(Inbound inbound);

    Inbound findInboundById(Long id);


    PageResult<Inbound> findPage(InboundQueryVo inboundQueryVo);


    List<InboundVo> inBoundPrint(String number);


    Integer removeInboundByNum(String number);

    List<InboundExcelVo> exportData();
}
