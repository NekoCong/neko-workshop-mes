package io.neko.workshop.module.mes.service.impl.qc;

import io.neko.workshop.framework.common.pojo.PageResult;

import io.neko.workshop.framework.mybatis.core.query.LambdaQueryWrapperX;
import io.neko.workshop.module.mes.controller.admin.qc.vo.OtherStandardQueryVo;
import io.neko.workshop.module.mes.dal.dataobject.md.Spec;
import io.neko.workshop.module.mes.dal.dataobject.qc.OtherStandard;
import io.neko.workshop.module.mes.dal.dataobject.qc.OtherStandardItem;
import io.neko.workshop.module.mes.dal.mysql.qc.OtherStandardMapper;
import io.neko.workshop.module.mes.service.qc.OtherStandardService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import lombok.RequiredArgsConstructor;

/**
 * @author 灵喵工坊
 * @time 2023/8/19 10:28
 */
@Service
@RequiredArgsConstructor
public class OtherStandardServiceImpl implements OtherStandardService {

    private final OtherStandardMapper otherStandardMapper;

    @Override
    public PageResult<OtherStandard> findPage(OtherStandardQueryVo otherStandardQueryVo) {
        return otherStandardMapper.findPage(otherStandardQueryVo);
    }

    @Override
    public List<OtherStandard> findOtherStandardList() {
        return otherStandardMapper.selectList();
    }

    @Override
    public List<OtherStandard> findOtherStandardByModel(String model) {
        LambdaQueryWrapperX<OtherStandard> queryWrapperX = new LambdaQueryWrapperX<>();
        queryWrapperX.like(OtherStandard::getMethod, model);
        return otherStandardMapper.selectList(queryWrapperX);
    }

    @Override
    public List<OtherStandard> findOtherStandardBySpec(String spec) {
        LambdaQueryWrapperX<OtherStandard> queryWrapperX = new LambdaQueryWrapperX<>();
        queryWrapperX.like(OtherStandard::getSpec, spec);
        return otherStandardMapper.selectList(queryWrapperX);
    }

    @Override
    public List<OtherStandard> findOtherStandardByLineType(String lineType) {
        LambdaQueryWrapperX<OtherStandard> queryWrapperX = new LambdaQueryWrapperX<>();
        queryWrapperX.like(OtherStandard::getLineType, lineType);
        return otherStandardMapper.selectList(queryWrapperX);
    }

    @Override
    public Integer saveOtherStandard(OtherStandard otherStandard) {
        return otherStandardMapper.insert(otherStandard);
    }

    @Override
    public Integer updateOtherStandard(OtherStandard otherStandard) {
        return otherStandardMapper.updateById(otherStandard);
    }

    @Override
    public OtherStandard findOtherStandard(Long id) {
        return otherStandardMapper.selectById(id);
    }

    @Override
    public Integer deleteOtherStandard(List<Long> ids) {
        return otherStandardMapper.deleteBatchIds(ids);
    }

    @Override
    public OtherStandard findOtherStandard(String model, String spec, String lineType) {
        return otherStandardMapper.findOtherStandard(model,spec,lineType);
    }
}
