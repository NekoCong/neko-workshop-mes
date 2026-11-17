package io.neko.workshop.module.mes.dal.mysql.cla;

import io.neko.workshop.framework.common.pojo.PageResult;
import io.neko.workshop.framework.mybatis.core.mapper.BaseMapperX;
import io.neko.workshop.framework.mybatis.core.query.LambdaQueryWrapperX;
import io.neko.workshop.module.mes.controller.admin.cla.vo.ClaTeamTypeQueryVo;
import io.neko.workshop.module.mes.dal.dataobject.cla.ClassTeamType;
import io.neko.workshop.module.mes.utils.StringUtils;
import org.apache.ibatis.annotations.Mapper;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;



/**
 * 班组类型 Mapper接口
 * @author 灵喵工坊
 */
@Mapper
public interface ClassTeamTypeMapper extends BaseMapperX<ClassTeamType> {


    /**
     * 班组类型条件分页查询
     * @param calTeamTypePageReqVO
     * @return  PageResult<CalTeamType>
     */
     default    PageResult<ClassTeamType> selectPage(ClaTeamTypeQueryVo calTeamTypePageReqVO){
         LambdaQueryWrapperX<ClassTeamType> queryWrapperX = new LambdaQueryWrapperX<>();
         if(StringUtils.isNotBlank(calTeamTypePageReqVO.getTypeName())){
             queryWrapperX.eq(ClassTeamType::getTypeName,calTeamTypePageReqVO.getTypeName());
         }
         if(StringUtils.isNotBlank(calTeamTypePageReqVO.getCreator())){
             queryWrapperX.eq(ClassTeamType::getCreator,calTeamTypePageReqVO.getCreator());
         }
         if(StringUtils.isNotBlank(calTeamTypePageReqVO.getCreateTime())){
             LocalDate createDate = LocalDate.parse(calTeamTypePageReqVO.getCreateTime(), DateTimeFormatter.ofPattern("yyyy-MM-dd"));
             queryWrapperX.like(ClassTeamType::getCreateTime,createDate);
         }
         if(StringUtils.isBlank(calTeamTypePageReqVO.getTypeName()) && StringUtils.isBlank(calTeamTypePageReqVO.getCreator()) && StringUtils.isBlank(calTeamTypePageReqVO.getCreateTime())){
             queryWrapperX.isNotNull(ClassTeamType::getId);
         }
         return  selectPage(calTeamTypePageReqVO,queryWrapperX);
     }




}
