package io.neko.workshop.module.mes.service.impl.order;


import io.neko.workshop.framework.mybatis.core.query.LambdaQueryWrapperX;
import io.neko.workshop.module.mes.dal.dataobject.order.ProductPickItem;
import io.neko.workshop.module.mes.dal.mysql.order.ProductPickItemMapper;
import io.neko.workshop.module.mes.service.order.ProductPickItemService;
import org.springframework.stereotype.Service;

import java.util.List;
import lombok.RequiredArgsConstructor;


/**
 * @author 灵喵工坊
 * @time 2023/7/21 14:25
 */
@Service
@RequiredArgsConstructor
public class ProductPickItemServiceImpl implements ProductPickItemService {

    private final ProductPickItemMapper productPickItemMapper;

    @Override
    public Integer removeByNumber(String number) {
        LambdaQueryWrapperX<ProductPickItem> queryWrapperX = new LambdaQueryWrapperX<>();
        queryWrapperX.eq(ProductPickItem::getPickNumber,number);
        return productPickItemMapper.delete(queryWrapperX);
    }

    @Override
    public List<ProductPickItem> findProductPickItemByNum(String number) {
        LambdaQueryWrapperX<ProductPickItem> queryWrapperX = new LambdaQueryWrapperX<>();
        queryWrapperX.eq(ProductPickItem::getPickNumber,number);
        return productPickItemMapper.selectList(queryWrapperX);
    }
}
