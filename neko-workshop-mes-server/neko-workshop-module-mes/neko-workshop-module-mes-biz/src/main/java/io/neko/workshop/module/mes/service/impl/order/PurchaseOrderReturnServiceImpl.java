package io.neko.workshop.module.mes.service.impl.order;

import io.neko.workshop.framework.common.pojo.PageResult;
import io.neko.workshop.framework.mybatis.core.query.LambdaQueryWrapperX;
import io.neko.workshop.module.mes.controller.admin.order.vo.PurchaseOrderReturnExcelVo;
import io.neko.workshop.module.mes.controller.admin.order.vo.PurchaseOrderReturnQueryVo;
import io.neko.workshop.module.mes.controller.admin.order.vo.QuoteExportVo;
import io.neko.workshop.module.mes.dal.dataobject.order.PurchaseOrderReturn;
import io.neko.workshop.module.mes.dal.dataobject.order.PurchaseOrderReturnItem;
import io.neko.workshop.module.mes.dal.dataobject.order.Quote;
import io.neko.workshop.module.mes.dal.mysql.order.PurchaseOrderReturnItemMapper;
import io.neko.workshop.module.mes.dal.mysql.order.PurchaseOrderReturnMapper;
import io.neko.workshop.module.mes.service.order.PurchaseOrderReturnService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import lombok.RequiredArgsConstructor;


/**
 * @author 灵喵工坊
 * @time 2023/8/15 11:55
 */
@Service
@RequiredArgsConstructor
public class PurchaseOrderReturnServiceImpl implements PurchaseOrderReturnService {

    private final PurchaseOrderReturnMapper purchaseOrderReturnMapper;
    private final PurchaseOrderReturnItemMapper purchaseOrderReturnItemMapper;


    @Override
    public PageResult<PurchaseOrderReturn> findPage(PurchaseOrderReturnQueryVo purchaseOrderReturnQueryVo) {
        return purchaseOrderReturnMapper.findPage(purchaseOrderReturnQueryVo);
    }

    @Override
    public Integer savePurchaseOrderReturn(PurchaseOrderReturn purchaseOrderReturn) {
        purchaseOrderReturn.setCreateTime(new Date());
        List<PurchaseOrderReturnItem> purchaseOrderReturnItemList = purchaseOrderReturn.getPurchaseOrderReturnItemList();
        for (PurchaseOrderReturnItem purchaseOrderReturnItem : purchaseOrderReturnItemList){
            purchaseOrderReturnItem.setReturnNumber(purchaseOrderReturn.getNumber());
            purchaseOrderReturnItemMapper.insert(purchaseOrderReturnItem);
        }
        return purchaseOrderReturnMapper.insert(purchaseOrderReturn);
    }

    @Override
    public Integer updatePurchaseOrderReturn(PurchaseOrderReturn purchaseOrderReturn) {
        PurchaseOrderReturn purchaseOrderReturnDo = purchaseOrderReturnMapper.selectById(purchaseOrderReturn.getId());
        //修改的时候数组先执行删除方法在添加
        purchaseOrderReturnItemMapper.delete(new LambdaQueryWrapperX<PurchaseOrderReturnItem>().eq(PurchaseOrderReturnItem::getReturnNumber, purchaseOrderReturnDo.getNumber()));
        purchaseOrderReturn.setUpdateTime(new Date());
        List<PurchaseOrderReturnItem> purchaseOrderReturnItemList = purchaseOrderReturn.getPurchaseOrderReturnItemList();
        for (PurchaseOrderReturnItem purchaseOrderReturnItem : purchaseOrderReturnItemList){
            purchaseOrderReturnItem.setReturnNumber(purchaseOrderReturn.getNumber());
            purchaseOrderReturnItemMapper.insert(purchaseOrderReturnItem);
        }
        return purchaseOrderReturnMapper.updateById(purchaseOrderReturn);
    }

    @Override
    public Integer removePurchaseOrderReturn(List<Long> ids) {
        return purchaseOrderReturnMapper.deleteBatchIds(ids);
    }

    @Override
    public PurchaseOrderReturn findPurchaseOrderReturn(Long id) {
        return purchaseOrderReturnMapper.selectById(id);
    }


    @Override
    public List<PurchaseOrderReturnExcelVo> exportData() {
        List<PurchaseOrderReturn> purchaseOrderReturnList = purchaseOrderReturnMapper.selectList();
        ArrayList<PurchaseOrderReturnExcelVo> purchaseOrderReturnExcelVo = new ArrayList<>(purchaseOrderReturnList.size());
        purchaseOrderReturnList.forEach(dict -> {
            PurchaseOrderReturnExcelVo excelDictDTO = new PurchaseOrderReturnExcelVo();
            BeanUtils.copyProperties(dict, excelDictDTO);
            purchaseOrderReturnExcelVo.add(excelDictDTO);
        });
        return purchaseOrderReturnExcelVo;
    }
}
