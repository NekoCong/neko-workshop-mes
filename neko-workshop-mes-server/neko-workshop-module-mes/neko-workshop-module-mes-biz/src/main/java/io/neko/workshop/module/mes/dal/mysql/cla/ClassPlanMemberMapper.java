package io.neko.workshop.module.mes.dal.mysql.cla;

import io.neko.workshop.framework.common.pojo.PageResult;
import io.neko.workshop.framework.mybatis.core.mapper.BaseMapperX;
import io.neko.workshop.framework.mybatis.core.query.LambdaQueryWrapperX;
import io.neko.workshop.module.mes.controller.admin.cla.vo.ClaPlanMemberQueryVo;
import io.neko.workshop.module.mes.dal.dataobject.cla.ClassPlanMember;
import io.neko.workshop.module.mes.utils.StringUtils;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author 灵喵工坊
 * @time 2023/6/30 13:47
 */
@Mapper
public interface ClassPlanMemberMapper extends BaseMapperX<ClassPlanMember> {


  default   PageResult<ClassPlanMember> findClassPlanMemberPage(ClaPlanMemberQueryVo classPlanMemberPageReqVO){
      LambdaQueryWrapperX<ClassPlanMember> queryWrapperX = new LambdaQueryWrapperX<>();
      if(StringUtils.isNotBlank(classPlanMemberPageReqVO.getPlanCode())){
          queryWrapperX.eq(ClassPlanMember::getPlanCode,classPlanMemberPageReqVO.getPlanCode());
      }
      return  selectPage(classPlanMemberPageReqVO,queryWrapperX);
  }



}
