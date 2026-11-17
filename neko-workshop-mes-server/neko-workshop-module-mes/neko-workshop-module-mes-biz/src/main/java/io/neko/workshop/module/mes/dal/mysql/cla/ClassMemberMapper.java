package io.neko.workshop.module.mes.dal.mysql.cla;

import io.neko.workshop.framework.common.pojo.PageResult;
import io.neko.workshop.framework.mybatis.core.mapper.BaseMapperX;
import io.neko.workshop.framework.mybatis.core.query.LambdaQueryWrapperX;
import io.neko.workshop.module.mes.controller.admin.cla.vo.ClaMemberQueryVo;
import io.neko.workshop.module.mes.dal.dataobject.cla.ClassMember;
import io.neko.workshop.module.mes.utils.StringUtils;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author 灵喵工坊
 * @time 2023/6/27 16:01
 */
@Mapper
public interface ClassMemberMapper extends BaseMapperX<ClassMember> {

  default   PageResult<ClassMember> selectClassMemberByCode(ClaMemberQueryVo classMemberPageReqVO){
      LambdaQueryWrapperX<ClassMember> queryWrapperX = new LambdaQueryWrapperX<>();
      if(StringUtils.isNotBlank(classMemberPageReqVO.getTeamCode())){
          queryWrapperX.eq(ClassMember::getTeamCode,classMemberPageReqVO.getTeamCode());
      }
      return  selectPage(classMemberPageReqVO,queryWrapperX);
  }
}
