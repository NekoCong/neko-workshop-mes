package io.neko.workshop.module.mes.service.order;


import io.neko.workshop.framework.common.pojo.PageResult;
import io.neko.workshop.module.mes.controller.admin.order.vo.PurchaseOrderReturnQueryVo;
import io.neko.workshop.module.mes.dal.dataobject.order.PurchaseOrderReturn;
import io.neko.workshop.module.mes.controller.admin.order.vo.PurchaseOrderReturnExcelVo;
import java.util.Collection;
import java.util.List;

/**
 * @author 灵喵工坊
 * @time 2023/8/15 11:55
 */
public interface PurchaseOrderReturnService {

    PageResult<PurchaseOrderReturn> findPage(PurchaseOrderReturnQueryVo purchaseOrderReturnQueryVo);

    Integer savePurchaseOrderReturn(PurchaseOrderReturn purchaseOrderReturn);

    Integer updatePurchaseOrderReturn(PurchaseOrderReturn purchaseOrderReturn);

    Integer removePurchaseOrderReturn(List<Long> ids);

    PurchaseOrderReturn findPurchaseOrderReturn(Long id);

    List<PurchaseOrderReturnExcelVo> exportData();
}
