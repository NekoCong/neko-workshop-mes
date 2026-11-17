package io.neko.workshop.module.mes.dal.mysql.order;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import io.neko.workshop.framework.mybatis.core.mapper.BaseMapperX;
import io.neko.workshop.framework.mybatis.core.query.LambdaQueryWrapperX;
import io.neko.workshop.module.mes.dal.dataobject.order.SaleItem;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import java.util.List;

/**
 * 销售订单子类Mapper
 *
 * @author 灵喵工坊MES
 */
@Mapper
public interface SaleItemMapper extends BaseMapperX<SaleItem> {

    @Select("select * from order_sale_item where sale_number = #{saleNumber}")
    List<SaleItem> findSaleItemListBySaleNumber(String saleNumber);

   default SaleItem findSaleItemBySaleNumber(String saleItemNumber){
       LambdaQueryWrapperX<SaleItem> queryWrapperX = new LambdaQueryWrapperX<>();
       queryWrapperX.eq(SaleItem::getNumber,saleItemNumber);
       queryWrapperX.last(" LIMIT 1");
       return  selectOne(queryWrapperX);
   }

   default List<SaleItem> findSaleItemAllBySaleNumber(String saleNumber){
       return  selectList(new LambdaQueryWrapper<SaleItem>().eq(SaleItem::getSaleNumber,saleNumber));
   }
}
