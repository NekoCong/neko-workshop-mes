package io.neko.workshop.module.mes.service.impl.order;

import io.neko.workshop.framework.mybatis.core.query.LambdaQueryWrapperX;
import io.neko.workshop.module.mes.dal.dataobject.order.SaleItem;
import io.neko.workshop.module.mes.dal.mysql.order.SaleItemMapper;
import io.neko.workshop.module.mes.service.order.SaleItemService;
import org.springframework.stereotype.Service;
import java.util.List;
import lombok.RequiredArgsConstructor;


/**
 * @author 灵喵工坊
 * @time 2023/7/20 10:31
 */
@Service
@RequiredArgsConstructor
public class SaleItemServiceImpl implements SaleItemService {

    private final SaleItemMapper saleItemMapper;


    @Override
    public List<SaleItem> findsaleItemByNum(String number) {
        LambdaQueryWrapperX<SaleItem> queryWrapperX = new LambdaQueryWrapperX<>();
        queryWrapperX.eq(SaleItem::getSaleNumber,number);
        return saleItemMapper.selectList(queryWrapperX);
    }

    @Override
    public SaleItem findSaleItemById(Long id) {
        return saleItemMapper.selectById(id);
    }

    @Override
    public Integer updateSaleItem(SaleItem item) {
        return saleItemMapper.updateById(item);
    }

    @Override
    public List<SaleItem> findsaleItemByItemNum(String saleItemNumber) {
        LambdaQueryWrapperX<SaleItem> queryWrapperX = new LambdaQueryWrapperX<>();
        queryWrapperX.eq(SaleItem::getNumber,saleItemNumber);
        return saleItemMapper.selectList(queryWrapperX);
    }

    @Override
    public Integer removeSaleItem(String saleNumber) {
        LambdaQueryWrapperX<SaleItem> queryWrapperX = new LambdaQueryWrapperX<>();
        queryWrapperX.eq(SaleItem::getSaleNumber,saleNumber);
        return saleItemMapper.delete(queryWrapperX);
    }


}
