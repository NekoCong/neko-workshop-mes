package io.neko.workshop.module.mes.service.wm;

import io.neko.workshop.framework.common.pojo.PageResult;
import io.neko.workshop.module.mes.controller.admin.wm.vo.WareHouseLocationQueryVo;
import io.neko.workshop.module.mes.dal.dataobject.wm.WareHouseLocation;

import java.util.List;

/**
 * 库位 Service
 * @author 灵喵工坊
 */
public interface WareHouseLocationService {



    Integer saveWareHouseLocation(WareHouseLocation wareHouseLocation);

    Integer removeWareHouseLocationByIds(List<Long> ids);

    WareHouseLocation findWareHouseLocationById(Long id);

    Integer updateWareHouseLocation(WareHouseLocation wareHouseLocation);

    PageResult<WareHouseLocation> findPage(WareHouseLocationQueryVo wareHouseLocationQueryVo);

    WareHouseLocation findWareHouseLocationByName(String locationName);


}
