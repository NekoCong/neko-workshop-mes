package io.neko.workshop.module.mes.dal.mysql.qc;

import io.neko.workshop.framework.common.pojo.PageResult;
import io.neko.workshop.framework.mybatis.core.mapper.BaseMapperX;
import io.neko.workshop.framework.mybatis.core.query.LambdaQueryWrapperX;
import io.neko.workshop.module.mes.controller.admin.qc.vo.OtherRecordQueryVo;
import io.neko.workshop.module.mes.dal.dataobject.qc.OtherRecord;
import io.neko.workshop.module.mes.utils.StringUtils;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author 灵喵工坊
 * @time 2023/8/19 10:43
 */
@Mapper
public interface OtherRecordMapper extends BaseMapperX<OtherRecord> {

    default PageResult<OtherRecord> findPage(OtherRecordQueryVo reqVO){

        LambdaQueryWrapperX<OtherRecord> queryWrapperX = new LambdaQueryWrapperX<>();

        if(StringUtils.isNotBlank(reqVO.getNumber())) {
            queryWrapperX.eq(OtherRecord::getNumber, reqVO.getNumber());
        }
        if(StringUtils.isNotBlank(reqVO.getWorkOrderCode())) {
            queryWrapperX.eq(OtherRecord::getWorkOrderCode, reqVO.getWorkOrderCode());
        }
        if(StringUtils.isNotBlank(reqVO.getModel())) {
            queryWrapperX.eq(OtherRecord::getModel, reqVO.getModel());
        }
        if(StringUtils.isNotBlank(reqVO.getSpec())) {
            queryWrapperX.eq(OtherRecord::getSpec, reqVO.getSpec());
        }
        if(StringUtils.isNotBlank(reqVO.getLineType())) {
            queryWrapperX.eq(OtherRecord::getLineType, reqVO.getLineType());
        }
        if(StringUtils.isBlank(reqVO.getNumber()) &&  StringUtils.isBlank(reqVO.getModel())
                && StringUtils.isBlank(reqVO.getSpec()) && StringUtils.isBlank(reqVO.getLineType())){
            queryWrapperX.isNotNull(OtherRecord::getId);
        }
        return  selectPage(reqVO,queryWrapperX);
    }
}
