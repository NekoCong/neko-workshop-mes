package io.neko.workshop.module.mes.service.order;

import io.neko.workshop.framework.common.pojo.PageResult;
import io.neko.workshop.module.mes.controller.admin.order.vo.QuoteQueryVo;
import io.neko.workshop.module.mes.dal.dataobject.order.Quote;
import io.neko.workshop.module.mes.controller.admin.order.vo.QuoteExportVo;

import java.util.List;

/**
 * @author 灵喵工坊
 * @time 2023/7/17 14:10
 */
public interface QuoteService {

    Integer saveOrderQuote(Quote orderQuote);

    Integer removeOrderQuote(List<Long> ids);

    Integer updateOrderQuote(Quote orderQuote);

    Quote findOrderQuoteById(Long id);

    PageResult<Quote> findPage(QuoteQueryVo orderQuoteQueryVo);

    List<Quote> findModelByName(String customerName);

    List<Quote> listAll();

    Quote findQuoteByNameLineTypeModel(String customerName, String lineType, String model);

    List<QuoteExportVo> exportData(List<String> ids);

    List<QuoteExportVo> exportAllData();
}
