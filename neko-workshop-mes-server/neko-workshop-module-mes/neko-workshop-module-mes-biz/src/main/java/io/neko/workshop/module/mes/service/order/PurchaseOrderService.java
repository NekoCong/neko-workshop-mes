package io.neko.workshop.module.mes.service.order;


import io.neko.workshop.framework.common.pojo.PageResult;
import io.neko.workshop.module.mes.controller.admin.order.vo.PurchaseOrderExcelVo;
import io.neko.workshop.module.mes.controller.admin.order.vo.PurchaseOrderVo;
import io.neko.workshop.module.mes.controller.admin.order.vo.ReceiptQueryVo;
import io.neko.workshop.module.mes.dal.dataobject.order.PurchaseOrder;

import java.util.Collection;
import java.util.List;



/**
 * @author 灵喵工坊
 * @time 2023/7/18 15:11
 */
public interface PurchaseOrderService {


    Integer saveReceipt(PurchaseOrder receipt);

    PurchaseOrder findReceiptById(Long id);

    Integer removeReceipt(List<Long> ids);


    Integer updateReceipt(PurchaseOrder receipt);


    PageResult<PurchaseOrder> findPage(ReceiptQueryVo receiptQueryVo);


    List<PurchaseOrder> selectList();

    List<PurchaseOrderVo> PurchaseOrderPriant(String number);

    PurchaseOrder selectByNumber(String number);

    List<PurchaseOrderExcelVo> exportData(List<String> number);

    List<PurchaseOrderExcelVo> exportAllData();
}
