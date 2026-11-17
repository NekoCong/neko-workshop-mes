package io.neko.workshop.module.mes.dal.mysql.wm;

import io.neko.workshop.framework.common.pojo.PageResult;
import io.neko.workshop.framework.mybatis.core.mapper.BaseMapperX;
import io.neko.workshop.framework.mybatis.core.query.LambdaQueryWrapperX;
import io.neko.workshop.module.mes.controller.admin.wm.vo.WareHouseLocationQueryVo;
import io.neko.workshop.module.mes.dal.dataobject.wm.WareHouseLocation;
import io.neko.workshop.module.mes.utils.StringUtils;
import org.apache.ibatis.annotations.Mapper;


/**
 * 库位 Mapper
 * @author 灵喵工坊MES
 */
@Mapper
public interface WareHouseLocationMapper extends BaseMapperX<WareHouseLocation> {


   default PageResult<WareHouseLocation> findPage(WareHouseLocationQueryVo wareHouseLocationQueryVo){
       LambdaQueryWrapperX<WareHouseLocation> queryWrapperX = new LambdaQueryWrapperX<>();
       if(StringUtils.isNotBlank(wareHouseLocationQueryVo.getLocationNumber())){
           queryWrapperX.eq(WareHouseLocation::getLocationNumber,wareHouseLocationQueryVo.getLocationNumber());
       }
       if(StringUtils.isNotBlank(wareHouseLocationQueryVo.getLocationName())){
           queryWrapperX.eq(WareHouseLocation::getLocationName,wareHouseLocationQueryVo.getLocationName());
       }
       if(StringUtils.isBlank(wareHouseLocationQueryVo.getLocationNumber()) && StringUtils.isBlank(wareHouseLocationQueryVo.getLocationName())){
           queryWrapperX.isNotNull(WareHouseLocation::getId);
       }
       return  selectPage(wareHouseLocationQueryVo,queryWrapperX);
   }


}
