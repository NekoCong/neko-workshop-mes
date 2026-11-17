package io.neko.workshop.module.mes.service.impl.wm;

import io.neko.workshop.framework.common.pojo.PageResult;
import io.neko.workshop.framework.mybatis.core.query.LambdaQueryWrapperX;
import io.neko.workshop.module.mes.controller.admin.wm.vo.WareHouseLocationQueryVo;
import io.neko.workshop.module.mes.dal.dataobject.wm.WareHouseLocation;
import io.neko.workshop.module.mes.dal.mysql.wm.WareHouseLocationMapper;
import io.neko.workshop.module.mes.service.wm.WareHouseLocationService;
import org.springframework.stereotype.Service;
import java.util.List;
import lombok.RequiredArgsConstructor;

/**
 * 库位 Service 实现类
 *
 * @author 灵喵工坊
 */
@Service
@RequiredArgsConstructor
public class WareHouseLocationServiceImpl implements WareHouseLocationService {

    private final WareHouseLocationMapper wareHouseLocationMapper;


    @Override
    public Integer saveWareHouseLocation(WareHouseLocation wareHouseLocation) {
        return wareHouseLocationMapper.insert(wareHouseLocation);
    }

    @Override
    public Integer removeWareHouseLocationByIds(List<Long> ids) {
        return wareHouseLocationMapper.deleteBatchIds(ids);
    }

    @Override
    public WareHouseLocation findWareHouseLocationById(Long id) {
        return wareHouseLocationMapper.selectById(id);
    }

    @Override
    public Integer updateWareHouseLocation(WareHouseLocation wareHouseLocation) {
        return wareHouseLocationMapper.updateById(wareHouseLocation);
    }

    @Override
    public PageResult<WareHouseLocation> findPage(WareHouseLocationQueryVo wareHouseLocationQueryVo) {
        return wareHouseLocationMapper.findPage(wareHouseLocationQueryVo);
    }

    @Override
    public WareHouseLocation findWareHouseLocationByName(String locationName) {
        return wareHouseLocationMapper.selectOne(new LambdaQueryWrapperX<WareHouseLocation>().eq(WareHouseLocation::getLocationName,locationName));
    }


}
