package io.neko.workshop.module.mes.service.impl.order;

import io.neko.workshop.framework.mybatis.core.query.LambdaQueryWrapperX;
import io.neko.workshop.module.mes.dal.dataobject.order.InboundItem;
import io.neko.workshop.module.mes.dal.mysql.order.InboundItemMapper;
import io.neko.workshop.module.mes.service.order.InboundItemService;
import org.springframework.stereotype.Service;

import java.util.List;
import lombok.RequiredArgsConstructor;

/**
 * @author 灵喵工坊
 * @time 2023/7/26 13:17
 */
@Service
@RequiredArgsConstructor
public class InboundItemServiceImpl implements InboundItemService {

    private final InboundItemMapper inboundItemMapper;


    @Override
    public List<InboundItem> findItemByNum(String number) {
        return inboundItemMapper.selectList(new LambdaQueryWrapperX<InboundItem>().eq(InboundItem::getInNumber,number));
    }

    @Override
    public List<InboundItem> findItemByPurchaseNum(String number) {
        return inboundItemMapper.selectList(new LambdaQueryWrapperX<InboundItem>().eq(InboundItem::getReceiptNumber,number));
    }

    @Override
    public Integer removeByPurchaseNum(String receiptNumber) {
        return inboundItemMapper.delete(new LambdaQueryWrapperX<InboundItem>().eq(InboundItem::getReceiptNumber,receiptNumber));
    }


}

