package io.neko.workshop.module.mes.dal.mysql.qc;

import io.neko.workshop.framework.common.pojo.PageResult;
import io.neko.workshop.framework.mybatis.core.mapper.BaseMapperX;
import io.neko.workshop.framework.mybatis.core.query.LambdaQueryWrapperX;
import io.neko.workshop.module.mes.controller.admin.qc.vo.WayQueryVo;
import io.neko.workshop.module.mes.dal.dataobject.qc.Way;
import io.neko.workshop.module.mes.utils.StringUtils;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author 灵喵工坊
 * @time 2023/7/6 10:53
 */
@Mapper
public interface WayMapper extends BaseMapperX<Way> {



    /**
     * 检验方式条件分页查询
     * @param reqVO
     * @return PageResult<InspectMethod>
     */
     default PageResult<Way> findPage(WayQueryVo reqVO){
       LambdaQueryWrapperX<Way> queryWrapperX = new LambdaQueryWrapperX<>();

       if(StringUtils.isNotBlank(reqVO.getInspectCode())){
           queryWrapperX.eq(Way::getInspectCode,reqVO.getInspectCode());
       }
       if(StringUtils.isNotBlank(reqVO.getInspectName())){
           queryWrapperX.like(Way::getInspectName,reqVO.getInspectName());
       }
       if(StringUtils.isBlank(reqVO.getInspectCode()) && StringUtils.isBlank(reqVO.getInspectName())){
            queryWrapperX.isNotNull(Way::getId);
       }
       return  selectPage(reqVO,queryWrapperX);
     }


    /**
     *  根据检测编号与检测名称查询检测方式
     * @param inspectCode
     * @param inspectName
     * @return InspectMethod
     */
      default Way findMethodByCodeAndName(String inspectCode, String inspectName){
          LambdaQueryWrapperX<Way> queryWrapperX = new LambdaQueryWrapperX<>();
          queryWrapperX.eq(Way::getInspectCode,inspectCode);
          queryWrapperX.eq(Way::getInspectName,inspectName);
          return  selectOne(queryWrapperX);
      }
}
