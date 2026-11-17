package io.neko.workshop.module.mes.service.impl.order;

import io.neko.workshop.framework.common.pojo.PageResult;
import io.neko.workshop.framework.mybatis.core.query.LambdaQueryWrapperX;
import io.neko.workshop.module.mes.controller.admin.order.vo.ReceiptItemQueryVo;
import io.neko.workshop.module.mes.dal.dataobject.order.PurchaseOrderItem;
import io.neko.workshop.module.mes.dal.mysql.order.PurchaseOrderItemMapper;
import io.neko.workshop.module.mes.service.order.PurchaseOrderItemService;
import org.springframework.stereotype.Service;

import java.util.List;
import lombok.RequiredArgsConstructor;

/**
 * @author 灵喵工坊
 * @time 2023/7/18 15:19
 */
@Service
@RequiredArgsConstructor
public class PurchaseOrdertItemServiceImpl implements PurchaseOrderItemService {

    private final PurchaseOrderItemMapper receiptItemMapper;


    @Override
    public Integer removeByNumber(String number) {
        LambdaQueryWrapperX<PurchaseOrderItem> queryWrapperX = new LambdaQueryWrapperX<>();
        queryWrapperX.eq(PurchaseOrderItem::getReceiptNumber,number);
        return receiptItemMapper.delete(queryWrapperX);
    }

    @Override
    public List<PurchaseOrderItem> findReceiptItemByNum(String number) {
        LambdaQueryWrapperX<PurchaseOrderItem> queryWrapperX = new LambdaQueryWrapperX<>();
        queryWrapperX.eq(PurchaseOrderItem::getReceiptNumber,number);
        return receiptItemMapper.selectList(queryWrapperX);
    }

    @Override
    public PageResult<PurchaseOrderItem> findPageByReceiptNumber( ReceiptItemQueryVo receiptItemQueryVo) {
        return receiptItemMapper.selectByReceiptNumber( receiptItemQueryVo);
    }

    @Override
    public PurchaseOrderItem findReceiptItemByNumAndCode(String itemCode, String receiptNumber) {
        return receiptItemMapper.selectOne(new LambdaQueryWrapperX<PurchaseOrderItem>().eq(PurchaseOrderItem::getItemCode,itemCode).eq(PurchaseOrderItem::getReceiptNumber,receiptNumber));
    }

    @Override
    public Integer updatePurchaseOrderItem(PurchaseOrderItem purchaseOrderItem) {
        return receiptItemMapper.updateById(purchaseOrderItem);
    }

}
