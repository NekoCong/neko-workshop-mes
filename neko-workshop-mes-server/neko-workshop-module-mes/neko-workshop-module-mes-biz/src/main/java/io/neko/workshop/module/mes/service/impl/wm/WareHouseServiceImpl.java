package io.neko.workshop.module.mes.service.impl.wm;

import io.neko.workshop.framework.common.pojo.PageResult;
import io.neko.workshop.framework.mybatis.core.query.LambdaQueryWrapperX;
import io.neko.workshop.module.mes.controller.admin.wm.vo.WareHouseQueryVo;
import io.neko.workshop.module.mes.dal.dataobject.wm.WareHouse;
import io.neko.workshop.module.mes.dal.mysql.wm.WareHouseMapper;
import io.neko.workshop.module.mes.service.wm.WareHouseService;
import org.springframework.stereotype.Service;
import java.util.Date;
import java.util.List;
import lombok.RequiredArgsConstructor;

/**
 * 仓库 Service 实现类
 * @author 灵喵工坊
 */
@Service
@RequiredArgsConstructor
public class WareHouseServiceImpl implements WareHouseService {

    private final WareHouseMapper warehouseMapper;


    @Override
    public Integer saveWareHouse(WareHouse wareHouse) {
        return warehouseMapper.insert(wareHouse);
    }



    @Override
    public Integer removeWareHouseByIds(List<Long> ids) {
        return warehouseMapper.deleteBatchIds(ids);
    }

    @Override
    public WareHouse findWareHouseById(Long id) {
        return warehouseMapper.selectById(id);
    }

    @Override
    public Integer updateWareHouse(WareHouse wareHouse) {
        return warehouseMapper.updateById(wareHouse);
    }

    @Override
    public PageResult<WareHouse> findPage(WareHouseQueryVo wareHouseQueryVo) {
        return warehouseMapper.findPage(wareHouseQueryVo);
    }

    @Override
    public WareHouse findWareHouseByName(String warehouseName) {
        return warehouseMapper.selectOne(new LambdaQueryWrapperX<WareHouse>().eq(WareHouse::getWarehouseName,warehouseName));
    }

    @Override
    public List<WareHouse> listAll() {
        return warehouseMapper.selectList();
    }


}
