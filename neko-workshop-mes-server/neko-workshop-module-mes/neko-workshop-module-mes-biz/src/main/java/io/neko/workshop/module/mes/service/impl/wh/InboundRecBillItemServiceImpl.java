package io.neko.workshop.module.mes.service.impl.wh;

import io.neko.workshop.framework.common.pojo.PageResult;
import io.neko.workshop.framework.mybatis.core.query.LambdaQueryWrapperX;
import io.neko.workshop.module.mes.controller.admin.wh.vo.InboundRecBillItemQueryVo;
import io.neko.workshop.module.mes.controller.admin.wh.vo.InboundRecBillItemVo;
import io.neko.workshop.module.mes.dal.dataobject.wh.InboundRecBill;
import io.neko.workshop.module.mes.dal.dataobject.wh.InboundRecBillItem;
import io.neko.workshop.module.mes.dal.mysql.wh.InboundRecBillItemMapper;
import io.neko.workshop.module.mes.service.wh.InboundRecBillItemService;
import org.springframework.stereotype.Service;

import java.util.List;
import lombok.RequiredArgsConstructor;

/**
 * 到货通知明细ServiceImpl
 *
 * @author 灵喵工坊MES
 */
@Service
@RequiredArgsConstructor
public class InboundRecBillItemServiceImpl implements InboundRecBillItemService {

    private final InboundRecBillItemMapper inboundRecBillItemMapper;
    @Override
    public PageResult<InboundRecBillItem> findPage(InboundRecBillItemQueryVo inboundRecBillItemQueryVo,String flag) {
        return inboundRecBillItemMapper.findPage(inboundRecBillItemQueryVo,flag);
    }

    @Override
    public List<InboundRecBillItemVo> findInboundRecBillItem(String receiptNumber) {
        return inboundRecBillItemMapper.findInboundRecBillItem(receiptNumber);
    }

    @Override
    public InboundRecBillItem findInboundRecBillItem(String itemCode, String recNumber) {
        return inboundRecBillItemMapper.findInboundRecBillItem(itemCode,recNumber);
    }

    @Override
    public Integer updateinboundRecBillItem(InboundRecBillItem inboundRecBillItem) {
        return inboundRecBillItemMapper.updateById(inboundRecBillItem);
    }

    @Override
    public Integer updateState(String itemCode, String recNumber, String state) {
        LambdaQueryWrapperX<InboundRecBillItem> queryWrapperX = new LambdaQueryWrapperX<>();
        queryWrapperX.eq(InboundRecBillItem::getItemCode,itemCode);
        queryWrapperX.eq(InboundRecBillItem::getRecNumber,recNumber);
        InboundRecBillItem inboundRecBillItem = inboundRecBillItemMapper.selectOne(queryWrapperX);
        inboundRecBillItem.setState(state);
        return inboundRecBillItemMapper.updateById(inboundRecBillItem);
    }
}
