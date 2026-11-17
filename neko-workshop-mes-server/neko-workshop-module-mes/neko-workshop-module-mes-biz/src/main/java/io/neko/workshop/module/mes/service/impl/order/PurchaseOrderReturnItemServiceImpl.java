package io.neko.workshop.module.mes.service.impl.order;

import io.neko.workshop.framework.mybatis.core.query.LambdaQueryWrapperX;
import io.neko.workshop.module.mes.dal.dataobject.order.PurchaseOrderReturnItem;
import io.neko.workshop.module.mes.dal.mysql.order.PurchaseOrderReturnItemMapper;
import io.neko.workshop.module.mes.service.order.PurchaseOrderReturnItemService;
import org.springframework.stereotype.Service;

import java.util.List;
import lombok.RequiredArgsConstructor;


/**
 * @author 灵喵工坊
 * @time 2023/8/15 11:55
 */
@Service
@RequiredArgsConstructor
public class PurchaseOrderReturnItemServiceImpl implements PurchaseOrderReturnItemService {

    private final PurchaseOrderReturnItemMapper purchaseOrderReturnItemMapper;

    @Override
    public Integer removeByNumber(String returnNumber) {
        LambdaQueryWrapperX<PurchaseOrderReturnItem> queryWrapperX = new LambdaQueryWrapperX<>();
        queryWrapperX.eq(PurchaseOrderReturnItem::getReturnNumber,returnNumber);
        return purchaseOrderReturnItemMapper.delete(queryWrapperX);
    }

    @Override
    public List<PurchaseOrderReturnItem> findPurchaseOrderReturnItemByNumber(String returnNumber) {
        LambdaQueryWrapperX<PurchaseOrderReturnItem> queryWrapperX = new LambdaQueryWrapperX<>();
        queryWrapperX.eq(PurchaseOrderReturnItem::getReturnNumber,returnNumber);
        return purchaseOrderReturnItemMapper.selectList(queryWrapperX);
    }
}
