package io.neko.workshop.module.mes.service.impl.order;

import io.neko.workshop.framework.common.pojo.PageResult;
import io.neko.workshop.framework.mybatis.core.query.LambdaQueryWrapperX;
import io.neko.workshop.module.mes.common.UserConstants;
import io.neko.workshop.module.mes.controller.admin.order.vo.QuoteExportVo;
import io.neko.workshop.module.mes.controller.admin.order.vo.QuoteQueryVo;
import io.neko.workshop.module.mes.dal.dataobject.order.Quote;
import io.neko.workshop.module.mes.dal.dataobject.order.QuoteItem;
import io.neko.workshop.module.mes.dal.mysql.order.QuoteItemMapper;
import io.neko.workshop.module.mes.dal.mysql.order.QuoteMapper;
import io.neko.workshop.module.mes.service.order.QuoteService;
import io.neko.workshop.module.mes.utils.AutoCodeUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import java.util.List;
import lombok.RequiredArgsConstructor;

/**
 * @author 灵喵工坊
 * @time 2023/7/17 14:11
 */
@Service
@RequiredArgsConstructor
public class QuoteServiceImpl implements QuoteService {

    private final QuoteMapper orderQuoteMapper;

    private final QuoteItemMapper orderQuoteItemMapper;

    private final AutoCodeUtil autoCodeUtil;


    @Override
    public Integer saveOrderQuote(Quote orderQuote) {
        //设置流水号
        orderQuote.setNumber(autoCodeUtil.genSerialCode(UserConstants.ORDERQUOTE_CODE,null));

        for(QuoteItem orderQuoteItem: orderQuote.getOrderQuoteItemList()){
            QuoteItem quoteItem = new QuoteItem();
            BeanUtils.copyProperties(orderQuoteItem,quoteItem);
            quoteItem.setQuoteNumber(orderQuote.getNumber());
            quoteItem.setSpec(quoteItem.getStartSpec() + "-" + quoteItem.getEndSpec());
            orderQuoteItemMapper.insert(quoteItem);
        }
        return  orderQuoteMapper.insert(orderQuote);
    }



    @Override
    public Integer removeOrderQuote(List<Long> ids) {
        return orderQuoteMapper.deleteBatchIds(ids);
    }


    @Override
    public Integer updateOrderQuote(Quote orderQuote) {
        Quote quote = orderQuoteMapper.selectById(orderQuote.getId());
        //修改的时候数组先执行删除方法在添加
        orderQuoteItemMapper.delete(new LambdaQueryWrapperX<QuoteItem>().eq(QuoteItem::getQuoteNumber,quote.getNumber()));
        for (QuoteItem orderQuoteItem : orderQuote.getOrderQuoteItemList()) {
            QuoteItem quoteItem = new QuoteItem();
            BeanUtils.copyProperties(orderQuoteItem, quoteItem);
            quoteItem.setQuoteNumber(quote.getNumber());
            quoteItem.setSpec(quoteItem.getStartSpec() + "-" + quoteItem.getEndSpec());
            orderQuoteItemMapper.insert(quoteItem);
        }
        return orderQuoteMapper.updateById(orderQuote);
    }





    @Override
    public Quote findOrderQuoteById(Long id) {
        return orderQuoteMapper.selectById(id);
    }

    @Override
    public PageResult<Quote> findPage(QuoteQueryVo orderQuoteQueryVo) {
        return orderQuoteMapper.findPage(orderQuoteQueryVo);
    }

    @Override
    public List<Quote> findModelByName(String customerName) {
        LambdaQueryWrapperX<Quote> queryWrapperX = new LambdaQueryWrapperX<>();
        queryWrapperX.eq(Quote::getCustomerName,customerName);
        return orderQuoteMapper.selectList(queryWrapperX);
    }

    @Override
    public List<Quote> listAll() {
        return orderQuoteMapper.selectList();
    }

    @Override
    public Quote findQuoteByNameLineTypeModel(String customerName, String lineType, String model) {
        LambdaQueryWrapperX<Quote> queryWrapperX = new LambdaQueryWrapperX<>();
        queryWrapperX.eq(Quote::getCustomerName,customerName);
        queryWrapperX.eq(Quote::getLineType,lineType);
        queryWrapperX.eq(Quote::getModel,model);
        return orderQuoteMapper.selectOne(queryWrapperX);
    }

    @Override
    public List<QuoteExportVo> exportData(List<String> number) {
        return orderQuoteMapper.exportData(number);
    }

    @Override
    public List<QuoteExportVo> exportAllData() {
        return orderQuoteMapper.exportAllData();
    }


}
