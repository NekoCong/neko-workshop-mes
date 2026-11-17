package io.neko.workshop.module.mes.service.wm;

import io.neko.workshop.framework.common.pojo.PageResult;
import io.neko.workshop.module.mes.controller.admin.wm.vo.WareHouseAreaQueryVo;
import io.neko.workshop.module.mes.dal.dataobject.wm.WareHouseArea;
import java.util.List;

/**
 * 库区 Service
 * @author 灵喵工坊MES
 */
public interface WareHouseAreaService {


    Integer saveWareHouseArea(WareHouseArea wareHouseArea);

    Integer removeWareHouseAreaByIds(List<Long> ids);

    WareHouseArea findWareHouseAreaById(Long id);

    Integer updateWareHouseArea( WareHouseArea wareHouseArea);

    PageResult<WareHouseArea> findPage(WareHouseAreaQueryVo wareHouseAreaQueryVo);

    WareHouseArea findWareHouseAreaByName(String areaName);
}
