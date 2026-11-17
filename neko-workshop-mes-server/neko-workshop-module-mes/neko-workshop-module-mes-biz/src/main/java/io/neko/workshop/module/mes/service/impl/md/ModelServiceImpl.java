package io.neko.workshop.module.mes.service.impl.md;

import io.neko.workshop.framework.common.pojo.PageResult;
import io.neko.workshop.framework.mybatis.core.query.LambdaQueryWrapperX;
import io.neko.workshop.module.mes.controller.admin.md.vo.ModelExportVo;
import io.neko.workshop.module.mes.controller.admin.md.vo.ModelQueryVo;
import io.neko.workshop.module.mes.dal.dataobject.md.Model;
import io.neko.workshop.module.mes.dal.mysql.md.ModelMapper;
import io.neko.workshop.module.mes.service.md.ModelService;
import io.neko.workshop.module.mes.utils.BeanCopyUtil;
import org.springframework.stereotype.Service;

import java.util.List;
import lombok.RequiredArgsConstructor;



/**
 * 型号ServiceImpl
 * @author 灵喵工坊
 */
@Service
@RequiredArgsConstructor
public class ModelServiceImpl implements ModelService {

    private final ModelMapper modelMapper;

    @Override
    public Integer saveModel(Model model) {
        return modelMapper.insert(model);
    }

    @Override
    public Integer removeModelByIds(List<Long> ids) {
        return modelMapper.deleteBatchIds(ids);
    }

    @Override
    public Integer updateModel(Model model) {
        return modelMapper.updateById(model);
    }

    @Override
    public Model findModelById(Long id) {
        return modelMapper.selectById(id);
    }

    @Override
    public PageResult<Model> findPage(ModelQueryVo modelQueryVo) {
        return modelMapper.findPage(modelQueryVo);
    }

    @Override
    public Model findModelByName(String name) {
        return modelMapper.selectOne(new LambdaQueryWrapperX<Model>().eq(Model::getName,name));
    }

    @Override
    public List<Model> listAll() {
        return modelMapper.selectList();
    }

    @Override
    public List<ModelExportVo> listData() {
        return BeanCopyUtil.copyListProperties(this.listAll(), ModelExportVo::new);
    }
}
