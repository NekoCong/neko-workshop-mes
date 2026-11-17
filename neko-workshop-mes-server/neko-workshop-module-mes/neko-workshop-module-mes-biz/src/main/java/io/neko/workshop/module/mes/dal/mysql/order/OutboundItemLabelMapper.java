package io.neko.workshop.module.mes.dal.mysql.order;

import io.neko.workshop.framework.mybatis.core.mapper.BaseMapperX;
import io.neko.workshop.framework.mybatis.core.query.LambdaQueryWrapperX;
import io.neko.workshop.module.mes.dal.dataobject.order.Label;
import io.neko.workshop.module.mes.dal.dataobject.order.OutboundItemLabel;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author 灵喵工坊
 * @time 2023/7/31 15:31
 */
@Mapper
public interface OutboundItemLabelMapper extends BaseMapperX<OutboundItemLabel> {

    List<OutboundItemLabel> findOutboundItemLabelList(@Param("id") Long id);




   default List<OutboundItemLabel> findBoundItemLabelList(String number){
       LambdaQueryWrapperX<OutboundItemLabel> queryWrapperX = new LambdaQueryWrapperX<>();
       queryWrapperX.eq(OutboundItemLabel::getOutboundNumber,number);
       return  selectList(queryWrapperX);
   }


    default   List<OutboundItemLabel>  selectLabel(Long id, String saleItemNumber){
       return selectList(new LambdaQueryWrapperX<OutboundItemLabel>().eq(OutboundItemLabel::getId,id).eq(OutboundItemLabel::getSaleItemNumber,saleItemNumber));
    }

   default List<OutboundItemLabel> findboundItemLabeByItemNum(String outBoundItemNumber){
       LambdaQueryWrapperX<OutboundItemLabel> queryWrapperX = new LambdaQueryWrapperX<>();
       queryWrapperX.eq(OutboundItemLabel::getOutboundItemNumber,outBoundItemNumber);
       return  selectList(queryWrapperX);
   }

   default    List<OutboundItemLabel> findboundItemLabeBySaleItemNum(String saleItemNumber){
       LambdaQueryWrapperX<OutboundItemLabel> queryWrapperX = new LambdaQueryWrapperX<>();
       queryWrapperX.eq(OutboundItemLabel::getSaleItemNumber,saleItemNumber);
       return  selectList(queryWrapperX);
   }

//   default OutboundItemLabel findboundItemLabelBySaleItemNum(String saleItemNumber){
//       LambdaQueryWrapperX<OutboundItemLabel> queryWrapperX = new LambdaQueryWrapperX<>();
//       queryWrapperX.eq(OutboundItemLabel::getSaleItemNumber,saleItemNumber);
//       return  selectOne(queryWrapperX);
//   }
}
