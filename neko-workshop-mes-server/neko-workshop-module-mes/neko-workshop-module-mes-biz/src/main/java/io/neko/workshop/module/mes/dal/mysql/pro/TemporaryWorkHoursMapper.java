package io.neko.workshop.module.mes.dal.mysql.pro;

import io.neko.workshop.framework.common.pojo.PageResult;
import io.neko.workshop.framework.mybatis.core.mapper.BaseMapperX;
import io.neko.workshop.framework.mybatis.core.query.LambdaQueryWrapperX;
import io.neko.workshop.module.mes.controller.admin.pro.vo.TemporaryWorkHoursVo;
import io.neko.workshop.module.mes.dal.dataobject.pro.TemporaryWorkHours;
import io.neko.workshop.module.mes.utils.StringUtils;
import org.apache.ibatis.annotations.Mapper;

/**
 * 临时工时管理 Mapper
 * @author 灵喵工坊
 */
@Mapper
public interface TemporaryWorkHoursMapper extends BaseMapperX<TemporaryWorkHours> {

    /**
     *  临时工时管理条件分页
     * @param temporaryWorkHoursVO
     * @return PageResult<TemporaryWorkHours>
     */
  default   PageResult<TemporaryWorkHours> list(TemporaryWorkHoursVo temporaryWorkHoursVO){
      LambdaQueryWrapperX<TemporaryWorkHours> queryWrapper = new LambdaQueryWrapperX<>();
      if(StringUtils.isNotBlank(temporaryWorkHoursVO.getWorkhoursType())){
          queryWrapper.eq(TemporaryWorkHours::getWorkhoursType,temporaryWorkHoursVO.getWorkhoursType());
      }
      if(StringUtils.isNotBlank(temporaryWorkHoursVO.getWorkerName())){
          queryWrapper.eq(TemporaryWorkHours::getWorkerName,temporaryWorkHoursVO.getWorkerName());
      }
      if(StringUtils.isNotBlank(temporaryWorkHoursVO.getWorkhours())){
          queryWrapper.like(TemporaryWorkHours::getWorkhours,temporaryWorkHoursVO.getWorkhours());
      }
      if(StringUtils.isNotBlank(temporaryWorkHoursVO.getWorkshopName())){
          queryWrapper.like(TemporaryWorkHours::getWorkshopName,temporaryWorkHoursVO.getWorkshopName());
      }
      if (StringUtils.isNotBlank(temporaryWorkHoursVO.getCreateTime()) && StringUtils.isNotBlank(temporaryWorkHoursVO.getEndTime())) {
          queryWrapper.between(TemporaryWorkHours::getCreateTime, temporaryWorkHoursVO.getCreateTime(), temporaryWorkHoursVO.getEndTime());
      }
      if(StringUtils.isBlank(temporaryWorkHoursVO.getWorkhoursType()) && StringUtils.isBlank(temporaryWorkHoursVO.getWorkhours()) &&
      StringUtils.isBlank(temporaryWorkHoursVO.getWorkerName()) && StringUtils.isBlank(temporaryWorkHoursVO.getWorkshopName())
      && StringUtils.isBlank(temporaryWorkHoursVO.getCreateTime()) && StringUtils.isBlank(temporaryWorkHoursVO.getEndTime())){
          queryWrapper.isNotNull(TemporaryWorkHours::getId);
      }
      return  selectPage(temporaryWorkHoursVO,queryWrapper);
  }





}
