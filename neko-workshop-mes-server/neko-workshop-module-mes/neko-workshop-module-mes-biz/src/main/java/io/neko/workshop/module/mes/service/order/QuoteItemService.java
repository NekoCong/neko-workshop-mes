package io.neko.workshop.module.mes.service.order;

import io.neko.workshop.framework.common.pojo.PageResult;
import io.neko.workshop.module.mes.controller.admin.order.vo.QuoteItemQueryVo;
import io.neko.workshop.module.mes.dal.dataobject.order.QuoteItem;

import java.util.List;

/**
 * @author 灵喵工坊
 * @time 2023/7/17 14:43
 */
public interface QuoteItemService {
    
    Integer saveOrderQuoteItem(QuoteItem orderQuoteItem);


    Integer removeOrderQuoteItem(List<Long> ids);

    Integer updateOrderQuoteItem(QuoteItem orderQuoteItem);

    QuoteItem findOrderQuoteItemById(Long id);

    PageResult<QuoteItem> findPage(QuoteItemQueryVo orderQuoteItemQueryVo);

    Integer removeByNumber(String number);

    List<QuoteItem> findOrderQuoteItemByNUm(String number);
}
