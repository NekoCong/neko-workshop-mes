package io.neko.workshop.module.mes.dal.mysql.qc;

import io.neko.workshop.framework.mybatis.core.mapper.BaseMapperX;
import io.neko.workshop.framework.mybatis.core.query.LambdaQueryWrapperX;
import io.neko.workshop.module.mes.controller.admin.qc.vo.OtherStandardResultVo;
import io.neko.workshop.module.mes.dal.dataobject.qc.OtherRecordResult;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;


/**
 * @author 灵喵工坊
 * @time 2023/8/19 14:28
 */
@Mapper
public interface OtherRecordResultMapper extends BaseMapperX<OtherRecordResult> {

    default OtherRecordResult selectOtherRecordResultByNumber(String recordNumber){
        LambdaQueryWrapperX<OtherRecordResult> queryWrapperX = new LambdaQueryWrapperX<>();
        queryWrapperX.eq(OtherRecordResult::getRecordNumber, recordNumber);
        return selectOne(queryWrapperX);
    }

    public OtherStandardResultVo getAcount(OtherStandardResultVo otherStandardResultVo);
}
