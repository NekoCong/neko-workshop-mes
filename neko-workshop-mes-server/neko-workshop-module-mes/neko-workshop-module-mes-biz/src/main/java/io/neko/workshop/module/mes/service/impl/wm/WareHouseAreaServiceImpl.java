package io.neko.workshop.module.mes.service.impl.wm;

import io.neko.workshop.framework.common.pojo.PageResult;
import io.neko.workshop.framework.mybatis.core.query.LambdaQueryWrapperX;
import io.neko.workshop.module.mes.controller.admin.wm.vo.WareHouseAreaQueryVo;
import io.neko.workshop.module.mes.dal.dataobject.wm.WareHouseArea;
import io.neko.workshop.module.mes.dal.mysql.wm.WareHouseAreaMapper;
import io.neko.workshop.module.mes.service.wm.WareHouseAreaService;
import org.springframework.stereotype.Service;
import java.util.List;
import lombok.RequiredArgsConstructor;


/**
 * 库区 Service
 * @author 灵喵工坊
 */
@Service
@RequiredArgsConstructor
public class WareHouseAreaServiceImpl implements WareHouseAreaService {

    private final WareHouseAreaMapper wareHouseAreaMapper;


    @Override
    public Integer saveWareHouseArea(WareHouseArea wareHouseArea) {
        return wareHouseAreaMapper.insert(wareHouseArea);
    }

    @Override
    public Integer removeWareHouseAreaByIds(List<Long> ids) {
        return wareHouseAreaMapper.deleteBatchIds(ids);
    }

    @Override
    public WareHouseArea findWareHouseAreaById(Long id) {
        return wareHouseAreaMapper.selectById(id);
    }

    @Override
    public Integer updateWareHouseArea(WareHouseArea wareHouseArea) {
        return wareHouseAreaMapper.updateById(wareHouseArea);
    }

    @Override
    public PageResult<WareHouseArea> findPage(WareHouseAreaQueryVo wareHouseAreaQueryVo) {
        return wareHouseAreaMapper.findPage(wareHouseAreaQueryVo);
    }

    @Override
    public WareHouseArea findWareHouseAreaByName(String areaName) {
        return wareHouseAreaMapper.selectOne(new LambdaQueryWrapperX<WareHouseArea>().eq(WareHouseArea::getAreaName,areaName));
    }
}
