package io.neko.workshop.module.mes.dal.mysql.qc;

import io.neko.workshop.framework.mybatis.core.mapper.BaseMapperX;
import io.neko.workshop.framework.mybatis.core.query.LambdaQueryWrapperX;
import io.neko.workshop.module.mes.dal.dataobject.qc.ReceiveStandardItem;
import io.neko.workshop.module.mes.dal.dataobject.qc.Standard;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author 灵喵工坊
 * @time 2023/7/26 11:43
 */
@Mapper
public interface ReceiveStandardItemMapper extends BaseMapperX<ReceiveStandardItem> {

    /*default List<ReceiveStandardItem> findReceiveStandardItemByRecNumber(String recNumber){
        LambdaQueryWrapperX<ReceiveStandardItem> queryWrapperX = new LambdaQueryWrapperX<>();
        queryWrapperX.like(ReceiveStandardItem::getRecNumber, recNumber);
        return selectList(queryWrapperX);
    }*/

    List<ReceiveStandardItem> findReceiveStandardItemByItemCode(@Param("itemCode") String itemCode);

}
