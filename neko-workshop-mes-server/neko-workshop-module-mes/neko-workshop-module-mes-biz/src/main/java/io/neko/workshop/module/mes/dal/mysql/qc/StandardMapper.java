package io.neko.workshop.module.mes.dal.mysql.qc;

import io.neko.workshop.framework.common.pojo.PageResult;
import io.neko.workshop.framework.mybatis.core.mapper.BaseMapperX;
import io.neko.workshop.framework.mybatis.core.query.LambdaQueryWrapperX;
import io.neko.workshop.module.mes.controller.admin.qc.vo.StandardQueryVo;
import io.neko.workshop.module.mes.dal.dataobject.qc.Standard;
import io.neko.workshop.module.mes.utils.StringUtils;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author 灵喵工坊
 * @time 2023/7/6 13:43
 */
@Mapper
public interface StandardMapper extends BaseMapperX<Standard> {


   default PageResult<Standard> findPage(StandardQueryVo reqVO){

       LambdaQueryWrapperX<Standard> queryWrapperX = new LambdaQueryWrapperX<>();

       if(StringUtils.isNotBlank(reqVO.getInspectCode())) {
           queryWrapperX.eq(Standard::getInspectCode, reqVO.getInspectCode());
       }
       if(StringUtils.isNotBlank(reqVO.getProcessName())) {
           queryWrapperX.like(Standard::getProcessName, reqVO.getProcessName());
       }
       if(StringUtils.isNotBlank(reqVO.getInspectScenario())) {
           queryWrapperX.eq(Standard::getInspectScenario, reqVO.getInspectScenario());
       }

       if(StringUtils.isNotBlank(reqVO.getSelect())) {
           queryWrapperX.eq(Standard::getInspectMethod, reqVO.getSelect());
       }
       if(StringUtils.isBlank(reqVO.getInspectCode()) &&  StringUtils.isBlank(reqVO.getProcessName()) && StringUtils.isBlank(reqVO.getInspectScenario()) && StringUtils.isBlank(reqVO.getSelect())){
           queryWrapperX.isNotNull(Standard::getId);
       }
       return  selectPage(reqVO,queryWrapperX);
   }


   default List<Standard> findStandByProcessCode(String code){
       LambdaQueryWrapperX<Standard> queryWrapperX = new LambdaQueryWrapperX<>();
       queryWrapperX.like(Standard::getProcessCode,code);
       return  selectList(queryWrapperX);
   }
}
