package io.neko.workshop.module.mes.dal.mysql.order;

import io.neko.workshop.framework.mybatis.core.mapper.BaseMapperX;
import io.neko.workshop.framework.mybatis.core.query.LambdaQueryWrapperX;
import io.neko.workshop.module.mes.dal.dataobject.order.PurchaseOrderItem;
import io.neko.workshop.module.mes.dal.dataobject.order.SemiLabelItem;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author 灵喵工坊
 * @time 2023/8/9 17:06
 */
@Mapper
public interface SemiLabelItemMapper extends BaseMapperX<SemiLabelItem> {

    default List<SemiLabelItem> selectSemiLabelItemByNumber(String semiNumber){
        LambdaQueryWrapperX<SemiLabelItem> queryWrapperX = new LambdaQueryWrapperX<>();
        queryWrapperX.eq(SemiLabelItem::getSemiNumber,semiNumber);
        return selectList(queryWrapperX);
    }

}
