package io.neko.workshop.module.mes.service.impl.qc;

import io.neko.workshop.framework.common.pojo.PageResult;
import io.neko.workshop.framework.mybatis.core.query.LambdaQueryWrapperX;
import io.neko.workshop.module.mes.controller.admin.qc.vo.StandardQueryVo;
import io.neko.workshop.module.mes.dal.dataobject.qc.Standard;
import io.neko.workshop.module.mes.dal.mysql.qc.StandardMapper;
import io.neko.workshop.module.mes.service.qc.StandardService;
import org.springframework.stereotype.Service;

import java.util.List;
import lombok.RequiredArgsConstructor;

/**
 * @author 灵喵工坊
 * @time 2023/7/6 13:43
 */
@Service
@RequiredArgsConstructor
public class StandardServiceImpl implements StandardService {

    private final StandardMapper standardMapper;

    @Override
    public Integer saveInspectStandard(Standard inspectStandard) {
        return standardMapper.insert(inspectStandard);
    }

    @Override
    public Integer removeInspectStandard(List<Long> ids) {
        return standardMapper.deleteBatchIds(ids);
    }

    @Override
    public Standard findStandardById(Long id) {
        return standardMapper.selectById(id);
    }

    @Override
    public Integer updateInspectStandard(Standard inspectStandard) {
        return standardMapper.updateById(inspectStandard);
    }

    @Override
    public PageResult<Standard> findPage(StandardQueryVo standardQueryVo) {
        return standardMapper.findPage(standardQueryVo);
    }

    @Override
    public List<Standard> findStandardList() {
        return standardMapper.selectList();
    }

    @Override
    public List<Standard> findStandardByProcessCode(String code) {
        LambdaQueryWrapperX<Standard> queryWrapperX = new LambdaQueryWrapperX<>();
        queryWrapperX.like(Standard::getProcessCode, code);
        return standardMapper.selectList(queryWrapperX);
    }
}
