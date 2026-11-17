package io.neko.workshop.module.mes.dal.mysql.qc;

import io.neko.workshop.framework.common.pojo.PageResult;
import io.neko.workshop.framework.mybatis.core.mapper.BaseMapperX;
import io.neko.workshop.framework.mybatis.core.query.LambdaQueryWrapperX;
import io.neko.workshop.module.mes.controller.admin.qc.vo.ProjectQueryVo;
import io.neko.workshop.module.mes.dal.dataobject.qc.Project;
import io.neko.workshop.module.mes.utils.StringUtils;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author 灵喵工坊
 * @time 2023/7/6 13:20
 */
@Mapper
public interface ProjectMapper extends BaseMapperX<Project> {

  default   PageResult<Project> findPage(ProjectQueryVo reqVO){
      LambdaQueryWrapperX<Project> queryWrapperX = new LambdaQueryWrapperX<>();
      if(StringUtils.isNotBlank(reqVO.getProjectCode())){
          queryWrapperX.eq(Project::getProjectCode,reqVO.getProjectCode());
      }
      if(StringUtils.isNotBlank(reqVO.getProjectName())){
          queryWrapperX.eq(Project::getProjectName,reqVO.getProjectName());
      }
      if(StringUtils.isBlank(reqVO.getProjectName()) && StringUtils.isBlank(reqVO.getProjectCode())){
          queryWrapperX.isNotNull(Project::getId);
      }
      return  selectPage(reqVO,queryWrapperX);
  }


  default Project findProjectByCodeAndName(String projectCode, String projectName){
      LambdaQueryWrapperX<Project> queryWrapperX = new LambdaQueryWrapperX<>();
      queryWrapperX.eq(Project::getProjectCode,projectCode);
      queryWrapperX.eq(Project::getProjectName,projectName);
      return  selectOne(queryWrapperX);
  }
}
