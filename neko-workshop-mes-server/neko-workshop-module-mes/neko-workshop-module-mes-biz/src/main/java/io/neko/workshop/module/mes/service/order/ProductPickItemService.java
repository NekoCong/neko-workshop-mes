package io.neko.workshop.module.mes.service.order;

import io.neko.workshop.module.mes.dal.dataobject.order.ProductPickItem;

import java.util.List;

/**
 * @author 灵喵工坊
 * @time 2023/7/21 14:25
 */
public interface ProductPickItemService {

    Integer removeByNumber(String number);

    List<ProductPickItem> findProductPickItemByNum(String number);
}
