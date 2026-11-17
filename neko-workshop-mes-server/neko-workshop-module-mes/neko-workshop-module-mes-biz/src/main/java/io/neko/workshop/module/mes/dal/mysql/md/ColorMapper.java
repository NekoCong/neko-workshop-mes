package io.neko.workshop.module.mes.dal.mysql.md;

import io.neko.workshop.framework.common.pojo.PageResult;
import io.neko.workshop.framework.mybatis.core.mapper.BaseMapperX;
import io.neko.workshop.framework.mybatis.core.query.LambdaQueryWrapperX;
import io.neko.workshop.module.mes.controller.admin.md.vo.ColorQueryVo;
import io.neko.workshop.module.mes.dal.dataobject.md.Color;
import io.neko.workshop.module.mes.utils.StringUtils;
import org.apache.ibatis.annotations.Mapper;

/**
 * 颜色Mapper
 *
 * @author 灵喵工坊MES
 */
@Mapper
public interface ColorMapper extends BaseMapperX<Color> {

    default PageResult<Color> findPage(ColorQueryVo colorQueryVo) {
        LambdaQueryWrapperX<Color> queryWrapperX = new LambdaQueryWrapperX<>();
        if(StringUtils.isNotBlank(colorQueryVo.getName())){
            queryWrapperX.like(Color::getName,colorQueryVo.getName());
        }
        if(StringUtils.isBlank(colorQueryVo.getName())){
            queryWrapperX.isNotNull(Color::getId);
        }
        return selectPage(colorQueryVo, queryWrapperX);
    };

}
