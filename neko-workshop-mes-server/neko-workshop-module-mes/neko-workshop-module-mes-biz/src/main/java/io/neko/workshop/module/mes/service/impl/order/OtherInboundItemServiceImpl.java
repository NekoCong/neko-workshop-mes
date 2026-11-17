package io.neko.workshop.module.mes.service.impl.order;

import io.neko.workshop.framework.mybatis.core.query.LambdaQueryWrapperX;
import io.neko.workshop.module.mes.dal.dataobject.order.OtherInboundItem;
import io.neko.workshop.module.mes.dal.mysql.order.OtherInboundItemMapper;
import io.neko.workshop.module.mes.service.order.OtherInboundItemService;
import org.springframework.stereotype.Service;

import java.util.List;
import lombok.RequiredArgsConstructor;

/**
 * @author 灵喵工坊
 * @time 2023/8/22 16:32
 */
@Service
@RequiredArgsConstructor
public class OtherInboundItemServiceImpl implements OtherInboundItemService {

    private final OtherInboundItemMapper otherInboundItemMapper;


    @Override
    public List<OtherInboundItem> findItemByNumber(String inNumber) {
        return otherInboundItemMapper.selectList(new LambdaQueryWrapperX<OtherInboundItem>().eq(OtherInboundItem::getInNumber,inNumber));
    }
}
