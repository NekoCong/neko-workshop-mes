package io.neko.workshop.module.mes.dal.mysql.qc;

import io.neko.workshop.framework.common.pojo.PageResult;
import io.neko.workshop.framework.mybatis.core.mapper.BaseMapperX;
import io.neko.workshop.framework.mybatis.core.query.LambdaQueryWrapperX;
import io.neko.workshop.module.mes.controller.admin.qc.vo.ProjectClassifyQueryVo;
import io.neko.workshop.module.mes.dal.dataobject.qc.ProjectClassify;
import io.neko.workshop.module.mes.utils.StringUtils;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author 灵喵工坊
 * @time 2023/7/6 11:38
 */
@Mapper
public interface ProjectClassifyMapper extends BaseMapperX<ProjectClassify> {

    /**
     *  检测项目分类条件分页查询
      * @param inspectProjectClassifyPageVO
     * @return  PageResult<InspectProjectClassify>
     */
   default   PageResult<ProjectClassify> findPage(ProjectClassifyQueryVo inspectProjectClassifyPageVO){
      LambdaQueryWrapperX<ProjectClassify> queryWrapperX = new LambdaQueryWrapperX<>();
      if(StringUtils.isNotBlank(inspectProjectClassifyPageVO.getProjectCode())){
          queryWrapperX.eq(ProjectClassify::getProjectCode,inspectProjectClassifyPageVO.getProjectCode());
      }
      if(StringUtils.isNotBlank(inspectProjectClassifyPageVO.getProjectName())){
          queryWrapperX.eq(ProjectClassify::getProjectName,inspectProjectClassifyPageVO.getProjectName());
      }
      if(StringUtils.isBlank(inspectProjectClassifyPageVO.getProjectCode()) && StringUtils.isBlank(inspectProjectClassifyPageVO.getProjectName())){
          queryWrapperX.isNotNull(ProjectClassify::getId);
      }
      return  selectPage(inspectProjectClassifyPageVO,queryWrapperX);
   }


    /**
     * 根据分类编号，名称查询分类信息
     * @param projectCode
     * @param projectName
     * @return InspectProjectClassify
     */
   default ProjectClassify findClassifyByCodeAndName(String projectCode, String projectName){
       LambdaQueryWrapperX<ProjectClassify> queryWrapperX = new LambdaQueryWrapperX<>();
       queryWrapperX.eq(ProjectClassify::getProjectCode,projectCode);
       queryWrapperX.eq(ProjectClassify::getProjectName,projectName);
       return  selectOne(queryWrapperX);
   }



}
