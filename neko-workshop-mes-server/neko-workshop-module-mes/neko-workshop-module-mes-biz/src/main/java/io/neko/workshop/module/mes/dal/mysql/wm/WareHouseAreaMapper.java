package io.neko.workshop.module.mes.dal.mysql.wm;

import io.neko.workshop.framework.common.pojo.PageResult;
import io.neko.workshop.framework.mybatis.core.mapper.BaseMapperX;
import io.neko.workshop.framework.mybatis.core.query.LambdaQueryWrapperX;
import io.neko.workshop.module.mes.controller.admin.wm.vo.WareHouseAreaQueryVo;
import io.neko.workshop.module.mes.dal.dataobject.wm.WareHouseArea;
import io.neko.workshop.module.mes.utils.StringUtils;
import org.apache.ibatis.annotations.Mapper;


/**
 * 库区 Mapper
 * @author 灵喵工坊MES
 */
@Mapper
public interface WareHouseAreaMapper extends BaseMapperX<WareHouseArea> {


   default PageResult<WareHouseArea> findPage(WareHouseAreaQueryVo wareHouseAreaQueryVo){
       LambdaQueryWrapperX<WareHouseArea> queryWrapperX = new LambdaQueryWrapperX<>();
       if(StringUtils.isNotBlank(wareHouseAreaQueryVo.getAreaNumber())){
           queryWrapperX.eq(WareHouseArea::getAreaNumber,wareHouseAreaQueryVo.getAreaNumber());
       }
       if(StringUtils.isNotBlank(wareHouseAreaQueryVo.getAreaName())){
           queryWrapperX.eq(WareHouseArea::getAreaName,wareHouseAreaQueryVo.getAreaName());
       }
       if(StringUtils.isBlank(wareHouseAreaQueryVo.getAreaNumber()) && StringUtils.isBlank(wareHouseAreaQueryVo.getAreaName())){
           queryWrapperX.isNotNull(WareHouseArea::getId);
       }
       return  selectPage(wareHouseAreaQueryVo,queryWrapperX);
   }

}
