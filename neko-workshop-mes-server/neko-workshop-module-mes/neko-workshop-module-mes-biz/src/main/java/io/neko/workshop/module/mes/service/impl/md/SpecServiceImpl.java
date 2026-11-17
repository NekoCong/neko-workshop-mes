package io.neko.workshop.module.mes.service.impl.md;

import io.neko.workshop.framework.common.pojo.PageResult;
import io.neko.workshop.framework.mybatis.core.query.LambdaQueryWrapperX;
import io.neko.workshop.module.mes.controller.admin.md.vo.NormQueryVo;
import io.neko.workshop.module.mes.controller.admin.md.vo.SpecExportVo;
import io.neko.workshop.module.mes.dal.dataobject.md.Spec;
import io.neko.workshop.module.mes.dal.mysql.md.SpecMapper;
import io.neko.workshop.module.mes.service.md.SpecService;
import io.neko.workshop.module.mes.utils.BeanCopyUtil;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import lombok.RequiredArgsConstructor;


/**
 * 规格ServiceImpl
 * @author 灵喵工坊
 */
@Service
@RequiredArgsConstructor
public class SpecServiceImpl implements SpecService {

    private final SpecMapper specMapper;


    @Override
    public Integer saveSpec(Spec spec) {
        spec.setCreateTime(new Date());
        return specMapper.insert(spec);
    }



    @Override
    public Integer removeSpecByIds(List<Long> ids) {
        return specMapper.deleteBatchIds(ids);
    }


    @Override
    public Integer updateSpec(Spec norm) {
        return specMapper.updateById(norm);
    }


    @Override
    public Spec findSpecById(Long id) {
        return specMapper.selectById(id);
    }


    @Override
    public PageResult<Spec> findPage(NormQueryVo normQueryVo) {
        return specMapper.findPage(normQueryVo);
    }


    @Override
    public Spec findSpecByName(String name) {
        return specMapper.selectOne(new LambdaQueryWrapperX<Spec>().eq(Spec::getName,name));
    }

    @Override
    public List<Spec> listAll() {
        return specMapper.selectList();
    }

    @Override
    public List<SpecExportVo> listData() {
        return BeanCopyUtil.copyListProperties(this.listAll(), SpecExportVo::new);
    }


}
