package io.neko.workshop.module.mes.service.impl.dv;

import io.neko.workshop.framework.common.pojo.PageResult;
import io.neko.workshop.framework.mybatis.core.query.LambdaQueryWrapperX;
import io.neko.workshop.module.mes.controller.admin.dv.vo.MachineryStatusQueryVo;
import io.neko.workshop.module.mes.dal.dataobject.dv.MachineryStatus;
import io.neko.workshop.module.mes.dal.mysql.dv.MachineryStatusMapper;
import io.neko.workshop.module.mes.service.dv.MachineryStatusService;
import org.springframework.stereotype.Service;
import java.util.Date;
import java.util.List;
import lombok.RequiredArgsConstructor;


/**
 * @author 灵喵工坊
 * @time 2023/6/20 15:33
 */
@Service
@RequiredArgsConstructor
public class MachineryStatusServiceImpl implements MachineryStatusService {


    private final MachineryStatusMapper machineryStatusMapper;


    @Override
    public Integer save(MachineryStatus machineryStatus) {
      //  machineryStatus.setCreateTime(LocalDateTime.now());
         machineryStatus.setCreateTime(new Date());
        return machineryStatusMapper.insert(machineryStatus);
    }

    @Override
    public Integer batch(List<Long> ids) {
        return machineryStatusMapper.deleteBatchIds(ids);
    }

    @Override
    public MachineryStatus getMachineryStatusById(Long id) {
        return machineryStatusMapper.selectById(id);
    }

    @Override
    public Integer update(MachineryStatus machineryStatus) {
        MachineryStatus machineryStatusDO = machineryStatusMapper.selectById(machineryStatus.getId());
        machineryStatus.setCreateTime(machineryStatusDO.getCreateTime());
        machineryStatus.setUpdateTime(new Date());
        return machineryStatusMapper.updateById(machineryStatus);
    }


    @Override
    public PageResult<MachineryStatus> findList(MachineryStatusQueryVo machineryStatusPageVO) {
        return machineryStatusMapper.findList(machineryStatusPageVO);
    }

    @Override
    public MachineryStatus findByStatusName(String statusName) {
        LambdaQueryWrapperX<MachineryStatus> queryWrapperX = new LambdaQueryWrapperX<>();
        queryWrapperX.eq(MachineryStatus::getStatusName,statusName);
        return machineryStatusMapper.selectOne(queryWrapperX);
    }


}
