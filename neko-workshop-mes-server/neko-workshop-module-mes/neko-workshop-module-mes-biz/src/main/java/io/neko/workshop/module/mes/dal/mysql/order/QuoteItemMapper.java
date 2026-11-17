package io.neko.workshop.module.mes.dal.mysql.order;

import io.neko.workshop.framework.common.pojo.PageResult;
import io.neko.workshop.framework.mybatis.core.mapper.BaseMapperX;
import io.neko.workshop.framework.mybatis.core.query.LambdaQueryWrapperX;
import io.neko.workshop.module.mes.controller.admin.order.vo.QuoteItemQueryVo;
import io.neko.workshop.module.mes.dal.dataobject.order.QuoteItem;
import io.neko.workshop.module.mes.utils.StringUtils;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author 灵喵工坊
 * @time 2023/7/17 14:43
 */
@Mapper
public interface QuoteItemMapper extends BaseMapperX<QuoteItem> {

   default PageResult<QuoteItem> findPage(QuoteItemQueryVo orderQuoteItemQueryVo){
       LambdaQueryWrapperX<QuoteItem> queryWrapperX = new LambdaQueryWrapperX<>();
       if(StringUtils.isNotBlank(orderQuoteItemQueryVo.getQuoteNumber())){
           queryWrapperX.eq(QuoteItem::getQuoteNumber,orderQuoteItemQueryVo.getQuoteNumber());
       }
       if(StringUtils.isBlank(orderQuoteItemQueryVo.getQuoteNumber())){
           queryWrapperX.isNotNull(QuoteItem::getId);
       }
       return  selectPage(orderQuoteItemQueryVo,queryWrapperX);

   }

}
