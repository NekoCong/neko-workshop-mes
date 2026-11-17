package io.neko.workshop.module.mes.dal.mysql.qc;

import io.neko.workshop.framework.common.pojo.PageResult;
import io.neko.workshop.framework.mybatis.core.mapper.BaseMapperX;
import io.neko.workshop.framework.mybatis.core.query.LambdaQueryWrapperX;
import io.neko.workshop.module.mes.controller.admin.qc.vo.ReceiveStandardQueryVo;
import io.neko.workshop.module.mes.dal.dataobject.qc.ReceiveStandard;
import io.neko.workshop.module.mes.utils.StringUtils;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author 灵喵工坊
 * @time 2023/7/26 11:43
 */
@Mapper
public interface ReceiveStandardMapper extends BaseMapperX<ReceiveStandard> {

    default PageResult<ReceiveStandard> findPage(ReceiveStandardQueryVo receiveStandardQueryVo){

        LambdaQueryWrapperX<ReceiveStandard> queryWrapperX = new LambdaQueryWrapperX<>();

        if(StringUtils.isNotBlank(receiveStandardQueryVo.getNumber())) {
            queryWrapperX.eq(ReceiveStandard::getNumber, receiveStandardQueryVo.getNumber());
        }
        if(StringUtils.isNotBlank(receiveStandardQueryVo.getMethod())) {
            queryWrapperX.eq(ReceiveStandard::getMethod, receiveStandardQueryVo.getMethod());
        }
        if(StringUtils.isNotBlank(receiveStandardQueryVo.getItemCode())) {
            queryWrapperX.eq(ReceiveStandard::getItemCode, receiveStandardQueryVo.getItemCode());
        }

        if(StringUtils.isBlank(receiveStandardQueryVo.getNumber()) &&  StringUtils.isBlank(receiveStandardQueryVo.getMethod())
                && StringUtils.isBlank(receiveStandardQueryVo.getItemCode())){
            queryWrapperX.isNotNull(ReceiveStandard::getId);
        }
        return  selectPage(receiveStandardQueryVo,queryWrapperX);
    }

    /*default List<ReceiveStandard> findReceiveStandardByRecNumber(String number){
        LambdaQueryWrapperX<ReceiveStandard> queryWrapperX = new LambdaQueryWrapperX<>();
        queryWrapperX.like(ReceiveStandard::getRecNumber, number);
        return selectList(queryWrapperX);
    }*/
    List<ReceiveStandard> findReceiveStandardByItemCode(@Param("itemCode") String itemCode);
}
