package io.neko.workshop.module.mes.dal.mysql.dv;

import io.neko.workshop.framework.common.pojo.PageResult;
import io.neko.workshop.framework.mybatis.core.mapper.BaseMapperX;
import io.neko.workshop.framework.mybatis.core.query.LambdaQueryWrapperX;
import io.neko.workshop.module.mes.controller.admin.dv.vo.MachineryStatusQueryVo;
import io.neko.workshop.module.mes.dal.dataobject.dv.MachineryStatus;
import io.neko.workshop.module.mes.utils.StringUtils;
import org.apache.ibatis.annotations.Mapper;


/**
 * 设备状态 Mapper
 * @author 灵喵工坊
 */
@Mapper
public interface MachineryStatusMapper extends BaseMapperX<MachineryStatus> {


    /**
     * 设备状态条件分页查询
     * @param machineryStatusPageVO
     * @return PageResult<MachineryStatus>
     */
  default   PageResult<MachineryStatus> findList(MachineryStatusQueryVo machineryStatusPageVO){
      LambdaQueryWrapperX<MachineryStatus> queryWrapperX = new LambdaQueryWrapperX<>();
      if(StringUtils.isNotBlank(machineryStatusPageVO.getStatusName())){
          queryWrapperX.eq(MachineryStatus::getStatusName,machineryStatusPageVO.getStatusName());
      }
      if(StringUtils.isBlank(machineryStatusPageVO.getStatusName())){
          queryWrapperX.isNotNull(MachineryStatus::getId);
      }
      return  selectPage(machineryStatusPageVO,queryWrapperX);
  }










}
