package io.neko.workshop.module.mes.service.order;

import io.neko.workshop.module.mes.dal.dataobject.order.ReturnsItem;

import java.util.List;

/**
 * @author 灵喵工坊
 * @time 2023/7/18 17:03
 */
public interface ReturnsItemService {
    Integer removeByNumber(String number);

    List<ReturnsItem> findReturnsItemByNum(String number);
}
