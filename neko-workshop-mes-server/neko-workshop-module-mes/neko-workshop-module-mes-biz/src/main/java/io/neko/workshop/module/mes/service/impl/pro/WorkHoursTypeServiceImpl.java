package io.neko.workshop.module.mes.service.impl.pro;

import io.neko.workshop.framework.common.pojo.PageResult;
import io.neko.workshop.framework.mybatis.core.query.LambdaQueryWrapperX;
import io.neko.workshop.module.mes.controller.admin.pro.vo.WorkHoursTypeVo;
import io.neko.workshop.module.mes.dal.dataobject.pro.WorkHoursType;
import io.neko.workshop.module.mes.dal.mysql.pro.WorkHoursTypeMapper;
import io.neko.workshop.module.mes.service.pro.WorkHoursTypeService;
import org.springframework.stereotype.Service;
import java.util.Date;
import java.util.List;
import lombok.RequiredArgsConstructor;



@Service
@RequiredArgsConstructor
public class WorkHoursTypeServiceImpl implements WorkHoursTypeService {

    private final WorkHoursTypeMapper workHoursTypeMapper;


    @Override
    public Integer save(WorkHoursType workHoursType) {

        workHoursType.setCreateTime(new Date());
        return workHoursTypeMapper.insert(workHoursType);
    }

    @Override
    public Integer removeByIds(List<Long> ids) {
        return workHoursTypeMapper.deleteBatchIds(ids);
    }

    @Override
    public WorkHoursType findById(Long id) {
        return workHoursTypeMapper.selectById(id);
    }

    @Override
    public Integer update(WorkHoursType workHoursType) {
        WorkHoursType workHoursType1 = workHoursTypeMapper.selectById(workHoursType.getId());
        workHoursType.setCreateTime(workHoursType1.getCreateTime());
        workHoursType.setUpdateTime(new Date());
        return workHoursTypeMapper.updateById(workHoursType);
    }

    @Override
    public PageResult<WorkHoursType> list(WorkHoursTypeVo workHoursTypeVO) {
        return workHoursTypeMapper.list(workHoursTypeVO);
    }

    @Override
    public WorkHoursType selectByWorkhoursType(String workhoursType) {
        LambdaQueryWrapperX<WorkHoursType> queryWrapper = new LambdaQueryWrapperX<>();
        queryWrapper.eq(WorkHoursType::getWorkhoursType,workhoursType);
        return workHoursTypeMapper.selectOne(queryWrapper);
    }
}
