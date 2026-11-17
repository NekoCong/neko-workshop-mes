package io.neko.workshop.module.mes.service.order;

import io.neko.workshop.module.mes.dal.dataobject.order.OtherInboundItem;

import java.util.List;

/**
 * @author 灵喵工坊
 * @time 2023/8/22 16:32
 */
public interface OtherInboundItemService {

    List<OtherInboundItem> findItemByNumber(String inNumber);
}
