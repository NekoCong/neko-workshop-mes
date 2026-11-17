package io.neko.workshop.module.mes.service.order;

import io.neko.workshop.module.mes.dal.dataobject.order.SaleItem;
import java.util.List;

/**
 * @author 灵喵工坊
 * @time 2023/7/20 10:31
 */
public interface SaleItemService {

    List<SaleItem> findsaleItemByNum(String number);


    SaleItem findSaleItemById(Long id);

    Integer updateSaleItem(SaleItem item);

    List<SaleItem> findsaleItemByItemNum(String saleItemNumber);

    Integer removeSaleItem(String saleNumber);

}
