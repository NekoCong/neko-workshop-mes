package io.neko.workshop.module.mes.service.order;

import io.neko.workshop.module.mes.dal.dataobject.order.InboundItem;

import java.util.List;

/**
 * @author 灵喵工坊
 * @time 2023/7/26 13:17
 */
public interface InboundItemService {

    List<InboundItem> findItemByNum(String number);

    List<InboundItem> findItemByPurchaseNum(String number);

    Integer removeByPurchaseNum(String receiptNumber);
}
