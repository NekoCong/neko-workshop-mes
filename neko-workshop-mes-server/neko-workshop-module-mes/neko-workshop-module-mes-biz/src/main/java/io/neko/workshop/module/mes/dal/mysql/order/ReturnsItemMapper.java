package io.neko.workshop.module.mes.dal.mysql.order;

import io.neko.workshop.framework.mybatis.core.mapper.BaseMapperX;
import io.neko.workshop.framework.mybatis.core.query.LambdaQueryWrapperX;
import io.neko.workshop.module.mes.dal.dataobject.order.ReturnsItem;
import io.neko.workshop.module.mes.dal.dataobject.order.SemiLabelItem;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author 灵喵工坊
 * @time 2023/7/18 17:03
 */
@Mapper
public interface ReturnsItemMapper extends BaseMapperX<ReturnsItem> {

    default List<ReturnsItem> selectReturnsItemByReturnNumber(String returnNumber){
        LambdaQueryWrapperX<ReturnsItem> queryWrapperX = new LambdaQueryWrapperX<>();
        queryWrapperX.eq(ReturnsItem::getReturnNumber,returnNumber);
        return selectList(queryWrapperX);
    }
}
