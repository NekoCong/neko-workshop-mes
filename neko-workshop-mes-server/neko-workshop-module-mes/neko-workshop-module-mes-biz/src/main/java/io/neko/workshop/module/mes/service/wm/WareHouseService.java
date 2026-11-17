package io.neko.workshop.module.mes.service.wm;

import io.neko.workshop.framework.common.pojo.PageResult;
import io.neko.workshop.module.mes.controller.admin.wm.vo.WareHouseQueryVo;
import io.neko.workshop.module.mes.dal.dataobject.wm.WareHouse;
import java.util.List;



/**
 * 仓库 Service
 * @author 灵喵工坊MES
 */
public interface WareHouseService {



    Integer saveWareHouse(WareHouse wareHouse);

    Integer removeWareHouseByIds(List<Long> ids);

    WareHouse findWareHouseById(Long id);

    Integer updateWareHouse(WareHouse wareHouse);

    PageResult<WareHouse> findPage(WareHouseQueryVo wareHouseQueryVo);

    WareHouse findWareHouseByName(String warehouseName);

    List<WareHouse> listAll();
}
