package io.neko.workshop.module.mes.service.impl.order;

import io.neko.workshop.framework.common.pojo.PageResult;
import io.neko.workshop.framework.mybatis.core.query.LambdaQueryWrapperX;
import io.neko.workshop.module.mes.controller.admin.order.vo.PurchaseOrderExcelVo;
import io.neko.workshop.module.mes.controller.admin.order.vo.PurchaseOrderVo;
import io.neko.workshop.module.mes.controller.admin.order.vo.QuoteExportVo;
import io.neko.workshop.module.mes.controller.admin.order.vo.ReceiptQueryVo;
import io.neko.workshop.module.mes.dal.dataobject.order.PurchaseOrder;
import io.neko.workshop.module.mes.dal.dataobject.order.PurchaseOrderItem;
import io.neko.workshop.module.mes.dal.dataobject.order.Quote;
import io.neko.workshop.module.mes.dal.mysql.order.PurchaseOrderItemMapper;
import io.neko.workshop.module.mes.dal.mysql.order.PurchaseOrderMapper;
import io.neko.workshop.module.mes.service.order.PurchaseOrderService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import lombok.RequiredArgsConstructor;


/**
 * @author 灵喵工坊
 * @time 2023/7/18 15:11
 */
@Service
@RequiredArgsConstructor
public class PurchaseOrderServiceImpl implements PurchaseOrderService {

    private final PurchaseOrderMapper receiptMapper;

    private final PurchaseOrderItemMapper receiptItemMapper;





    @Override
    public Integer saveReceipt(PurchaseOrder receipt) {
        receipt.setCreateTime(new Date());
        for(PurchaseOrderItem purchaseOrderItem:receipt.getReceiptItemList()){
            PurchaseOrderItem orderItem = new PurchaseOrderItem();
            orderItem.setVendor(purchaseOrderItem.getVendor()).setItemCode(purchaseOrderItem.getItemCode())
                    .setItemName(purchaseOrderItem.getItemName()).setModel(purchaseOrderItem.getModel()).setSpec(purchaseOrderItem.getSpec())
                    .setQuantity(purchaseOrderItem.getQuantity()).setKind(purchaseOrderItem.getKind()).setUnitOfMeasure(purchaseOrderItem.getUnitOfMeasure())
                    .setPurchasePrice(purchaseOrderItem.getPurchasePrice()).setIncludTax(purchaseOrderItem.getIncludTax()).setNoIncludTax(purchaseOrderItem.getNoIncludTax())
                    .setReceiptNumber(receipt.getNumber());
            receiptItemMapper.insert(orderItem);
        }
        return  receiptMapper.insert(receipt);
    }



    @Override
    public PurchaseOrder findReceiptById(Long id) {
        return receiptMapper.selectById(id);
    }



    @Override
    public Integer removeReceipt(List<Long> ids) {
        return receiptMapper.deleteBatchIds(ids);
    }



    @Override
    public Integer updateReceipt(PurchaseOrder receipt) {
        PurchaseOrder receiptDo = receiptMapper.selectById(receipt.getId());
        //修改的时候数组先执行删除方法在添加
        receiptItemMapper.delete(new LambdaQueryWrapperX<PurchaseOrderItem>().eq(PurchaseOrderItem::getReceiptNumber, receiptDo.getNumber()));
        for(PurchaseOrderItem purchaseOrderItem:receipt.getReceiptItemList()){
            PurchaseOrderItem orderItem = new PurchaseOrderItem();
            orderItem.setVendor(purchaseOrderItem.getVendor()).setItemCode(purchaseOrderItem.getItemCode())
                    .setItemName(purchaseOrderItem.getItemName()).setModel(purchaseOrderItem.getModel()).setSpec(purchaseOrderItem.getSpec())
                    .setQuantity(purchaseOrderItem.getQuantity()).setKind(purchaseOrderItem.getKind()).setUnitOfMeasure(purchaseOrderItem.getUnitOfMeasure())
                    .setPurchasePrice(purchaseOrderItem.getPurchasePrice()).setIncludTax(purchaseOrderItem.getIncludTax()).setNoIncludTax(purchaseOrderItem.getNoIncludTax())
                    .setReceiptNumber(receipt.getNumber());
            receiptItemMapper.insert(orderItem);
        }
        return receiptMapper.updateById(receipt);
    }



    @Override
    public PageResult<PurchaseOrder> findPage(ReceiptQueryVo receiptQueryVo) {
        return receiptMapper.findPage(receiptQueryVo);
    }

    @Override
    public List<PurchaseOrder> selectList() {
        return receiptMapper.selectList();
    }

    @Override
    public List<PurchaseOrderVo> PurchaseOrderPriant(String number) {
        return receiptMapper.PurchaseOrderPriant(number);
    }

    @Override
    public PurchaseOrder selectByNumber(String number) {
        LambdaQueryWrapperX<PurchaseOrder> queryWrapperX = new LambdaQueryWrapperX<>();
        queryWrapperX.eq(PurchaseOrder::getNumber,number);
        return receiptMapper.selectOne(queryWrapperX);
    }

    @Override
    public List<PurchaseOrderExcelVo> exportData(List<String> number) {
        return receiptMapper.exportData(number);
    }

    @Override
    public List<PurchaseOrderExcelVo> exportAllData() {
        return receiptMapper.exportAllData();
    }


}
