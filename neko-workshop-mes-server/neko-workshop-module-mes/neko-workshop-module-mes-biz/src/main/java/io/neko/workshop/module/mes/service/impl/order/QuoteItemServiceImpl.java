package io.neko.workshop.module.mes.service.impl.order;

import io.neko.workshop.framework.common.pojo.PageResult;
import io.neko.workshop.framework.mybatis.core.query.LambdaQueryWrapperX;
import io.neko.workshop.module.mes.controller.admin.order.vo.QuoteItemQueryVo;
import io.neko.workshop.module.mes.dal.dataobject.order.QuoteItem;
import io.neko.workshop.module.mes.dal.mysql.order.QuoteItemMapper;
import io.neko.workshop.module.mes.service.order.QuoteItemService;
import org.springframework.stereotype.Service;
import java.util.List;
import lombok.RequiredArgsConstructor;

/**
 * @author 灵喵工坊
 * @time 2023/7/17 14:48
 */
@Service
@RequiredArgsConstructor
public class QuoteItemServiceImpl implements QuoteItemService {

    private final QuoteItemMapper orderQuoteItemMapper;

    @Override
    public Integer saveOrderQuoteItem(QuoteItem orderQuoteItem) {
        return orderQuoteItemMapper.insert(orderQuoteItem);
    }

    @Override
    public Integer removeOrderQuoteItem(List<Long> ids) {
        return orderQuoteItemMapper.deleteBatchIds(ids);
    }

    @Override
    public Integer updateOrderQuoteItem(QuoteItem orderQuoteItem) {
        return orderQuoteItemMapper.updateById(orderQuoteItem);
    }

    @Override
    public QuoteItem findOrderQuoteItemById(Long id) {
        return orderQuoteItemMapper.selectById(id);
    }

    @Override
    public PageResult<QuoteItem> findPage(QuoteItemQueryVo orderQuoteItemQueryVo) {
        return orderQuoteItemMapper.findPage(orderQuoteItemQueryVo);
    }

    @Override
    public Integer removeByNumber(String number) {
        LambdaQueryWrapperX<QuoteItem> queryWrapperX = new LambdaQueryWrapperX<>();
        queryWrapperX.eq(QuoteItem::getQuoteNumber,number);
        return orderQuoteItemMapper.delete(queryWrapperX);
    }

    @Override
    public List<QuoteItem> findOrderQuoteItemByNUm(String number) {
        LambdaQueryWrapperX<QuoteItem> queryWrapperX = new LambdaQueryWrapperX<>();
        queryWrapperX.eq(QuoteItem::getQuoteNumber,number);
        return orderQuoteItemMapper.selectList(queryWrapperX);
    }
}
