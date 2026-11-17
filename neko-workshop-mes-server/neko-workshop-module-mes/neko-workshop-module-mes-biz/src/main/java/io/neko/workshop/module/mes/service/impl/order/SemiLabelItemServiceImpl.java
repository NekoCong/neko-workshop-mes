package io.neko.workshop.module.mes.service.impl.order;

import io.neko.workshop.framework.mybatis.core.query.LambdaQueryWrapperX;
import io.neko.workshop.module.mes.dal.dataobject.order.SemiLabelItem;
import io.neko.workshop.module.mes.dal.mysql.order.SemiLabelItemMapper;
import io.neko.workshop.module.mes.service.order.SemiLabelItemService;
import org.springframework.stereotype.Service;

import java.util.List;
import lombok.RequiredArgsConstructor;

/**
 * @author 灵喵工坊
 * @time 2023/8/9 17:06
 */
@Service
@RequiredArgsConstructor
public class SemiLabelItemServiceImpl implements SemiLabelItemService {


    private final SemiLabelItemMapper semiLabelItemMapper;

    @Override
    public List<SemiLabelItem> selectSemiLabelItemByNumber(String semiNumber) {
        return semiLabelItemMapper.selectSemiLabelItemByNumber(semiNumber);
    }

    @Override
    public Integer deleteSemiLabelItem(String semiNumber) {
        LambdaQueryWrapperX<SemiLabelItem> queryWrapperX = new LambdaQueryWrapperX<>();
        queryWrapperX.eq(SemiLabelItem::getSemiNumber,semiNumber);
        return semiLabelItemMapper.delete(queryWrapperX);
    }
}
