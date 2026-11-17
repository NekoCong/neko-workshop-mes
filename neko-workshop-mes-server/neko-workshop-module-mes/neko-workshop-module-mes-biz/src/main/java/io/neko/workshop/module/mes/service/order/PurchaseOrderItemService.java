package io.neko.workshop.module.mes.service.order;

import io.neko.workshop.framework.common.pojo.PageResult;
import io.neko.workshop.module.mes.controller.admin.order.vo.ReceiptItemQueryVo;
import io.neko.workshop.module.mes.dal.dataobject.order.PurchaseOrderItem;
import java.util.List;

/**
 * @author 灵喵工坊
 * @time 2023/7/18 15:18
 */
public interface PurchaseOrderItemService {
    
    Integer removeByNumber(String number);


    List<PurchaseOrderItem> findReceiptItemByNum(String number);

    PageResult<PurchaseOrderItem> findPageByReceiptNumber(ReceiptItemQueryVo receiptItemQueryVo);

    PurchaseOrderItem findReceiptItemByNumAndCode(String itemCode, String receiptNumber);

    Integer updatePurchaseOrderItem(PurchaseOrderItem purchaseOrderItem);
}
