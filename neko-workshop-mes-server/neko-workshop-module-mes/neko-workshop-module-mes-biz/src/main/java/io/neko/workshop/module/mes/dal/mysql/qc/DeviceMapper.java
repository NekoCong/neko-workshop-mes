package io.neko.workshop.module.mes.dal.mysql.qc;

import io.neko.workshop.framework.common.pojo.PageResult;
import io.neko.workshop.framework.mybatis.core.mapper.BaseMapperX;
import io.neko.workshop.framework.mybatis.core.query.LambdaQueryWrapperX;
import io.neko.workshop.module.mes.controller.admin.qc.vo.DeviceQueryVo;
import io.neko.workshop.module.mes.dal.dataobject.qc.Device;
import io.neko.workshop.module.mes.utils.StringUtils;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author 灵喵工坊
 * @time 2023/7/6 11:16
 */
@Mapper
public interface DeviceMapper extends BaseMapperX<Device> {

    /**
     * 检验设备
     * @param queryVo
     * @return  PageResult<Device>
     */
   default PageResult<Device> findPage(DeviceQueryVo queryVo){
       LambdaQueryWrapperX<Device> queryWrapperX = new LambdaQueryWrapperX<>();
       if(StringUtils.isNotBlank(queryVo.getDeviceCode())){
           queryWrapperX.eq(Device::getDeviceCode,queryVo.getDeviceCode());
       }
       if(StringUtils.isNotBlank(queryVo.getDeviceName())){
           queryWrapperX.eq(Device::getDeviceName,queryVo.getDeviceName());
       }
       if(StringUtils.isBlank(queryVo.getDeviceCode()) && StringUtils.isBlank(queryVo.getDeviceName())){
           queryWrapperX.isNotNull(Device::getId);
       }
       return  selectPage(queryVo,queryWrapperX);
   }



}
