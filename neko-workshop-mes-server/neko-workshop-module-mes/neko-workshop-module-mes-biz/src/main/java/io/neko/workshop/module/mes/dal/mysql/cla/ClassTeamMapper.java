package io.neko.workshop.module.mes.dal.mysql.cla;

import io.neko.workshop.framework.common.pojo.PageResult;
import io.neko.workshop.framework.mybatis.core.mapper.BaseMapperX;
import io.neko.workshop.framework.mybatis.core.query.LambdaQueryWrapperX;
import io.neko.workshop.module.mes.controller.admin.cla.vo.ClaTeamQueryVo;
import io.neko.workshop.module.mes.dal.dataobject.cla.ClassTeam;
import io.neko.workshop.module.mes.utils.StringUtils;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author 灵喵工坊
 * @time 2023/6/26 9:41
 */
@Mapper
public interface ClassTeamMapper extends BaseMapperX<ClassTeam> {

    /**
     *  班组分页条件查询
      * @param calTeamPageReqVO
     * @return PageResult<CalTeam>
     */
  default   PageResult<ClassTeam> getCalTeamPage(ClaTeamQueryVo calTeamPageReqVO){
      LambdaQueryWrapperX<ClassTeam> queryWrapperX = new LambdaQueryWrapperX<>();
      if(StringUtils.isNotBlank(calTeamPageReqVO.getTeamType())){
          queryWrapperX.eq(ClassTeam::getTeamType,calTeamPageReqVO.getTeamType());
      }

      if(StringUtils.isNotBlank(calTeamPageReqVO.getTeamCode())){
          queryWrapperX.eq(ClassTeam::getTeamCode,calTeamPageReqVO.getTeamCode());
      }

      if(StringUtils.isNotBlank(calTeamPageReqVO.getTeamName())){
          queryWrapperX.eq(ClassTeam::getTeamName,calTeamPageReqVO.getTeamName());
      }
      if(StringUtils.isBlank(calTeamPageReqVO.getTeamType()) && StringUtils.isBlank(calTeamPageReqVO.getTeamCode()) && StringUtils.isBlank(calTeamPageReqVO.getTeamName())){
          queryWrapperX.isNotNull(ClassTeam::getId);
      }

      return  selectPage(calTeamPageReqVO,queryWrapperX);
  }
}
