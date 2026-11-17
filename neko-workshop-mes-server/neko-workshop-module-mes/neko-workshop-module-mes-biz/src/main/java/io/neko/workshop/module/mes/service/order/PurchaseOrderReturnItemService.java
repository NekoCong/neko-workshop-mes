package io.neko.workshop.module.mes.service.order;

import io.neko.workshop.module.mes.dal.dataobject.order.PurchaseOrderReturnItem;

import java.util.List;

/**
 * @author 灵喵工坊
 * @time 2023/8/15 11:55
 */
public interface PurchaseOrderReturnItemService {

    Integer removeByNumber(String returnNumber);

    List<PurchaseOrderReturnItem> findPurchaseOrderReturnItemByNumber(String returnNumber);
}
