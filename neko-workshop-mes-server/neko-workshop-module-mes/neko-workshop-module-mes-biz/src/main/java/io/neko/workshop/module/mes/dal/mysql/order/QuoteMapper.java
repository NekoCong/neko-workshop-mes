package io.neko.workshop.module.mes.dal.mysql.order;

import io.neko.workshop.framework.common.pojo.PageResult;
import io.neko.workshop.framework.mybatis.core.mapper.BaseMapperX;
import io.neko.workshop.framework.mybatis.core.query.LambdaQueryWrapperX;
import io.neko.workshop.module.mes.controller.admin.order.vo.QuoteExportVo;
import io.neko.workshop.module.mes.controller.admin.order.vo.QuoteQueryVo;
import io.neko.workshop.module.mes.dal.dataobject.order.PurchaseOrder;
import io.neko.workshop.module.mes.dal.dataobject.order.Quote;
import io.neko.workshop.module.mes.utils.StringUtils;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;


/**
 * @author 灵喵工坊
 * @time 2023/7/17 14:08
 */
@Mapper
public interface QuoteMapper extends BaseMapperX<Quote> {

   default PageResult<Quote> findPage(QuoteQueryVo orderQuoteQueryVo){
       LambdaQueryWrapperX<Quote> queryWrapperX = new LambdaQueryWrapperX<>();
       if(StringUtils.isNotBlank(orderQuoteQueryVo.getCustomerName())){
           queryWrapperX.like(Quote::getCustomerName,orderQuoteQueryVo.getCustomerName());
       }
       if(StringUtils.isNotBlank(orderQuoteQueryVo.getModel())){
           queryWrapperX.like(Quote::getModel,orderQuoteQueryVo.getModel());
       }
       if(StringUtils.isNotBlank(orderQuoteQueryVo.getLineType())){
           queryWrapperX.like(Quote::getLineType,orderQuoteQueryVo.getLineType());
       }

       if(StringUtils.isNotBlank(orderQuoteQueryVo.getCreateTime())){
           LocalDate requestDate = LocalDate.parse(orderQuoteQueryVo.getCreateTime(), DateTimeFormatter.ofPattern("yyyy-MM-dd"));
           queryWrapperX.ge(Quote::getCreateTime, requestDate);
       }
       if(StringUtils.isNotBlank(orderQuoteQueryVo.getEndTime())){
           LocalDate requestDate = LocalDate.parse(orderQuoteQueryVo.getEndTime(), DateTimeFormatter.ofPattern("yyyy-MM-dd"));
           queryWrapperX.le(Quote::getCreateTime, requestDate.plusDays(1));
       }

      if(StringUtils.isBlank(orderQuoteQueryVo.getCreateTime()) && StringUtils.isBlank(orderQuoteQueryVo.getCustomerName()) && StringUtils.isBlank(orderQuoteQueryVo.getModel()) && StringUtils.isBlank(orderQuoteQueryVo.getLineType())){
          queryWrapperX.isNotNull(Quote::getId);
      }
      queryWrapperX.orderByDesc(Quote::getCreateTime);
      return  selectPage(orderQuoteQueryVo,queryWrapperX);
   }

    List<QuoteExportVo> exportData(@Param("number") List<String> number);

    List<QuoteExportVo> exportAllData();
}
