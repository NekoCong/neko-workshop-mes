package io.neko.workshop.module.mes.dal.mysql.qc;

import io.neko.workshop.framework.mybatis.core.mapper.BaseMapperX;
import io.neko.workshop.framework.mybatis.core.query.LambdaQueryWrapperX;
import io.neko.workshop.module.mes.dal.dataobject.qc.OtherRecord;
import io.neko.workshop.module.mes.dal.dataobject.qc.OtherRecordItem;
import io.neko.workshop.module.mes.utils.StringUtils;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;


/**
 * @author 灵喵工坊
 * @time 2023/8/19 10:28
 */
@Mapper
public interface OtherRecordItemMapper extends BaseMapperX<OtherRecordItem> {

    default List<OtherRecordItem> findOtherRecordItem(String recordNumber){
        LambdaQueryWrapperX<OtherRecordItem> lambdaQueryWrapperX = new LambdaQueryWrapperX<>();
        lambdaQueryWrapperX.eq(OtherRecordItem::getRecordNumber, recordNumber);
        return selectList(lambdaQueryWrapperX);
    }
}
