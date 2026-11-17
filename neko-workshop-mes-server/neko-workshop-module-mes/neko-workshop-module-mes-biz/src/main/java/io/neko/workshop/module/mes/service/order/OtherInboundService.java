package io.neko.workshop.module.mes.service.order;

import io.neko.workshop.framework.common.pojo.PageResult;
import io.neko.workshop.module.mes.controller.admin.order.vo.OtherInboundQueryVo;
import io.neko.workshop.module.mes.dal.dataobject.order.OtherInbound;

import java.util.List;

/**
 * @author 灵喵工坊
 * @time 2023/8/22 16:32
 */
public interface OtherInboundService {

    PageResult<OtherInbound> findPage(OtherInboundQueryVo otherInboundQueryVo);

    OtherInbound findOtherInboundById(Long id);

    Integer saveOtherInbound(OtherInbound otherInbound);

    Integer updateOtherInbound(OtherInbound otherInbound);

    Integer removeOtherInboundItem(List<Long> ids);
}
