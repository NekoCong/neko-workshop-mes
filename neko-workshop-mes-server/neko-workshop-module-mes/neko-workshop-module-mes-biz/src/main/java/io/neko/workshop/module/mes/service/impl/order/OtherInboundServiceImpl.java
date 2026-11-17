package io.neko.workshop.module.mes.service.impl.order;

import io.neko.workshop.framework.common.pojo.PageResult;
import io.neko.workshop.module.mes.common.UserConstants;
import io.neko.workshop.module.mes.controller.admin.order.vo.OtherInboundQueryVo;
import io.neko.workshop.module.mes.dal.dataobject.order.*;
import io.neko.workshop.module.mes.dal.dataobject.wh.InboundRecBillItem;
import io.neko.workshop.module.mes.dal.mysql.order.OtherInboundItemMapper;
import io.neko.workshop.module.mes.dal.mysql.order.OtherInboundMapper;
import io.neko.workshop.module.mes.dal.mysql.wh.InboundRecBillItemMapper;
import io.neko.workshop.module.mes.service.order.OtherInboundItemService;
import io.neko.workshop.module.mes.service.order.OtherInboundService;
import io.neko.workshop.module.mes.utils.AutoCodeUtil;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import lombok.RequiredArgsConstructor;

/**
 * @author 灵喵工坊
 * @time 2023/8/22 16:32
 */
@Service
@RequiredArgsConstructor
public class OtherInboundServiceImpl implements OtherInboundService {

    private final OtherInboundMapper otherInboundMapper;
    private final OtherInboundItemMapper otherInboundItemMapper;
    private final InboundRecBillItemMapper inboundRecBillItemMapper;
    private final OtherInboundItemService otherInboundItemService;

    private final AutoCodeUtil autoCodeUtil;


    @Override
    public PageResult<OtherInbound> findPage(OtherInboundQueryVo otherInboundQueryVo) {
        return otherInboundMapper.findPage(otherInboundQueryVo);
    }

    @Override
    public OtherInbound findOtherInboundById(Long id) {
        Map<String, Object> map = new HashMap<>();
        map.put("in_number", otherInboundMapper.selectById(id).getInNumber());
        List<OtherInboundItem> list = otherInboundItemMapper.selectByMap(map);
        return otherInboundMapper.selectById(id).setItemList(list);
    }

    @Override
    public Integer saveOtherInbound(OtherInbound otherInbound) {
        //设置流水号
        //todo
        otherInbound.setInNumber(autoCodeUtil.genSerialCode(UserConstants.INBOUND_CODE,null));
        for(OtherInboundItem otherInboundItem: otherInbound.getItemList()){
            OtherInboundItem quoteItem = new OtherInboundItem();
            quoteItem.setInNumber(otherInbound.getInNumber())
                    .setReceiptNumber(otherInboundItem.getReceiptNumber())
                    .setRecNumber(otherInboundItem.getRecNumber())
                    .setItemCode(otherInboundItem.getItemCode()).setItemName(otherInboundItem.getItemName()).setModel(otherInboundItem.getModel())
                    .setSpec(otherInboundItem.getSpec()).setBatchNumber(otherInboundItem.getBatchNumber()).setVendor(otherInboundItem.getVendor())
                    .setBoxNumber(otherInboundItem.getBoxNumber()).setQuantity(otherInboundItem.getQuantity()).setAmount(otherInboundItem.getAmount()).setUnqualifiedNumber(otherInboundItem.getUnqualifiedNumber()).setPurchasePrice(otherInboundItem.getPurchasePrice())
                    .setTotalPrice(otherInboundItem.getTotalPrice()).setUnitOfMeasure(otherInboundItem.getUnitOfMeasure()).setKind(otherInboundItem.getKind())
                    .setBarcode(otherInboundItem.getBarcode()).setProductionDate(otherInboundItem.getProductionDate()).setEffectiveDate(otherInboundItem.getEffectiveDate()).setRemark(otherInboundItem.getRemark());

            InboundRecBillItem inboundRecBillItem =  inboundRecBillItemMapper.findInboundRecBillItem(otherInboundItem.getItemCode(),otherInboundItem.getRecNumber());
            inboundRecBillItem.setStatus("1");
            inboundRecBillItemMapper.updateById(inboundRecBillItem);
            otherInboundItemMapper.insert(quoteItem);
        }
        return otherInboundMapper.insert(otherInbound);
    }

    @Override
    public Integer updateOtherInbound(OtherInbound otherInbound) {
        Map<String, Object> map = new HashMap<>();
        map.put("in_number", otherInbound.getInNumber());
        List<OtherInboundItem> itemList = otherInboundItemService.findItemByNumber(otherInbound.getInNumber());
        for(OtherInboundItem otherInboundItem :itemList){
            InboundRecBillItem inboundRecBillItem =  inboundRecBillItemMapper.findInboundRecBillItem(otherInboundItem.getItemCode(),otherInboundItem.getRecNumber());
            inboundRecBillItem.setStatus("0");
            inboundRecBillItemMapper.updateById(inboundRecBillItem);
        }
        //先删除，再添加
        otherInboundItemMapper.deleteByMap(map);
        for(OtherInboundItem otherInboundItem: otherInbound.getItemList()){
            OtherInboundItem quoteItem = new OtherInboundItem();
            quoteItem.setInNumber(otherInbound.getInNumber())
                    .setReceiptNumber(otherInboundItem.getReceiptNumber())
                    .setRecNumber(otherInboundItem.getRecNumber())
                    .setItemCode(otherInboundItem.getItemCode()).setItemName(otherInboundItem.getItemName()).setModel(otherInboundItem.getModel())
                    .setSpec(otherInboundItem.getSpec()).setBatchNumber(otherInboundItem.getBatchNumber()).setVendor(otherInboundItem.getVendor())
                    .setBoxNumber(otherInboundItem.getBoxNumber()).setQuantity(otherInboundItem.getQuantity()).setAmount(otherInboundItem.getAmount()).setUnqualifiedNumber(otherInboundItem.getUnqualifiedNumber()).setPurchasePrice(otherInboundItem.getPurchasePrice())
                    .setTotalPrice(otherInboundItem.getTotalPrice()).setUnitOfMeasure(otherInboundItem.getUnitOfMeasure()).setKind(otherInboundItem.getKind())
                    .setBarcode(otherInboundItem.getBarcode()).setProductionDate(otherInboundItem.getProductionDate()).setEffectiveDate(otherInboundItem.getEffectiveDate()).setRemark(otherInboundItem.getRemark());

            InboundRecBillItem inboundRecBillItem =  inboundRecBillItemMapper.findInboundRecBillItem(otherInboundItem.getItemCode(),otherInboundItem.getRecNumber());
            inboundRecBillItem.setStatus("1");
            inboundRecBillItemMapper.updateById(inboundRecBillItem);
            otherInboundItemMapper.insert(quoteItem);
        }
        return otherInboundMapper.updateById(otherInbound);
    }

    @Override
    public Integer removeOtherInboundItem(List<Long> ids) {
        Map<String, Object> map = new HashMap<>();
        for (int i=0; i<ids.size(); ++i) {
            map.clear();
            map.put("in_number", otherInboundMapper.selectById(ids.get(i)).getInNumber());
            otherInboundItemMapper.deleteByMap(map);
        }
        return otherInboundMapper.deleteBatchIds(ids);
    }
}
