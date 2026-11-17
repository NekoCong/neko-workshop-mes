package io.neko.workshop.module.mes.service.impl.md;

import io.neko.workshop.framework.common.pojo.PageResult;
import io.neko.workshop.framework.mybatis.core.query.LambdaQueryWrapperX;
import io.neko.workshop.module.mes.controller.admin.md.vo.ColorExportVo;
import io.neko.workshop.module.mes.controller.admin.md.vo.ColorQueryVo;
import io.neko.workshop.module.mes.dal.dataobject.md.Color;
import io.neko.workshop.module.mes.dal.mysql.md.ColorMapper;
import io.neko.workshop.module.mes.service.md.ColorService;
import io.neko.workshop.module.mes.utils.BeanCopyUtil;
import org.springframework.stereotype.Service;

import java.util.List;
import lombok.RequiredArgsConstructor;


/**
 * 盘号ServiceImpl
 * @author 灵喵工坊
 */
@Service
@RequiredArgsConstructor
public class ColorServiceImpl implements ColorService {

    private final ColorMapper colorMapper;


    @Override
    public Integer saveColor(Color color) {
        return colorMapper.insert(color);
    }

    @Override
    public Integer removeColorByIds(List<Long> ids) {
        return colorMapper.deleteBatchIds(ids);
    }

    @Override
    public Integer updateColor(Color color) {
        return colorMapper.updateById(color);
    }

    @Override
    public Color findColorById(Long id) {
        return colorMapper.selectById(id);
    }

    @Override
    public PageResult<Color> findPage(ColorQueryVo colorQueryVo) {
        return colorMapper.findPage(colorQueryVo);
    }

    @Override
    public Color findColorByName(String name) {
        return colorMapper.selectOne(new LambdaQueryWrapperX<Color>().eq(Color::getName,name));
    }

    @Override
    public List<Color> listAll() {
        return colorMapper.selectList();
    }

    @Override
    public List<ColorExportVo> listData() {
        return BeanCopyUtil.copyListProperties(this.listAll(), ColorExportVo::new);
    }

}
