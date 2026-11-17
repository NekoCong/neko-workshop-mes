package io.neko.workshop.module.mes.service.impl.order;

import io.neko.workshop.framework.mybatis.core.query.LambdaQueryWrapperX;
import io.neko.workshop.module.mes.dal.dataobject.order.ReturnsItem;
import io.neko.workshop.module.mes.dal.mysql.order.ReturnsItemMapper;
import io.neko.workshop.module.mes.service.order.ReturnsItemService;
import org.springframework.stereotype.Service;
import java.util.List;
import lombok.RequiredArgsConstructor;

/**
 * @author 灵喵工坊
 * @time 2023/7/18 17:04
 */
@Service
@RequiredArgsConstructor
public class ReturnsItemServiceImpl implements ReturnsItemService {

    private final ReturnsItemMapper returnsItemMapper;


    @Override
    public Integer removeByNumber(String number) {
        LambdaQueryWrapperX<ReturnsItem> queryWrapperX = new LambdaQueryWrapperX<>();
        queryWrapperX.eq(ReturnsItem::getReturnNumber,number);
        return returnsItemMapper.delete(queryWrapperX);
    }

    @Override
    public List<ReturnsItem> findReturnsItemByNum(String number) {
        LambdaQueryWrapperX<ReturnsItem> queryWrapperX = new LambdaQueryWrapperX<>();
        queryWrapperX.eq(ReturnsItem::getReturnNumber,number);
        return returnsItemMapper.selectList(queryWrapperX);
    }
}
